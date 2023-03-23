package shop.mtcoding.rodongin.dto.customer;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.rodongin.model.employee.Employee;

@Setter
@Getter // updateDto - findCustomerDetail
public class CustomerDetailOutDto {

  private Integer id;
  private String customerTitle;
  private String customerContent;
  private EmployeeDto employee;
  private Timestamp createdAt;

  @Setter
  @Getter
  public static class EmployeeDto {
    private Integer id;
    private String employeeName;

  }

}
