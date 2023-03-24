package shop.mtcoding.rodongin.model.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.employee.EmployeeGraduateDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeSaveInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeResp.GraduateRespDto;

@Mapper
public interface EmployeeGraduateRepository {

    public List<GraduateRespDto> findById(int employeeId);

    public List<EmployeeGraduateDto> findByEmpId(int employeeId);

    public int insert(@Param("employeeId") Integer employeeId,
            @Param("employeeSaveInDto") EmployeeSaveInDto employeeSaveInDto);

}
