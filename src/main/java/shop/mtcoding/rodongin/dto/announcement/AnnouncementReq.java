package shop.mtcoding.rodongin.dto.announcement;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class AnnouncementReq {
    @Getter
    @Setter
    public static class AnnouncementSaveReqDto {
        private Integer stackId;
        private String announcementTitle;
        private String announcementContent;
        private String announcementCarrer;
        private String announcementHireType;
        private int announcementRecNum;
        private String announcementSalary;
        private String announcementArea;
    }

    @Getter
    @Setter
    public static class AnnouncementUpdateReqDto {
        private Integer stackId;
        private String announcementTitle;
        private String announcementContent;
        private String announcementCarrer;
        private String announcementHireType;
        private Integer announcementRecNum;
        private String announcementSalary;
        private String announcementArea;
    }

    

    }




