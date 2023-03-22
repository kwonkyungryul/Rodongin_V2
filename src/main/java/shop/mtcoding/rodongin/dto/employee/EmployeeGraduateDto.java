package shop.mtcoding.rodongin.dto.employee;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EmployeeGraduateDto {
    private Integer id;
    private Integer schoolId;
    private String schoolGraduate;
    private Timestamp createdAt;
}
