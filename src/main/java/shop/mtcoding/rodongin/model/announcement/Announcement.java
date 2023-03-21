package shop.mtcoding.rodongin.model.announcement;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Announcement {
    private Integer id;
    private Integer companyId;
    private Integer stackId;
    private String announcementTitle;
    private String announcementContent;
    private String announcementCarrer;
    private String announcementHireType;
    private String announcementRecNum;
    private String announcementSalary;
    private String announcementArea;
    private Timestamp createdAt;

    public Announcement(Integer companyId, Integer stackId, String announcementTitle, String announcementContent,
            String announcementCarrer, String announcementHireType, String announcementRecNum,
            String announcementSalary, String announcementArea) {
        this.companyId = companyId;
        this.stackId = stackId;
        this.announcementTitle = announcementTitle;
        this.announcementContent = announcementContent;
        this.announcementCarrer = announcementCarrer;
        this.announcementHireType = announcementHireType;
        this.announcementRecNum = announcementRecNum;
        this.announcementSalary = announcementSalary;
        this.announcementArea = announcementArea;
    }
}
