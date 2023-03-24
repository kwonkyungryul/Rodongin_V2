package shop.mtcoding.rodongin.model.employee;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.mtcoding.rodongin.dto.employee.EmployeeLicenseDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeSaveInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeResp.LicenseRespDto;

import java.util.List;

@Mapper
public interface EmployeeLicenseRepository {
        public int insert(@Param("employeeLicense") EmployeeLicense employeeLicense,
                        @Param("employeeId") int employeeId);

        public List<LicenseRespDto> findById(int employeeId);

        public List<EmployeeLicenseDto> findByEmpId(Integer employeeId);

        public int insert(@Param("employeeId") Integer employeeId,
                        @Param("employeeSaveInDto") EmployeeSaveInDto employeeSaveInDto);
}
