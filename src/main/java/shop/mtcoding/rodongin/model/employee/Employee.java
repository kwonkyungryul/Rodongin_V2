package shop.mtcoding.rodongin.model.employee;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String employeeName;
    private String employeePassword;
    private String employeeRole;
    private String employeeFullname;
    private String employeeEmail;
    private String employeeTel;
    private String employeeGender;
    private Date employeeBirth;
    private String employeeAddress;
    private String employeeThumbnail;
    private Timestamp createdAt;

    @Builder
    public Employee(Integer id, String employeeName, String employeePassword, String employeeRole, String employeeFullname, String employeeEmail,
            String employeeTel, String employeeGender, Date employeeBirth, String employeeAddress,
            String employeeThumbnail) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.employeeRole = employeeRole;
        this.employeeFullname = employeeFullname;
        this.employeeEmail = employeeEmail;
        this.employeeTel = employeeTel;
        this.employeeGender = employeeGender;
        this.employeeBirth = employeeBirth;
        this.employeeAddress = employeeAddress;
        this.employeeThumbnail = employeeThumbnail;
    }

}
