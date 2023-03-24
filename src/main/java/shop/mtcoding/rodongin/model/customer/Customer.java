package shop.mtcoding.rodongin.model.customer;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Customer {
    private Integer id;
    private String customerTitle;
    private String customerContent;
    private Integer employeeId;
    private String employeeName;
    private Timestamp createdAt;

    public Customer(Integer id, String customerTitle, String customerContent, Integer employeeId, String employeeName,
            Timestamp createdAt) {
        this.id = id;
        this.customerTitle = customerTitle;
        this.customerContent = customerContent;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.createdAt = createdAt;
    }

}
