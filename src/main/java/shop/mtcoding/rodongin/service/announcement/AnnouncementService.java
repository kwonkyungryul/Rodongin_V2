package shop.mtcoding.rodongin.service.announcement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementDetailOutDto;
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
}