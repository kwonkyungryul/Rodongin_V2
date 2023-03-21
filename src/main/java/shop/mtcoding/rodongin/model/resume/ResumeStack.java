package shop.mtcoding.rodongin.model.resume;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResumeStack {
    private Integer id;
    private Integer resumeId;
    private Integer stackId;
    private String stackAcquisition;
    private Timestamp createdAt;
}
