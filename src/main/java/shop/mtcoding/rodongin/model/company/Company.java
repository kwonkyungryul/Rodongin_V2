package shop.mtcoding.rodongin.model.company;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Company {
    private Integer id;
    private String companyUsername;
    private String companyPassword;
    private String companyRole;
    private String companyFullname;
    private String companyCeoName;
    private String companyLicenseNumber;
    private String companyTel;
    private String companyAddress;
    private String companyEmail;

    private String companyThumbnail;
    private Date companyEstablish;
    private Long companySales;
    private Integer companyEmployeesNumber;
    private String companyIntroduction;
    private String companyHistory;
    private String companyVision;
    private Timestamp createdAt;

    @Builder
    public Company(Integer id, String companyUsername, String companyPassword, String companyRole, String companyFullname, String companyCeoName, String companyLicenseNumber, String companyTel, String companyAddress, String companyEmail, String companyThumbnail, Date companyEstablish, Long companySales, Integer companyEmployeesNumber, String companyIntroduction, String companyHistory, String companyVision, Timestamp createdAt) {
        this.id = id;
        this.companyUsername = companyUsername;
        this.companyPassword = companyPassword;
        this.companyRole = companyRole;
        this.companyFullname = companyFullname;
        this.companyCeoName = companyCeoName;
        this.companyLicenseNumber = companyLicenseNumber;
        this.companyTel = companyTel;
        this.companyAddress = companyAddress;
        this.companyEmail = companyEmail;
        this.companyThumbnail = companyThumbnail;
        this.companyEstablish = companyEstablish;
        this.companySales = companySales;
        this.companyEmployeesNumber = companyEmployeesNumber;
        this.companyIntroduction = companyIntroduction;
        this.companyHistory = companyHistory;
        this.companyVision = companyVision;
        this.createdAt = createdAt;
    }
}
