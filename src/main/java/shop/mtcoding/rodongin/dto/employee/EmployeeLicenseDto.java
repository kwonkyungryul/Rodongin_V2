package shop.mtcoding.rodongin.dto.employee;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EmployeeLicenseDto {
    private Integer id;
    private Integer licenseId;
    private String licenseIssuer;
    private Timestamp createdAt;
}