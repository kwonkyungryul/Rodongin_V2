package shop.mtcoding.rodongin.service.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.rodongin.dto.resume.*;
import shop.mtcoding.rodongin.model.resume.*;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    private final ResumeGraduateRepository resumeGraduateRepository;

    private final ResumeCareerRepository resumeCareerRepository;

    private final ResumeLicenseRepository resumeLicenseRepository;

    private final ResumeStackRepository resumeStackRepository;

    public ResumeDetailOutDto 이력서상세보기(int principalId, int id) {
        ResumeDetailOutDto resumeDetailOutDto = resumeRepository.findByResumeJoinAndEmployee(id);
        List<ResumeGraduateDto> resumeGraduateDtos = resumeGraduateRepository.findByResumeGraduateJoinSchoolMaster(id);
        List<ResumeCareerDto> resumeCareerDtos = resumeCareerRepository.findByResumeId(id);
        List<ResumeLicenseDto> resumeLicenseDtos = resumeLicenseRepository.findByResumeLicenseJoinLicenseMaster(id);
        List<ResumeStackDto> resumeStackDtos = resumeStackRepository.findByResumeStackJoinStackMaster(id);

        resumeDetailOutDto.setResumeGraduateDtoList(resumeGraduateDtos);
        resumeDetailOutDto.setResumeCareerDtoList(resumeCareerDtos);
        resumeDetailOutDto.setResumeLicenseDtoList(resumeLicenseDtos);
        resumeDetailOutDto.setResumeStackDtoList(resumeStackDtos);

        return resumeDetailOutDto;
    }
}
