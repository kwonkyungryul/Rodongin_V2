package shop.mtcoding.rodongin.model.employee;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeGraduate {
    private Integer id;
    private Integer employeeId;
    private Integer schoolId;
    private String schoolGraduate;
    private Timestamp createdAt;

    public EmployeeGraduate(Integer employeeId, Integer schoolId, String schoolGraduate) {
        this.employeeId = employeeId;
        this.schoolId = schoolId;
        this.schoolGraduate = schoolGraduate;
    }

}
