package shop.mtcoding.rodongin.dto.employee;

import lombok.Getter;
import lombok.Setter;

public class EmployeeResp {

    @Getter
    @Setter
    public static class GraduateRespDto {
        private Integer id;
        private Integer employeeId;
        private Integer schoolId;
        private String schoolName;
    }

    @Getter
    @Setter
    public static class LicenseRespDto {
        private Integer id;
        private Integer employeeId;
        private Integer licenseId;
        private String licenseName;
        private String licenseIssuer;
    }

    @Getter
    @Setter
    public static class StackRespDto {
        private Integer id;
        private Integer employeeId;
        private Integer stackId;
        private String stackName;
        private String stackAcquisition;
    }

    @Setter
    @Getter
    public static class ResumeApplyListRespDto {
        private String resumeTitle;
        private String resumeSalary;
        private String careerCompany;
    }

}
