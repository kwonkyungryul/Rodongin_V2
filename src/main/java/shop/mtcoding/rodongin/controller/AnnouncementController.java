package shop.mtcoding.rodongin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementDetailOutDto;
import shop.mtcoding.rodongin.service.announcement.AnnouncementService;

@RequiredArgsConstructor
@RestController
public class AnnouncementController {

    public final AnnouncementService announcementService;

    @GetMapping("announcement/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        AnnouncementDetailOutDto datailDto = announcementService.공고상세보기(id);
        return new ResponseEntity<>(new ResponseDto<>(1, "공고상세보기페이지", datailDto), HttpStatus.OK);
    }
}
