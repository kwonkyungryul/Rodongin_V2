package shop.mtcoding.rodongin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.subscribe.SubscribeSaveInDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.service.subscribe.SubscribeService;
import shop.mtcoding.rodongin.util.MySession;

@RequiredArgsConstructor
@RestController
public class SubscribeController {

    private final HttpSession session;
    private final SubscribeService subscribeService;

    @DeleteMapping("/subscribes/{announcementId}")
    public ResponseEntity<?> delete(@PathVariable Integer announcementId) {

        // Employee principal = (Employee) session.getAttribute("principal");
        Employee principal = MySession.MyPrincipal(session);

        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        if (announcementId == null) {
            throw new CustomApiException("비정상적인 접근입니다.");
        }

        subscribeService.구독취소(principal.getId(), announcementId);
        return new ResponseEntity<>(new ResponseDto<>(1, "구독취소", null), HttpStatus.OK);
    }

    @PostMapping("/subscribes")
    public ResponseEntity<?> save(@RequestBody SubscribeSaveInDto subscribeSaveInDto) {

        // Employee principal = (Employee) session.getAttribute("principal");
        Employee principal = MySession.MyPrincipal(session);

        if (principal == null) {
            throw new CustomApiException("인증이 되지 않았습니다", HttpStatus.UNAUTHORIZED);
        }

        if (subscribeSaveInDto.getAnnouncementId() == null) {
            throw new CustomApiException("비정상적인 접근입니다.");
        }

        subscribeService.구독하기(principal.getId(), subscribeSaveInDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "구독하기 성공!", null), HttpStatus.CREATED);
    }

}
