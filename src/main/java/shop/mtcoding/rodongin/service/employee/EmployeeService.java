package shop.mtcoding.rodongin.service.employee;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.employee.EmployeeJoinInDto;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.model.employee.EmployeeRepository;
import shop.mtcoding.rodongin.util.Encode;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    public final EmployeeRepository employeeRepository;

    @Transactional
    public void 회원가입(EmployeeJoinInDto employeeJoinInDto) {

        Employee sameEmployee = employeeRepository.findByEmployeeName(employeeJoinInDto.getEmployeeName());

        if (sameEmployee != null) {
            throw new CustomException("동일한 username이 존재합니다");
        }

        String encodedPassword = "";

        try {
            encodedPassword = Encode.passwordEncode(employeeJoinInDto.getEmployeePassword());

        } catch (Exception e) {
            throw new CustomException("비밀번호 해싱 오류", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        employeeJoinInDto.setEmployeePassword(encodedPassword);
        System.out.println("테스트");

        employeeRepository.insert(employeeJoinInDto);

        try {

        } catch (Exception e) {
            throw new CustomException("일시적인 서버 에러입니다.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
