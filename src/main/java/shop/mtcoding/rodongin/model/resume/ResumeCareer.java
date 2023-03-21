package shop.mtcoding.rodongin.model.resume;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResumeCareer {
    private Integer id;
    private Integer resumeId;
    private String careerCompany;
    private Date careerStart;
    private Date careerEnd;
    private Timestamp createdAt;

    public ResumeCareer(Integer resumeId, String careerCompany, Date careerStart,
            Date careerEnd) {
        this.resumeId = resumeId;
        this.careerCompany = careerCompany;
        this.careerStart = careerStart;
        this.careerEnd = careerEnd;
    }
}
