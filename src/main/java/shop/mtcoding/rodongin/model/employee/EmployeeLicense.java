package shop.mtcoding.rodongin.model.employee;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeLicense {
    private Integer id;
    private Integer employeeId;
    private Integer licenseId;
    private String licenseIssuer;
    private Timestamp createdAt;
    public EmployeeLicense(Integer employeeId, Integer licenseId, String licenseIssuer) {
        this.employeeId = employeeId;
        this.licenseId = licenseId;
        this.licenseIssuer = licenseIssuer;
    }
}
