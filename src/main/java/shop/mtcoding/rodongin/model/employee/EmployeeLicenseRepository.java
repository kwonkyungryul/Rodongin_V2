package shop.mtcoding.rodongin.model.employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.employee.EmployeeResp.LicenseRespDto;

@Mapper
public interface EmployeeLicenseRepository {
    public int insert(@Param("employeeLicense") EmployeeLicense employeeLicense,
            @Param("employeeId") int employeeId);

    public List<LicenseRespDto> findById(int employeeId);

  

}
