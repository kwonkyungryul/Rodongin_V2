package shop.mtcoding.rodongin.model.apply;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Apply {
    private Integer id;
    private Integer resumeId;
    private Integer companyId;
    private Timestamp createdAt;
    
    public Apply(Integer resumeId, Integer companyId) {
        this.resumeId = resumeId;
        this.companyId = companyId;
    }
}
