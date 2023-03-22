package shop.mtcoding.rodongin.dto.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.rodongin.dto.resume.ResumeDto;

import java.sql.Date;
import java.util.List;

@Getter @Setter
public class EmployeeDetailOutDto {
    private Integer id;
    private String employeeFullname;
    private String employeeName;
    private String employeeAddress;
    private Date employeeBirth;
    private String employeeEmail;
    private String employeeTel;
    private List<EmployeeGraduateDto> employeeGraduateDtos;
    private List<EmployeeCareerDto> employeeCareerDtos;
    private List<EmployeeLicenseDto> employeeLicenseDtos;
    private List<EmployeeStackDto> employeeStackDtos;
    private List<ResumeDto> resumeDtos;

    @Builder
    public EmployeeDetailOutDto(Integer id, String employeeFullname, String employeeName, String employeeAddress, Date employeeBirth, String employeeEmail, String employeeTel, List<EmployeeGraduateDto> employeeGraduateDtos, List<EmployeeCareerDto> employeeCareerDtos, List<EmployeeLicenseDto> employeeLicenseDtos, List<EmployeeStackDto> employeeStackDtos, List<ResumeDto> resumeDtos) {
        this.id = id;
        this.employeeFullname = employeeFullname;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.employeeBirth = employeeBirth;
        this.employeeEmail = employeeEmail;
        this.employeeTel = employeeTel;
        this.employeeGraduateDtos = employeeGraduateDtos;
        this.employeeCareerDtos = employeeCareerDtos;
        this.employeeLicenseDtos = employeeLicenseDtos;
        this.employeeStackDtos = employeeStackDtos;
        this.resumeDtos = resumeDtos;
    }
}
