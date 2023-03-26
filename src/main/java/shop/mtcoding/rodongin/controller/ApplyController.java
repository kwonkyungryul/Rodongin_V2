package shop.mtcoding.rodongin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.ResponseDto;
import shop.mtcoding.rodongin.dto.apply.ApplyListOutDto;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.service.apply.ApplyService;
import shop.mtcoding.rodongin.util.MySession;

@RequiredArgsConstructor
@RestController
@Tag(name = "3. 지원자 정보관리", description = "지원자 정보관리")
public class ApplyController {

    private final HttpSession session;
    private final ApplyService applyService;

    @GetMapping("/s/apply/{announcementId}")
    @Operation(summary = "1. 지원자 리스트 보기", description = "지원자 리스트를 조회합니다.")
    public ResponseEntity<?> applyList(@PathVariable int announcementId) {

        List<ApplyListOutDto> listDto = applyService.지원자목록보기(announcementId);
        return new ResponseEntity<>(new ResponseDto<>(1, "지원자 목록 보기", listDto), HttpStatus.OK);

    }
}
