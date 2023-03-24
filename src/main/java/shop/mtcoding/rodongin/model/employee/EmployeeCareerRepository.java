package shop.mtcoding.rodongin.model.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.mtcoding.rodongin.dto.employee.EmployeeCareerDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeGraduateDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeSaveInDto;

@Mapper
public interface EmployeeCareerRepository {
    public int insert(@Param("employeeId") Integer employeeId,
            @Param("employeeSaveInDto") EmployeeSaveInDto employeeSaveInDto);

    public List<EmployeeCareer> findById(int employeeId);

    public List<EmployeeCareerDto> findByEmpId(int employeeId);

}
