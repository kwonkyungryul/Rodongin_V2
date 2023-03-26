package shop.mtcoding.rodongin.dto.employee;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class EmployeeUpdateInDto {

    private Integer id;
    private String employeePassword;
    private String employeeEmail;
    private Date employeeBirth;
    private String employeeTel;
    private String employeeAddress;
    private String employeeThumbnail;

}
