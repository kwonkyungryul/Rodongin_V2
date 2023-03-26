package shop.mtcoding.rodongin.service.company;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.config.auth.JwtProvider;
import shop.mtcoding.rodongin.dto.company.CompanyDetailOutDto;
import shop.mtcoding.rodongin.dto.company.CompanyJoinInDto;
import shop.mtcoding.rodongin.dto.company.CompanyLoginInDto;
import shop.mtcoding.rodongin.dto.company.CompanyUpdateInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.model.company.CompanyRepository;
import shop.mtcoding.rodongin.util.Encode;
import shop.mtcoding.rodongin.util.PathUtil;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final HttpSession session;

    public CompanyDetailOutDto 기업상세보기(int id) {
        CompanyDetailOutDto DetailDto = companyRepository.findById(id);
        return DetailDto;
    }

    @Transactional
    public String 로그인(CompanyLoginInDto companyLoginInDto, HttpServletResponse response, String companyUsername) {
        Company principalPS = companyRepository.findByCompanyUsername(companyLoginInDto.getCompanyUsername());
        if (principalPS == null) {
            throw new CustomApiException("일치하는 회원 정보가 없습니다.");
        }
        boolean isCheck;
        try {
            isCheck = Encode.matches(companyLoginInDto.getCompanyPassword(), principalPS.getCompanyPassword());
        } catch (Exception e) {
            throw new CustomApiException("???");
        }

        if (!isCheck) {
            throw new CustomApiException("비밀번호가 다릅니다.");
        }
        companyLoginInDto.setCompanyPassword(principalPS.getCompanyPassword());

        Company principal = companyRepository.findByCompanyNameAndPassword(companyLoginInDto);
        String jwt;
        if (principal != null) {
            jwt = JwtProvider.create(principal);
            System.out.println(jwt);
        } else {
            throw new CustomApiException("");
        }

        if (companyUsername ==  null || companyLoginInDto.getCompanyUsername().isEmpty()) {
            companyUsername = "";
        }

        if (companyUsername.equals("on")) {
            Cookie cookie = new Cookie("remember", companyLoginInDto.getCompanyUsername());
            cookie.setMaxAge(60);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("remember", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return jwt;

    }

    @Transactional
    public void 기업회원가입(CompanyJoinInDto companyJoinInDto) {

        Company sameCompany = companyRepository.findByCompanyUsername(companyJoinInDto.getCompanyUsername());

        if (sameCompany != null) {
            throw new CustomApiException("동일한 아이디가 존재합니다");
        }

        String encodedPassword = "";
        try {
            
            encodedPassword = Encode.passwordEncode(companyJoinInDto.getCompanyPassword());

        } catch (Exception e) {
            throw new CustomApiException("비밀번호 해싱 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        companyJoinInDto.setCompanyPassword(encodedPassword);
        // System.out.println("테스트");

        companyRepository.insert(companyJoinInDto);
        try {

        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Transactional
    public void 기업소개등록(int id, CompanyUpdateInDto companyUpdateInDto) {

        Company comPrincipal = (Company) session.getAttribute("comPrincipal");

        CompanyDetailOutDto principalPS = companyRepository.findById(id);
        if (principalPS.getId() != comPrincipal.getId()) {
            throw new CustomApiException("기업소개를 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        if (companyUpdateInDto.getCompanyThumbnail() != null) {
            String thumbnail = PathUtil.writeImageFile(companyUpdateInDto.getCompanyThumbnail());
            companyUpdateInDto.setCompanyThumbnail(thumbnail);
        }


        try {
            companyRepository.updateById(companyUpdateInDto, id);
            } catch (Exception e) {
        throw new CustomApiException("기업소개 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
}
