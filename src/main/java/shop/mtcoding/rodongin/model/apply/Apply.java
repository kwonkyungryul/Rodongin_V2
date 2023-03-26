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
    private Integer announcementId;
    private Integer resumeId;
    private Timestamp createdAt;

    public Apply(Integer id, Integer announcementId, Integer resumeId, Timestamp createdAt) {
        this.id = id;
        this.announcementId = announcementId;
        this.resumeId = resumeId;
        this.createdAt = createdAt;
    }

}
