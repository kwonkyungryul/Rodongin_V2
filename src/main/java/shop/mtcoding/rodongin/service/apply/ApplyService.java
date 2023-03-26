package shop.mtcoding.rodongin.service.apply;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.rodongin.dto.apply.ApplyListOutDto;
import shop.mtcoding.rodongin.dto.employee.EmployeeDto;
import shop.mtcoding.rodongin.dto.resume.ResumeCareerDto;
import shop.mtcoding.rodongin.dto.resume.ResumeDto;
import shop.mtcoding.rodongin.dto.resume.ResumeStackDto;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
import shop.mtcoding.rodongin.model.announcement.Announcement;
import shop.mtcoding.rodongin.model.announcement.AnnouncementRepository;
import shop.mtcoding.rodongin.model.apply.Apply;
import shop.mtcoding.rodongin.model.apply.ApplyRepository;
import shop.mtcoding.rodongin.model.company.Company;
import shop.mtcoding.rodongin.model.employee.EmployeeRepository;
import shop.mtcoding.rodongin.model.resume.ResumeCareerRepository;
import shop.mtcoding.rodongin.model.resume.ResumeRepository;
import shop.mtcoding.rodongin.model.resume.ResumeStackRepository;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class ApplyService {

    private final AnnouncementRepository announcementRepository;

    private final ApplyRepository applyRepository;

    private final ResumeRepository resumeRepository;

    private final EmployeeRepository employeeRepository;

    private final ResumeStackRepository resumeStackRepository;

    private final ResumeCareerRepository resumeCareerRepository;

    private final HttpSession session;

    public List<ApplyListOutDto> 지원자목록보기(int announcementId) {

        Company comPrincipal = (Company) session.getAttribute("comPrincipal");

        Announcement ann = announcementRepository.findById(announcementId);

        if (comPrincipal.getId().intValue() != ann.getCompanyId()) {
            throw new CustomApiException("권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        List<ApplyListOutDto> applyListOutDto = new ArrayList<>();
        List<Apply> applyList = applyRepository.findByAnnouncmentId(announcementId);

        for (Apply apply : applyList) {
            ApplyListOutDto outDto = new ApplyListOutDto();

            outDto.setResumeDto(resumeRepository.findByIdApply(apply.getResumeId()));
            outDto.setResumeStackDto(resumeStackRepository.findByIdApply(apply.getResumeId()));
            outDto.setResumeCareerDto(resumeCareerRepository.findByIdApply(apply.getResumeId()));
            outDto.setEmployeeDto(employeeRepository.findByIdApply(outDto.getResumeDto().getEmployeeId()));

            outDto.setId(apply.getId());

            applyListOutDto.add(outDto);
        }

        return applyListOutDto;
    }
}
