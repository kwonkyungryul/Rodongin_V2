package shop.mtcoding.rodongin.service.announcement;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementDetailOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementSaveInDto;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.announcement.AnnouncementRepository;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

    public final AnnouncementRepository announcementRepository;

    @Transactional
    public AnnouncementDetailOutDto 공고상세보기(Integer id) {
        AnnouncementDetailOutDto detailDto = announcementRepository.findByIdJoinCompanyAndStack(id);
        return detailDto;
    }

    @Transactional
    public void 공고등록(int comPrincipalId, AnnouncementSaveInDto announcementSaveInDto) {
        announcementRepository.insert(comPrincipalId, announcementSaveInDto);
        try {

        } catch (Exception e) {
            throw new CustomException("일시적인 서버 에러입니다.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}