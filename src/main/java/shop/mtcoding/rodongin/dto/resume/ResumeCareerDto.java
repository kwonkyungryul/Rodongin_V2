package shop.mtcoding.rodongin.dto.resume;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ResumeCareerDto {
    private Integer id;
    private String careerCompany;
    private Date careerStart;
    private Date careerEnd;
    private Timestamp createdAt;
}
