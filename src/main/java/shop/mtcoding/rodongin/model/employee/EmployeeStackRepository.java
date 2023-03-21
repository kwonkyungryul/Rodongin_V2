package shop.mtcoding.rodongin.model.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.employee.EmployeeResp.StackRespDto;

@Mapper
public interface EmployeeStackRepository {
    public int insert(@Param("employeeStack") EmployeeStack employeeStack,
            @Param("employeeId") int employeeId);

    public List<StackRespDto> findById(int employeeId);

    public List<EmployeeStack> findByEmployeeId(Integer employeeId);


}
