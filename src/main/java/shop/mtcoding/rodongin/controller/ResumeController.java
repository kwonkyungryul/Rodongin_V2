package shop.mtcoding.rodongin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.resume.ResumeDetailOutDto;
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

}
