package shop.mtcoding.rodongin.dto.announcement;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementDetailOutDto {
    private Integer id;
    private CompanyDto company;
    private StackMasterDto stackMaster;
    private String announcementTitle;
    private String announcementContent;
    private String announcementCarrer;
    private String announcementHireType;
    private String announcementRecNum;
    private String announcementSalary;
    private String announcementArea;
    private Timestamp createdAt;

    @Getter
    @Setter
    public static class CompanyDto {
        private Integer id;
        private String companyFullname;
        private String companyCeoName;
        private String companyAddress;
        private String companyThumbnail;
        private Date companyEstablish;
        private Integer companyEmployeesNumber;
        private Timestamp createdAt;
    }

    @Getter
    @Setter
    public static class StackMasterDto {
        private Integer id;
        private String stackName;

    }
}
