package shop.mtcoding.rodongin.model.master;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LicenseMaster {
    private Integer id;
    private String licenseName;
    private Timestamp createdAt;
}
