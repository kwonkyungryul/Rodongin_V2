package shop.mtcoding.rodongin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.subscribe.SubscribeSaveInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.service.subscribe.SubscribeService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@Tag(name = "4. 구독", description = "구독 및 구독취소")
public class SubscribeController {

    private final HttpSession session;
    private final SubscribeService subscribeService;

    @DeleteMapping("/s/subscribes/{announcementId}")
    @Operation(summary = "2. 구독취소", description = "공고를 구독을 취소합니다.")
    public ResponseEntity<?> delete(@PathVariable Integer announcementId) {

        if (announcementId == null) {
            throw new CustomApiException("비정상적인 접근입니다.");
        }

        subscribeService.구독취소(announcementId);
        return new ResponseEntity<>(new ResponseDto<>(1, "구독취소", null), HttpStatus.OK);
    }

    @PostMapping("/s/subscribes")
    @Operation(summary = "1. 구독하기", description = "공고를 구독합니다.")
    public ResponseEntity<?> save(@RequestBody SubscribeSaveInDto subscribeSaveInDto) {

        if (subscribeSaveInDto.getAnnouncementId() == null) {
            throw new CustomApiException("비정상적인 접근입니다.");
        }

        subscribeService.구독하기(subscribeSaveInDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "구독하기 성공!", null), HttpStatus.CREATED);
    }

}
