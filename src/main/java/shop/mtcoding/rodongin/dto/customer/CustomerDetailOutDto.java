package shop.mtcoding.rodongin.dto.customer;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter // updateDto - findCustomerDetail
public class CustomerDetailOutDto {

  private Integer id;
  private String customerTitle;
  private String customerContent;
  private Integer employeeId;
  private String employeeName;
  private Timestamp createdAt;

}
