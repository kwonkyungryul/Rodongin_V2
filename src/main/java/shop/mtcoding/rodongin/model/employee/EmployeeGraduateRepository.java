package shop.mtcoding.rodongin.model.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.employee.EmployeeResp.GraduateRespDto;


@Mapper
public interface EmployeeGraduateRepository {

    public int insert(@Param("employeeGraduate") EmployeeGraduate employeeGraduate,
            @Param("employeeId") int employeeId);

    public List<GraduateRespDto> findById(int employeeId);
    // public GraduateResp findById(int employeeId);

}
