package shop.mtcoding.rodongin.dto.subscribe;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscribeSaveInDto {
    private Integer announcementId;

    @Builder
    public SubscribeSaveInDto(Integer announcementId) {
        this.announcementId = announcementId;
    }

}
