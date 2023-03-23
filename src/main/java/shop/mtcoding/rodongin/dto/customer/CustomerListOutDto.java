package shop.mtcoding.rodongin.dto.customer;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerListOutDto {
    // listDto - findCustomerList
    private Integer id;
    private String customerTitle;
    private Integer employeeId;
    private String employeeName;
    private Timestamp createdAt;

}
