package shop.mtcoding.rodongin.dto.announcement;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnnouncementSaveInDto {

    private Integer stackId;
    private String announcementTitle;
    private String announcementContent;
    private String announcementCarrer;
    private String announcementHireType;
    private int announcementRecNum;
    private String announcementSalary;
    private String announcementArea;

}
