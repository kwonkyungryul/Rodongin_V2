package shop.mtcoding.rodongin.dto.employee;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeUpdateInDto {

    private Integer id;
    private String employeePassword;
    private String employeeEmail;
    private Date employeeBirth;
    private String employeeTel;
    private String employeeAddress;
    private String employeeThumbnail;

}
