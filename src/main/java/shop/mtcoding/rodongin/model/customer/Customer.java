package shop.mtcoding.rodongin.model.customer;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {
    private Integer id;
    private String customerTitle;
    private String customerContent;
    private Integer employeeId;
    private String employeeName;
    private Timestamp createdAt;

}
