package shop.mtcoding.rodongin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.resume.ResumeDetailOutDto;
import shop.mtcoding.rodongin.dto.resume.ResumeSaveInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.service.resume.ResumeService;
import shop.mtcoding.rodongin.util.MySession;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class ResumeController {

    private final ResumeService resumeService;

    private final HttpSession session;

    @GetMapping("/resumes/{id}")
    public ResponseEntity<?> detail(@PathVariable int id) {
        Employee principal = MySession.MyPrincipal(session);

        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        ResumeDetailOutDto resumeDetailOutDto = resumeService.이력서상세보기(principal.getId(), id);
        return new ResponseEntity<>(new ResponseDto<>(1, "이력서 상세보기", resumeDetailOutDto), HttpStatus.OK);
    }

    @PostMapping("/resumes")
    public ResponseEntity<?> save(@RequestBody ResumeSaveInDto resumeSaveInDto) {
        Employee principal = MySession.MyPrincipal(session);

        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }
        
        if (resumeSaveInDto.getResumeTitle() == null || resumeSaveInDto.getResumeTitle().isEmpty()) {
            throw new CustomApiException("이력서 제목을 입력해주세요");
        }

        if (resumeSaveInDto.getResumeSalary() == null || resumeSaveInDto.getResumeSalary().isEmpty()) {
            throw new CustomApiException("희망연봉을 입력해주세요");
        }

        if (resumeSaveInDto.getCV() == null || resumeSaveInDto.getCV().isEmpty()) {
            throw new CustomApiException("자기소개서를 입력해주세요");
        }

        resumeService.이력서등록(principal.getId(), resumeSaveInDto);
        
        return new ResponseEntity<>(new ResponseDto<>(1, "이력서 저장 성공", null), HttpStatus.CREATED);
    }

}
