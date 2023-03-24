package shop.mtcoding.rodongin.model.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.employee.EmployeeResp.StackRespDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeSaveInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeStackDto;

@Mapper
public interface EmployeeStackRepository {
        public int insert(@Param("employeeStack") EmployeeStack employeeStack,
                        @Param("employeeId") int employeeId);

        public List<StackRespDto> findById(int employeeId);

        public List<EmployeeStackDto> findByEmpId(Integer employeeId);

        public int insert(@Param("employeeId") Integer employeeId,
                        @Param("employeeSaveInDto") EmployeeSaveInDto employeeSaveInDto);
}
