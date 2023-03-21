package shop.mtcoding.rodongin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.company.CompanyDetailOutDto;
import shop.mtcoding.rodongin.service.company.CompanyService;
@RequiredArgsConstructor
@RestController
@Controller
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/company/{id}")
    public ResponseEntity<?> detail(@PathVariable int id, Model model) {
        CompanyDetailOutDto dto = companyService.기업상세보기(id);
        // model.addAttribute("detailDto", dto);
        return new ResponseEntity<>(new ResponseDto<>(1, "기업상세보기", dto), HttpStatus.OK);
    }
}
