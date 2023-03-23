package shop.mtcoding.rodongin.service.announcement;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementCompanyListOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementDetailOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementListDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementListOutDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementResp;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementSaveInDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementUpdateInDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeStackDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.handler.ex.CustomException;
import shop.mtcoding.rodongin.model.announcement.Announcement;
import shop.mtcoding.rodongin.model.announcement.AnnouncementRepository;
import shop.mtcoding.rodongin.model.employee.Employee;
import shop.mtcoding.rodongin.model.employee.EmployeeStackRepository;
import shop.mtcoding.rodongin.model.master.StackMaster;
import shop.mtcoding.rodongin.model.master.StackMasterRepository;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    private final EmployeeStackRepository employeeStackRepository;

    private final StackMasterRepository stackMasterRepository;

    private final HttpSession session;

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

    public AnnouncementListOutDto 공고리스트보기(int num, String content) {
        System.out.println(num);
        Employee principal = (Employee) session.getAttribute("principal");

        List<String> skills = new ArrayList<>();
        List<AnnouncementResp.AnnouncementDetailRespDto> announcementDetailDto;

        int cnt;
        if (principal != null) { // 로그인이 되어 있을 때
            List<EmployeeStackDto> stacks = employeeStackRepository.findByEmpId(principal.getId());
            for (int i = 0; i < stacks.size(); i++) {
                skills.add(stacks.get(i).getStackId().toString());
            }
            cnt = announcementRepository.findAnnouncementCount(skills, content);
        } else { // 비로그인 일 때
            List<StackMaster> stackMasters = stackMasterRepository.findAll();
            for (int i = 0; i < stackMasters.size(); i++) {
                skills.add(stackMasters.get(i).getId().toString());
            }
            cnt = announcementRepository.findAnnouncementCount(skills, content);
        }

        System.out.println(cnt);

        int end = 10; // 한 페이지에 보여줄 게시물 수
        int pageNum = (int) Math.ceil((double) cnt / end); // 페이지 번호

        int start = (num - 1) * end; // 0에서 부터 10개 자르기

        int pageNum_cnt = 10; // 페이지 개수 번호를 10개씩만 출력

        int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);

        int startPageNum = endPageNum - (pageNum_cnt - 1);

        int lastPageNum = (int) (Math.ceil((double) cnt / (double) pageNum_cnt));

        if (endPageNum > lastPageNum) {
            endPageNum = lastPageNum;
        }

        boolean prev = startPageNum == 1 ? false : true;
        boolean next = endPageNum * pageNum_cnt >= cnt ? false : true;

        List<AnnouncementListDto> announcementlist = announcementRepository.findAnnouncementlist(skills, content, start,
                end);
        AnnouncementListOutDto.AnnouncementPagingDto announcementPagingDto = AnnouncementListOutDto.AnnouncementPagingDto
                .builder()
                .num(num)
                .start(start)
                .end(end)
                .select(num)
                .startPageNum(startPageNum)
                .endPageNum(endPageNum)
                .pageNum(pageNum)
                .next(next)
                .prev(prev).build();

        AnnouncementListOutDto announcementListOutDto = AnnouncementListOutDto.builder()
                .announcementListDto(announcementlist)
                .content(content)
                .announcementPagingDto(announcementPagingDto).build();
        // announcementListOutDto.getAnnouncementPagingDto().setNum(num);
        // announcementListOutDto.getAnnouncementPagingDto().setStart(start);
        // announcementListOutDto.getAnnouncementPagingDto().setEnd(end);
        // announcementListOutDto.getAnnouncementPagingDto().setSelect(num);
        // announcementListOutDto.getAnnouncementPagingDto().setStartPageNum(startPageNum);
        // announcementListOutDto.getAnnouncementPagingDto().setEndPageNum(endPageNum);
        // announcementListOutDto.getAnnouncementPagingDto().setPageNum(pageNum);
        // announcementListOutDto.getAnnouncementPagingDto().setPrev(prev);
        // announcementListOutDto.getAnnouncementPagingDto().setNext(next);

        return announcementListOutDto;
    }

    public List<AnnouncementCompanyListOutDto> 우리회사공고리스트(Integer comPrincipalId) {

        List<AnnouncementCompanyListOutDto> announcementCompanyListOutDto = announcementRepository.findCompanyId(
                comPrincipalId);

        return announcementCompanyListOutDto;
    }
}