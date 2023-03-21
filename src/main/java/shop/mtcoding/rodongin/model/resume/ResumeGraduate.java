package shop.mtcoding.rodongin.model.resume;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResumeGraduate {
    private Integer id;
    private Integer resumeId;
    private Integer schoolId;
    private String schoolGraduate;
    private Timestamp createdAt;
    
    public ResumeGraduate(Integer resumeId, Integer schoolId, String schoolGraduate) {
        this.resumeId = resumeId;
        this.schoolId = schoolId;
        this.schoolGraduate = schoolGraduate;
    }
}
