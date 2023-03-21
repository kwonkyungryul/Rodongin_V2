package shop.mtcoding.rodongin.dto.resume;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

public class ResumeResp {

    @Getter
    @Setter
    public static class ResumeListRespDto {
        private Integer id;
        private Integer resumeId;
        private Integer employeeId;
        private String resumeTitle;
        private String resumeSalary;
        private Integer schoolId;
        private String schoolGradute;
        private String schoolName;
        private String careerCompany;
        private Date careerStart;
        private Date careerEnd;
        private String licenseName;
        private String licenseIssuer;
        private String stackName;
        private String stackAcquisition;
    }

    @Getter
    @Setter
    public static class ResumeGraduateRespDto {
        private Integer resumeId;
        private Integer schoolId;
        private String schoolName;
        private String schoolGraduate;
    }

    @Getter
    @Setter
    public static class ResumeLicenseRespDto {
        private Integer resumeId;
        private Integer licenseId;
        private String licenseName;
        private String licenseIssuer;
    }

    @Getter
    @Setter
    public static class ResumeStackRespDto {
        private Integer resumeId;;
        private Integer stackId;
        private String stackName;
        private String stackAcquisition;
    }

}
