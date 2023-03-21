package shop.mtcoding.rodongin.model.employee;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.employee.EmployeeReq.EmployeeJoinReqDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeReq.EmployeeLoginReqDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeReq.EmployeeUpdatdReq;

@Mapper
public interface EmployeeRepository {

    public Employee findByEmployeeName(String employeeName);

    public Employee findByEmployeeNameAndPassword(EmployeeLoginReqDto employeeLoginReqDto);

    public List<Employee> findAll();

    public Employee findById(int id);

    public int insert(EmployeeJoinReqDto employeejoinReqDto);
    
    public int updateById(@Param("id") int id, @Param("employeeUpdatdReq") EmployeeUpdatdReq employeeUpdatdReq,
            @Param("employeeThumbnail") String employeeThumbnail);

    public int deleteById(int id);
}
