package shop.mtcoding.rodongin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeDetailOutDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeJoinInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeLoginInDto;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.service.employee.EmployeeService;
import shop.mtcoding.rodongin.util.MySession;

import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/employee/join")
    public ResponseEntity<?> join(EmployeeJoinInDto employeeJoinInDto) throws Exception {

        if (employeeJoinInDto.getEmployeeName() == null || employeeJoinInDto.getEmployeeName().isEmpty()) {
            throw new CustomException("아이디를 작성해주세요");
        }
        if (employeeJoinInDto.getEmployeePassword() == null || employeeJoinInDto.getEmployeePassword().isEmpty()) {
            throw new CustomException("비밀번호를 작성해주세요");
        }
        if (employeeJoinInDto.getEmployeeEmail() == null || employeeJoinInDto.getEmployeeEmail().isEmpty()) {
            throw new CustomException("email을 작성해주세요");
        }
        if (employeeJoinInDto.getEmployeeFullname() == null || employeeJoinInDto.getEmployeeFullname().isEmpty()) {
            throw new CustomException("성함을 작성해주세요");
        }
        if (employeeJoinInDto.getEmployeeBirth() == null) {
            throw new CustomException("생일을 작성해주세요");
        }
        if (employeeJoinInDto.getEmployeeTel() == null || employeeJoinInDto.getEmployeeTel().isEmpty()) {
            throw new CustomException("연락처를 작성해주세요");
        }
        if (employeeJoinInDto.getEmployeeGender() == null) {
            throw new CustomException("성별을 선택해주세요");
        }
        if (employeeJoinInDto.getEmployeeAddress() == null || employeeJoinInDto.getEmployeeAddress().isEmpty()) {
            throw new CustomException("주소를 작성해주세요");
        }

        employeeService.회원가입(employeeJoinInDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 완료", null), HttpStatus.CREATED);

    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> login(EmployeeLoginInDto employeeLoginInDto,
            HttpServletResponse response,
            @RequestParam(value = "remember", required = false) String employeeName) {

        if (employeeLoginInDto.getEmployeeName() == null || employeeLoginInDto.getEmployeeName().isEmpty()) {
            throw new CustomException("username을 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        if (employeeLoginInDto.getEmployeePassword() == null || employeeLoginInDto.getEmployeePassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        Employee principal = employeeService.로그인(employeeLoginInDto, response, employeeName);

        session.setAttribute("principal", principal);

        return new ResponseEntity<>(new ResponseDto<>(1, "로그인 완료", null), HttpStatus.OK);
    }

}
