package shop.mtcoding.rodongin.dto.resume;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ResumeDto {
    private Integer id;
    private Integer employeeId;
    private String resumeTitle;
    private String resumeSalary;
    private String CV;
    private Timestamp createdAt;
}