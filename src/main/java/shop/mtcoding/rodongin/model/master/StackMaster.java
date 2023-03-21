package shop.mtcoding.rodongin.model.master;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StackMaster {
    private Integer id;
    private String stackName;
    private Timestamp createdAt;
}
