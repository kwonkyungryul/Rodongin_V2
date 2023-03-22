package shop.mtcoding.rodongin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeJoinInDto;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.service.employee.EmployeeService;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    public final EmployeeService employeeService;

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

}
