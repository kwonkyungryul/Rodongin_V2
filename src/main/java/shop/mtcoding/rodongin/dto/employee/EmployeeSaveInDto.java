package shop.mtcoding.rodongin.dto.employee;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeSaveInDto {
    private Integer schoolId;
    private String schoolGraduate;
    private String careerCompany;
    private Date careerStart;
    private Date careerEnd;
    private Integer licenseId;
    private String licenseIssuer;
    private Integer stackId;
    private String stackAcquisition;
    private Timestamp createdAt;

    @Builder
    public EmployeeSaveInDto(Integer schoolId, String schoolGraduate, String careerCompany,
            Date careerStart, Date careerEnd, Integer licenseId, String licenseIssuer, Integer stackId,
            String stackAcquisition, Timestamp createdAt) {
        this.schoolId = schoolId;
        this.schoolGraduate = schoolGraduate;
        this.careerCompany = careerCompany;
        this.careerStart = careerStart;
        this.careerEnd = careerEnd;
        this.licenseId = licenseId;
        this.licenseIssuer = licenseIssuer;
        this.stackId = stackId;
        this.stackAcquisition = stackAcquisition;
        this.createdAt = createdAt;
    }

}
