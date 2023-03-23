package shop.mtcoding.rodongin.dto.resume;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class ResumeDetailOutDto {
    private Integer id;
    private String resumeTitle;
    private EmployeeDto employeeDto;
    private List<ResumeGraduateDto> resumeGraduateDtoList;
    private List<ResumeCareerDto> resumeCareerDtoList;
    private List<ResumeLicenseDto> resumeLicenseDtoList;
    private List<ResumeStackDto> resumeStackDtoList;
    private String resumeSalary;
    private String CV;

    @Getter @Setter @NoArgsConstructor
    public static class EmployeeDto {
        private Integer id;
        private String employeeFullname;
        private String employeeEmail;
        private String employeeTel;
        private Date employeeBirth;
        private String employeeAddress;
        private String employeeThumbnail;
    }

    @Builder
    public ResumeDetailOutDto(Integer id, String resumeTitle, EmployeeDto employeeDto, List<ResumeGraduateDto> resumeGraduateDtoList, List<ResumeCareerDto> resumeCareerDtoList, List<ResumeLicenseDto> resumeLicenseDtoList, List<ResumeStackDto> resumeStackDtoList, String resumeSalary, String CV) {
        this.id = id;
        this.resumeTitle = resumeTitle;
        this.employeeDto = employeeDto;
        this.resumeGraduateDtoList = resumeGraduateDtoList;
        this.resumeCareerDtoList = resumeCareerDtoList;
        this.resumeLicenseDtoList = resumeLicenseDtoList;
        this.resumeStackDtoList = resumeStackDtoList;
        this.resumeSalary = resumeSalary;
        this.CV = CV;
    }
}
