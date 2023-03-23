package shop.mtcoding.rodongin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.company.CompanyDetailOutDto;
import shop.mtcoding.rodongin.dto.company.CompanyJoinInDto;
import shop.mtcoding.rodongin.dto.company.CompanyLoginInDto;
import shop.mtcoding.rodongin.dto.company.CompanyUpdateInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.service.company.CompanyService;
import shop.mtcoding.rodongin.util.MySession;
@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;

    private final HttpSession session;

    @GetMapping("/companes/{id}")
    public ResponseEntity<?> detail(@PathVariable int id) {
        CompanyDetailOutDto dto = companyService.기업상세보기(id);
        // model.addAttribute("detailDto", dto);
        return new ResponseEntity<>(new ResponseDto<>(1, "기업상세보기", dto), HttpStatus.OK);
    }

    @PostMapping("/companes/login")
    public ResponseEntity<?>  login(@RequestBody CompanyLoginInDto companyLoginInDto, HttpServletResponse response, 
    @RequestParam(value = "remember", required = false) String companyUsername) {
        // System.out.println(companyLoginReqDto.getCompanyUsername());
        // System.out.println(companyLoginReqDto.getCompanyPassword());
        if (companyLoginInDto.getCompanyUsername() == null || companyLoginInDto.getCompanyUsername().isEmpty()) {
            throw new CustomException("기업아이디를 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        if (companyLoginInDto.getCompanyPassword() == null || companyLoginInDto.getCompanyPassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        
       
        Company principal = companyService.로그인(companyLoginInDto, response, companyUsername);

        session.setAttribute("comPrincipal", principal);

        return new ResponseEntity<>(new ResponseDto<>(1, "기업로그인완료", null), HttpStatus.OK);

        
    }

    @PostMapping("/companes/join")
    public ResponseEntity<?> join(@RequestBody CompanyJoinInDto companyJoinInDto) throws Exception {
        if (companyJoinInDto.getCompanyUsername() == null || companyJoinInDto.getCompanyUsername().isEmpty()) {
            throw new CustomException("아이디를 작성해주세요");
        }
        if (companyJoinInDto.getCompanyPassword() == null || companyJoinInDto.getCompanyPassword().isEmpty()) {
            throw new CustomException("비밀번호를 작성해주세요");
        }
        if (companyJoinInDto.getCompanyLicenseNumber() == null
                || companyJoinInDto.getCompanyLicenseNumber().isEmpty()) {
            throw new CustomException("사업자등록번호를 작성해주세요");
        }
        if (companyJoinInDto.getCompanyCeoName() == null || companyJoinInDto.getCompanyCeoName().isEmpty()) {
            throw new CustomException("대표자 성함을 작성해주세요");
        }
        if (companyJoinInDto.getCompanyFullname() == null || companyJoinInDto.getCompanyFullname().isEmpty()) {
            throw new CustomException("기업명을 작성해주세요");
        }
        if (companyJoinInDto.getCompanyTel() == null || companyJoinInDto.getCompanyTel().isEmpty()) {
            throw new CustomException("연락처를 작성해주세요");
        }
        if (companyJoinInDto.getCompanyAddress() == null || companyJoinInDto.getCompanyAddress().isEmpty()) {
            throw new CustomException("주소를 작성해주세요");
        }
        if (companyJoinInDto.getCompanyEmail() == null || companyJoinInDto.getCompanyEmail().isEmpty()) {
            throw new CustomException("email을 작성해주세요");
        }

        String tel = companyJoinInDto.getCompanyTel().replaceAll(",", "");
        companyJoinInDto.setCompanyTel(tel);

        String address = companyJoinInDto.getCompanyAddress().replaceAll(",", "");
        companyJoinInDto.setCompanyAddress(address);

        String email = companyJoinInDto.getCompanyEmail().replaceAll(",", "");
        
        companyJoinInDto.setCompanyEmail(email);

        companyService.기업회원가입(companyJoinInDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "기업회원가입완료", null), HttpStatus.CREATED);
    }


    @PutMapping("/companes/update/{id}")
    public ResponseEntity<?> update(MultipartFile profile, @PathVariable int id,
    @RequestBody CompanyUpdateInDto companyUpdateInDto, HttpServletResponse response){

        Company comPrincipal = MySession.CompanyPrincipal(session);
        // Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        if (comPrincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        if (companyUpdateInDto.getCompanyEstablish() == null){
            throw new CustomApiException("Etablish를 작성해주세요");
        } 
        if (companyUpdateInDto.getCompanyFullname() == null || companyUpdateInDto.getCompanyFullname().isEmpty()) {
            throw new CustomApiException("Fullname을 작성해주세요");
        }       
        if (companyUpdateInDto.getCompanyIntroduction() == null || companyUpdateInDto.getCompanyIntroduction().isEmpty()) {
            throw new CustomApiException("Introduction을 작성해주세요");
        }
        if (companyUpdateInDto.getCompanyHistory() == null || companyUpdateInDto.getCompanyHistory().isEmpty()) {
            throw new CustomApiException("History를 작성해주세요");
        }
        if (companyUpdateInDto.getCompanyVision() == null || companyUpdateInDto.getCompanyVision().isEmpty()) {
            throw new CustomApiException("Vision을 작성해주세요");
        }
        
        companyService.기업소개등록(id, companyUpdateInDto, comPrincipal.getId(), profile);

        session.setAttribute("comPrincipal", comPrincipal);

        return new ResponseEntity<>(new ResponseDto<>(1, "기업소개 수정성공", null), HttpStatus.OK);
            
        
    }
    
}
