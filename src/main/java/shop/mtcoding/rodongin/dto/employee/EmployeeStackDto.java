package shop.mtcoding.rodongin.dto.employee;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EmployeeStackDto {
    private Integer id;
    private Integer stackId;
    private String stackAcquisition;
    private Timestamp createdAt;
}