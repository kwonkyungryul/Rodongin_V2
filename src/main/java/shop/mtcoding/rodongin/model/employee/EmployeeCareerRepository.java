package shop.mtcoding.rodongin.model.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.mtcoding.rodongin.dto.employee.EmployeeCareerDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeGraduateDto;

@Mapper
public interface EmployeeCareerRepository {
    public int insert(@Param("employeeCareer") EmployeeCareer employeeCareer,
            @Param("employeeId") int employeeId);

    public List<EmployeeCareer> findById(int employeeId);

    public List<EmployeeCareerDto> findByEmpId(int employeeId);

   
}
