package shop.mtcoding.rodongin.service.subscribe;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.subscribe.SubscribeSaveInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.subscribe.SubscribeRepository;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    public void 구독하기(Integer principalId, SubscribeSaveInDto subscribeSaveInDto) {

        try {
            subscribeRepository.insert(principalId, subscribeSaveInDto);

        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void 구독취소(Integer principalId, Integer announcementId) {

        try {
            subscribeRepository.deleteByEmployeeIdAndAnnouncementId(principalId,
                    announcementId);

        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
