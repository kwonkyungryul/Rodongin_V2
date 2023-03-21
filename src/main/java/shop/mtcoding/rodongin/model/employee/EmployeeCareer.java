package shop.mtcoding.rodongin.model.employee;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeCareer {
    private Integer id;
    private Integer employeeId;
    private String careerCompany;
    private Date careerStart;
    private Date careerEnd;
    private Timestamp createdAt;

    public EmployeeCareer(Integer employeeId, String careerCompany, Date careerStart, Date careerEnd) {
        this.employeeId = employeeId;
        this.careerCompany = careerCompany;
        this.careerStart = careerStart;
        this.careerEnd = careerEnd;
    }

}
