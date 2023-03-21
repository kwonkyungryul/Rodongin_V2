package shop.mtcoding.rodongin.model.resume;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Resume {
    private Integer id;
    private Integer employeeId;
    private String resumeTitle;
    private String resumeSalary;
    private String CV;
    private Timestamp createdAt;

    public Resume(Integer employeeId, String resumeTitle, String resumeSalary, String cV) {
        this.employeeId = employeeId;
        this.resumeTitle = resumeTitle;
        this.resumeSalary = resumeSalary;
        CV = cV;
    }
}
