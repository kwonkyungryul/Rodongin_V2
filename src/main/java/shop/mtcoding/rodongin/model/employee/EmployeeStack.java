package shop.mtcoding.rodongin.model.employee;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeStack {
    private Integer id;
    private Integer employeeId;
    private Integer stackId;
    private String stackAcquisition;
    private Timestamp createdAt;

    public EmployeeStack(Integer employeeId, Integer stackId, String stackAcquisition) {
        this.employeeId = employeeId;
        this.stackId = stackId;
        this.stackAcquisition = stackAcquisition;
    }
}