package shop.mtcoding.rodongin.service.subscribe;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.subscribe.SubscribeSaveInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.model.subscribe.SubscribeRepository;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    private final HttpSession session;

    public void 구독하기(SubscribeSaveInDto subscribeSaveInDto) {

        Employee principal = (Employee) session.getAttribute("principal");

        try {
            subscribeRepository.insert(principal.getId(), subscribeSaveInDto);

        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void 구독취소(Integer announcementId) {

        Employee principal = (Employee) session.getAttribute("principal");

        try {
            subscribeRepository.deleteByEmployeeIdAndAnnouncementId(principal.getId(),
                    announcementId);

        } catch (Exception e) {
            throw new CustomApiException("일시적인 서버 에러입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
