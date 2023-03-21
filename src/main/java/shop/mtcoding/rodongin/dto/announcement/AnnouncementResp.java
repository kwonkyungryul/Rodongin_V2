package shop.mtcoding.rodongin.dto.announcement;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class AnnouncementResp {
    @Getter
    @Setter
    public static class AnnouncementDetailRespDto {
        private Integer id;
        private Integer stackId;
        private String companyId;
        private String announcementTitle;
        private String announcementContent;
        private String announcementCarrer;
        private String announcementHireType;
        private Integer announcementRecNum;
        private String announcementSalary;
        private String announcementArea;

        private String companyUsername;
        private String companyPassword;
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
    }
}
