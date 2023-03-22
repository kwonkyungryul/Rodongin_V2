package shop.mtcoding.rodongin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementDetailOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementSaveInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.service.announcement.AnnouncementService;
import shop.mtcoding.rodongin.util.MySession;

@RequiredArgsConstructor
@RestController
public class AnnouncementController {

    private final AnnouncementService announcementService;
    private final HttpSession session;

    @PostMapping("/announcement")
    public ResponseEntity<?> save(@RequestBody AnnouncementSaveInDto announcementSaveInDto) {
        System.out.println(announcementSaveInDto.getStackId());
        // Company comPrincipal = (Company) session.getAttribute("comPrincipal");

        Company comPrincipal = MySession.CompanyPrincipal(session);

        if (comPrincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        if (announcementSaveInDto.getStackId() == null
                || announcementSaveInDto.getStackId() == 0) {
            throw new CustomApiException("기술선택을 선택해주세요");
        }
        if (announcementSaveInDto.getAnnouncementTitle() == null
                || announcementSaveInDto.getAnnouncementTitle().isEmpty()) {
            throw new CustomApiException("title을 작성해주세요");
        }
        if (announcementSaveInDto.getAnnouncementContent() == null
                || announcementSaveInDto.getAnnouncementContent().isEmpty()) {
            throw new CustomApiException("Content을 작성해주세요");
        }
        if (announcementSaveInDto.getAnnouncementCarrer() == null
                || announcementSaveInDto.getAnnouncementCarrer().isEmpty()) {
            throw new CustomApiException("Carrer을 작성해주세요");
        }
        if (announcementSaveInDto.getAnnouncementHireType() == null
                || announcementSaveInDto.getAnnouncementHireType().isEmpty()) {
            throw new CustomApiException("HireType을 작성해주세요");
        }
        if (announcementSaveInDto.getAnnouncementSalary() == null
                || announcementSaveInDto.getAnnouncementSalary().isEmpty()) {
            throw new CustomApiException("Salary을 작성해주세요");
        }
        if (announcementSaveInDto.getAnnouncementArea() == null
                || announcementSaveInDto.getAnnouncementArea().isEmpty()) {
            throw new CustomApiException("Area을 작성해주세요");
        }

        announcementService.공고등록(comPrincipal.getId(), announcementSaveInDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글작성 성공", null), HttpStatus.CREATED);
    }

    @GetMapping("announcement/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        AnnouncementDetailOutDto datailDto = announcementService.공고상세보기(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "공고상세보기페이지", datailDto), HttpStatus.OK);
    }
}
