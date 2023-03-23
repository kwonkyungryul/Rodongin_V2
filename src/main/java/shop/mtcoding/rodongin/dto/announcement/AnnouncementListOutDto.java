package shop.mtcoding.rodongin.dto.announcement;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class AnnouncementListOutDto {
    private String content;
    private List<AnnouncementListDto> announcementListDto;
    private AnnouncementPagingDto announcementPagingDto;

    @Getter @Setter @NoArgsConstructor
    public static class AnnouncementPagingDto {
        private Integer pageNum;
        private Integer startPageNum;
        private Integer endPageNum;
        private Integer select;
        private Integer num;
        private Integer start;
        private Integer end;
        private boolean prev;
        private boolean next;

        @Builder
        public AnnouncementPagingDto(Integer pageNum, Integer startPageNum, Integer endPageNum, Integer select, Integer num, Integer start, Integer end, boolean prev, boolean next) {
            this.pageNum = pageNum;
            this.startPageNum = startPageNum;
            this.endPageNum = endPageNum;
            this.select = select;
            this.num = num;
            this.start = start;
            this.end = end;
            this.prev = prev;
            this.next = next;
        }
    }

    @Builder
    public AnnouncementListOutDto(List<AnnouncementListDto> announcementListDto, String content, AnnouncementPagingDto announcementPagingDto) {
        this.announcementListDto = announcementListDto;
        this.content = content;
        this.announcementPagingDto = announcementPagingDto;
    }
}
