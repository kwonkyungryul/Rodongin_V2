package shop.mtcoding.rodongin.service.announcement;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementDetailOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementSaveInDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementUpdateInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.announcement.Announcement;
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

    public void 게시글수정(int comPrincipalId, int id, AnnouncementUpdateInDto announcementUpdateInDto) {
        Announcement announcementPS = announcementRepository.findById(id);
        if (announcementPS == null) {
            throw new CustomApiException("해당 게시글을 찾을 수 없습니다.");
        }
        if (announcementPS.getCompanyId() != comPrincipalId) {
            throw new CustomApiException("게시글을 수정할 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        int result = announcementRepository.updateById(
                announcementUpdateInDto, id);

        if (result != 1) {

            throw new CustomApiException("게시글 수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void 게시글삭제(int comPrincipalId, int id) {
        Announcement announcementPS = announcementRepository.findById(id);
        if (announcementPS == null) {
            throw new CustomApiException("없는 게시글을 삭제할 수 없습니다.");
        }
        if (announcementPS.getCompanyId() != comPrincipalId) {
            throw new CustomApiException("삭제 권한이 없습니다.");
        }

        try {
            announcementRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomApiException("게시글 삭제에 실패하였습니다.");
        }
    }
}