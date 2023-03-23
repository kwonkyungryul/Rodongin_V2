package shop.mtcoding.rodongin.dto.announcement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class AnnouncementListDto {
    private Integer id;
    private StackDto stackDto;
    private CompanyDto companyDto;
    private String announcementTitle;
    private String announcementContent;
    private String announcementCarrer;
    private String announcementHireType;
    private Integer announcementRecNum;
    private String announcementSalary;
    private String announcementArea;
    private Timestamp createdAt;

    @Getter @Setter @NoArgsConstructor
    public static class CompanyDto {
        private Integer id;
        private String companyFullname;
    }

    @Getter @Setter @NoArgsConstructor
    public static class StackDto {
        private Integer id;
        private String stackName;
    }
}
