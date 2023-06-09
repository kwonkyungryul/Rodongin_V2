package shop.mtcoding.rodongin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.rodongin.config.auth.JwtProvider;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.employee.*;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.service.employee.EmployeeService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@Tag(name = "1. 구직자 정보관리", description = "구직자 정보관리")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final HttpSession session;

    @PutMapping("/s/employees")
    @Operation(summary = "4. 구직자 정보수정", description = "구직자 개인정보를 수정합니다.")
    public ResponseEntity<?> update(@RequestBody EmployeeUpdateInDto employeeUpdateInDto) {

        if (employeeUpdateInDto.getEmployeePassword() == null ||
                employeeUpdateInDto.getEmployeePassword().isEmpty()) {
            throw new CustomApiException("Password을 작성해주세요");
        }
        if (employeeUpdateInDto.getEmployeeEmail() == null ||
                employeeUpdateInDto.getEmployeeEmail().isEmpty()) {
            throw new CustomApiException("Email을 작성해주세요");
        }
        if (employeeUpdateInDto.getEmployeeBirth() == null) {
            throw new CustomApiException("Birth을 작성해주세요");
        }
        if (employeeUpdateInDto.getEmployeeTel() == null ||
                employeeUpdateInDto.getEmployeeTel().isEmpty()) {
            throw new CustomApiException("Tel을 작성해주세요");
        }
        if (employeeUpdateInDto.getEmployeeAddress() == null ||
                employeeUpdateInDto.getEmployeeAddress().isEmpty()) {
            throw new CustomApiException("Address을 작성해주세요");
        }

        employeeService.회원정보수정(employeeUpdateInDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "회원정보 수정 완료!", null), HttpStatus.OK);
    }

    @PostMapping("/s/employees")
    @Operation(summary = "5. 구직자 상세정보 추가", description = "구직자 상세정보를 추가합니다.")
    public ResponseEntity<?> save(@RequestBody EmployeeSaveInDto employeeSaveInDto) {

        employeeService.개인정보추가(employeeSaveInDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "개인정보 추가 완료!", null), HttpStatus.CREATED);
    }

    @GetMapping("/s/employees")
    @Operation(summary = "3. 구직자 정보보기", description = "구직자 개인정보를 조회합니다.")
    public ResponseEntity<?> detail() {
        EmployeeDetailOutDto response = employeeService.유저정보조회();

        return new ResponseEntity<>(new ResponseDto<>(1, "개인정보 페이지", response), HttpStatus.OK);
    }

    @PostMapping("/employees/join")
    @Operation(summary = "1. 구직자 회원가입", description = "구직자 회원가입을 합니다.")
    public ResponseEntity<?> join(@RequestBody EmployeeJoinInDto employeeJoinInDto) throws Exception {
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

    @PostMapping("/employees/login")
    @Operation(summary = "2. 구직자 로그인", description = "구직자 로그인을 합니다.")
    public ResponseEntity<?> login(@RequestBody EmployeeLoginInDto employeeLoginInDto,
            HttpServletResponse response,
            @RequestParam(value = "remember", required = false) String employeeName) {

        if (employeeLoginInDto.getEmployeeName() == null || employeeLoginInDto.getEmployeeName().isEmpty()) {
            throw new CustomException("username을 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        if (employeeLoginInDto.getEmployeePassword() == null || employeeLoginInDto.getEmployeePassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요", HttpStatus.BAD_REQUEST);
        }

        String jwt = employeeService.로그인(employeeLoginInDto, response, employeeName);

        return ResponseEntity.ok().header(JwtProvider.HEADER, jwt).body("구직자 로그인 성공");
    }

}
