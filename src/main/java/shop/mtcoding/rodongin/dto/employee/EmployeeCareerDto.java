package shop.mtcoding.rodongin.dto.employee;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class EmployeeCareerDto {
    private Integer id;
    private String careerCompany;
    private Date careerStart;
    private Date careerEnd;
    private Timestamp createdAt;
}
