package shop.mtcoding.rodongin.dto.employee;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
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

    @Builder
    public EmployeeJoinInDto(String employeeName, String employeePassword, String employeeEmail,
            String employeeFullname, Date employeeBirth, String employeeTel, String employeeGender,
            String employeeAddress, Timestamp createdAt) {
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.employeeEmail = employeeEmail;
        this.employeeFullname = employeeFullname;
        this.employeeBirth = employeeBirth;
        this.employeeTel = employeeTel;
        this.employeeGender = employeeGender;
        this.employeeAddress = employeeAddress;
        this.createdAt = createdAt;
    }

}
