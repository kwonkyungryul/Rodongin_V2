package shop.mtcoding.rodongin.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementDetailOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementListOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementSaveInDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementUpdateInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.service.announcement.AnnouncementService;
import shop.mtcoding.rodongin.util.MySession;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AnnouncementController {

    private final AnnouncementService announcementService;

    private final HttpSession session;

    @DeleteMapping("/announcements/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        // Company comPrincipal = (Company) session.getAttribute("comPrincipal");
        Company comPrincipal = MySession.CompanyPrincipal(session);
        if (comPrincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다.");
        }
        announcementService.게시글삭제(comPrincipal.getId(), id);

        return new ResponseEntity<>(new ResponseDto<>(1, "삭제성공", null), HttpStatus.OK);
    }

    // 게시글 수정
    @PutMapping("/announcements/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
            @RequestBody AnnouncementUpdateInDto announcementUpdateInDto, HttpServletResponse response) {

        // Company principal = (Company) session.getAttribute("comPrincipal");

        Company comPrincipal = MySession.CompanyPrincipal(session);

        if (comPrincipal == null) {
            throw new CustomApiException("인증이 되지 않았습니다.", HttpStatus.UNAUTHORIZED);
        }

        if (announcementUpdateInDto.getAnnouncementTitle() == null
                || announcementUpdateInDto.getAnnouncementTitle().isEmpty()) {
            throw new CustomApiException("title을 작성해주세요");
        }

        if (announcementUpdateInDto.getStackId() == null
                || announcementUpdateInDto.getStackId() == 0) {
            throw new CustomApiException("기술선택을 선택해주세요");
        }

        if (announcementUpdateInDto.getAnnouncementContent() == null
                || announcementUpdateInDto.getAnnouncementContent().isEmpty()) {
            throw new CustomApiException("Content을 작성해주세요");
        }

        if (announcementUpdateInDto.getAnnouncementCarrer() == null
                || announcementUpdateInDto.getAnnouncementCarrer().isEmpty()) {
            throw new CustomApiException("Carrer을 작성해주세요");
        }

        if (announcementUpdateInDto.getAnnouncementHireType() == null
                || announcementUpdateInDto.getAnnouncementHireType().isEmpty()) {
            throw new CustomApiException("HireType을 작성해주세요");
        }

        if (announcementUpdateInDto.getAnnouncementSalary() == null
                || announcementUpdateInDto.getAnnouncementSalary().isEmpty()) {
            throw new CustomApiException("Salary을 작성해주세요");
        }

        if (announcementUpdateInDto.getAnnouncementArea() == null
                || announcementUpdateInDto.getAnnouncementArea().isEmpty()) {
            throw new CustomApiException("Area을 작성해주세요");
        }

        announcementService.게시글수정(comPrincipal.getId(), id, announcementUpdateInDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "게시글수정성공", null), HttpStatus.OK);
    }

    @PostMapping("/announcements")
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

    @GetMapping("announcements/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        AnnouncementDetailOutDto datailDto = announcementService.공고상세보기(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "공고상세보기페이지", datailDto), HttpStatus.OK);
    }

    @GetMapping({"/announcements", "/"})
    public ResponseEntity<?> list(@RequestParam(defaultValue = "1") int num,
                                  @RequestParam(defaultValue = "") String content) {

        AnnouncementListOutDto announcementList = announcementService.공고리스트보기(num, content);

        return new ResponseEntity<>(new ResponseDto<>(1, "공고 리스트 " + num + "페이지", announcementList), HttpStatus.OK);
    }
}
