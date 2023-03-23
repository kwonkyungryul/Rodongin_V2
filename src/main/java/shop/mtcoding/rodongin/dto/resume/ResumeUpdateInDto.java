package shop.mtcoding.rodongin.dto.resume;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter @NoArgsConstructor
public class ResumeUpdateInDto {
    private String resumeTitle;

    private Integer schoolId;
    private String schoolGraduate;

    private String careerCompany;
    private Date careerStart;
    private Date careerEnd;

    private Integer licenseId;
    private String licenseIssuer;

    private Integer stackId;
    private String stackAcquisition;

    private String resumeSalary;
    private String CV;

    @Builder
    public ResumeUpdateInDto(String resumeTitle, Integer schoolId, String schoolGraduate, String careerCompany, Date careerStart, Date careerEnd, Integer licenseId, String licenseIssuer, Integer stackId, String stackAcquisition, String resumeSalary, String CV) {
        this.resumeTitle = resumeTitle;
        this.schoolId = schoolId;
        this.schoolGraduate = schoolGraduate;
        this.careerCompany = careerCompany;
        this.careerStart = careerStart;
        this.careerEnd = careerEnd;
        this.licenseId = licenseId;
        this.licenseIssuer = licenseIssuer;
        this.stackId = stackId;
        this.stackAcquisition = stackAcquisition;
        this.resumeSalary = resumeSalary;
        this.CV = CV;
    }
}
