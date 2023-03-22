package shop.mtcoding.rodongin.dto.employee;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeJoinInDto {
    private String employeeName;
    private String employeePassword;
    private String employeeEmail;
    private String employeeFullname;
    private Date employeeBirth;
    private String employeeTel;
    private String employeeGender;
    private String employeeAddress;
    private Timestamp createdAt;
}
