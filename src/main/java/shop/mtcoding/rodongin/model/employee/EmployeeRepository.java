package shop.mtcoding.rodongin.model.employee;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.mtcoding.rodongin.dto.employee.EmployeeJoinInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeLoginInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeUpdateInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeReq.EmployeeUpdatdReq;

import java.util.List;

@Mapper
public interface EmployeeRepository {
    public Employee findByEmployeeNameAndPassword(EmployeeLoginInDto employeeLoginInDto);

    public int insert(EmployeeJoinInDto employeeJoinInDto);

    public Employee findByEmployeeName(String employeeName);

    public List<Employee> findAll();

    public Employee findById(int id);

    // public int updateById(@Param("id") int id, @Param("employeeUpdatdReq")
    // EmployeeUpdatdReq employeeUpdatdReq,
    // @Param("employeeThumbnail") String employeeThumbnail);

    public int deleteById(int id);

    public int updateById(@Param("id") int id, @Param("employeeUpdateInDto") EmployeeUpdateInDto employeeUpdateInDto);
}
