package shop.mtcoding.rodongin.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementCompanyListOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementDetailOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementListOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementSaveInDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementUpdateInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.service.announcement.AnnouncementService;
import shop.mtcoding.rodongin.util.MySession;

@RequiredArgsConstructor
@RestController
@Tag(name = "6. 공고 정보관리", description = "공고 정보관리")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    private final HttpSession session;

    @DeleteMapping("/s/announcements/{id}")
    @Operation(summary = "6. 공고 삭제", description = "공고 정보를 삭제합니다.")
    public ResponseEntity<?> delete(@PathVariable int id) {

        announcementService.게시글삭제(id);

        return new ResponseEntity<>(new ResponseDto<>(1, "삭제성공", null), HttpStatus.OK);
    }

    // 게시글 수정
    @PutMapping("/s/announcements/{id}")
    @Operation(summary = "5. 공고 정보수정", description = "공고 정보를 수정합니다.")
    public ResponseEntity<?> update(@PathVariable int id,
            @RequestBody AnnouncementUpdateInDto announcementUpdateInDto, HttpServletResponse response) {


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

        announcementService.게시글수정(id, announcementUpdateInDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "게시글수정성공", null), HttpStatus.OK);
    }

    @PostMapping("/s/announcements")
    @Operation(summary = "4. 공고 등록", description = "공고 정보를 등록합니다.")
    public ResponseEntity<?> save(@RequestBody AnnouncementSaveInDto announcementSaveInDto) {

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

        announcementService.공고등록(announcementSaveInDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "게시글작성 성공", null), HttpStatus.CREATED);
    }

    @GetMapping("/announcements/{id}")
    @Operation(summary = "3. 공고 상세보기", description = "공고 상세정보를 조회합니다.")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        AnnouncementDetailOutDto datailDto = announcementService.공고상세보기(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "공고상세보기페이지", datailDto), HttpStatus.OK);
    }

    @GetMapping({ "/announcements", "/" })
    @Operation(summary = "1. 공고 리스트 보기", description = "공고 리스트를 조회합니다.")
    public ResponseEntity<?> list(@RequestParam(defaultValue = "1") int num,
            @RequestParam(defaultValue = "") String content) {

        AnnouncementListOutDto announcementList = announcementService.공고리스트보기(num, content);

        return new ResponseEntity<>(new ResponseDto<>(1, "공고 리스트 " + num + "페이지",
                announcementList), HttpStatus.OK);

    }

    @GetMapping("/s/announcements/companies/{companyId}")
    @Operation(summary = "2. 공고 정보수정", description = "공고 정보를 수정합니다.")
    public ResponseEntity<?> comList(@PathVariable int companyId) {

        List<AnnouncementCompanyListOutDto> comList = announcementService.우리회사공고리스트(companyId);

        return new ResponseEntity<>(new ResponseDto<>(1, "공고 리스트 보기 성공",
                comList), HttpStatus.OK);
    }
}
