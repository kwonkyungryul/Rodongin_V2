package shop.mtcoding.rodongin.dto.announcement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AnnouncementUpdateInDto {
    private Integer stackId;
    private String announcementTitle;
    private String announcementContent;
    private String announcementCarrer;
    private String announcementHireType;
    private Integer announcementRecNum;
    private String announcementSalary;
    private String announcementArea;

    @Builder
    public AnnouncementUpdateInDto(Integer stackId, String announcementTitle, String announcementContent,
            String announcementCarrer, String announcementHireType, Integer announcementRecNum,
            String announcementSalary, String announcementArea) {
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
