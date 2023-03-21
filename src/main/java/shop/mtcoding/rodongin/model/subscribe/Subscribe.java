package shop.mtcoding.rodongin.model.subscribe;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Subscribe {
    private Integer id;
    private Integer employeeId;
    private Integer companyId;
    private Timestamp createdAt;

    public Subscribe (Integer employeeId, Integer companyId) {
        this.employeeId = employeeId;
        this.companyId = companyId;
    }
}