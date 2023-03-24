package shop.mtcoding.rodongin.dto.customer;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor // updateDto - findCustomerDetail
public class CustomerListOutDto {

  private Integer id;
  private String customerTitle;
  private EmployeeDto employee;
  private Timestamp createdAt;

  @Setter
  @Getter
  @NoArgsConstructor
  public static class EmployeeDto {
    private Integer id;
    private String employeeName;

    @Builder
    public EmployeeDto(Integer id, String employeeName) {
      this.id = id;
      this.employeeName = employeeName;
    }
  }

  @Builder
  public CustomerListOutDto(Integer id, String customerTitle, EmployeeDto employee, Timestamp createdAt) {
    this.id = id;
    this.customerTitle = customerTitle;
    this.employee = employee;
    this.createdAt = createdAt;
  }
}
