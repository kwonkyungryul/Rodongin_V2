package shop.mtcoding.rodongin.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.company.CompanyDetailOutDto;
import shop.mtcoding.rodongin.dto.company.CompanyLoginInDto;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.service.company.CompanyService;
@RequiredArgsConstructor
@RestController
public class CompanyController {

    private final CompanyService companyService;

    private final HttpSession session;

    @GetMapping("/company/{id}")
    public ResponseEntity<?> detail(@PathVariable int id) {
        CompanyDetailOutDto dto = companyService.기업상세보기(id);
        // model.addAttribute("detailDto", dto);
        return new ResponseEntity<>(new ResponseDto<>(1, "기업상세보기", dto), HttpStatus.OK);
    }

    @PostMapping("/company/login")
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
        // return new ResponseEntity<>(new ResponseDto<>(1, "기업로그인완료", null), HttpStatus.OK);
        
    }

}
