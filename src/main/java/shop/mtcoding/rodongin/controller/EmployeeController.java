package shop.mtcoding.rodongin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeDetailOutDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.service.employee.EmployeeService;
import shop.mtcoding.rodongin.util.MySession;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class EmployeeController {


    private final EmployeeService employeeService;

    private final HttpSession session;

    @GetMapping("/employees")
    public ResponseEntity<?> detail() {
        Employee principal = MySession.MyPrincipal(session);
//        Employee principal = (Employee) session.getAttribute("principal");
        if (principal == null) {
            return new ResponseEntity<>(new ResponseDto<>(-1, "인증이 되지 않았습니다.", null), HttpStatus.UNAUTHORIZED);
        }
        EmployeeDetailOutDto response = employeeService.유저정보조회(principal.getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "개인정보 페이지", response), HttpStatus.OK);
    }
}
