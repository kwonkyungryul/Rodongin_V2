package shop.mtcoding.rodongin.model.master;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SchoolMaster {
    private Integer id;
    private String schoolName;
    private Timestamp createdAt;
}
