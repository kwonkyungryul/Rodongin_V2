package shop.mtcoding.rodongin.dto.announcement;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnnouncementCompanyListOutDto {
    private Integer id;
    private StackDto stack;
    private String announcementTitle;
    private String announcementContent;
    private String announcementCarrer;
    private String announcementHireType;
    private Integer announcementRecNum;
    private String announcementSalary;
    private String announcementArea;
    private Timestamp createdAt;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class StackDto {
        private Integer id;
        private String stackName;
    }

}
