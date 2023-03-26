package shop.mtcoding.rodongin.model.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.employee.EmployeeDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeJoinInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeLoginInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeUpdateInDto;

@Mapper
public interface EmployeeRepository {
    public Employee findByEmployeeNameAndPassword(EmployeeLoginInDto employeeLoginInDto);

    public int insert(EmployeeJoinInDto employeeJoinInDto);

    public Employee findByEmployeeName(String employeeName);

    public List<Employee> findAll();

    public Employee findById(int id);

    public EmployeeDto findByIdApply(int id);

    // public int updateById(@Param("id") int id, @Param("employeeUpdatdReq")
    // EmployeeUpdatdReq employeeUpdatdReq,
    // @Param("employeeThumbnail") String employeeThumbnail);

    public int deleteById(int id);

    public int updateById(@Param("id") int id, @Param("employeeUpdateInDto") EmployeeUpdateInDto employeeUpdateInDto);
}
