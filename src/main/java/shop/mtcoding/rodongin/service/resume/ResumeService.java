package shop.mtcoding.rodongin.service.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.rodongin.dto.resume.*;
import shop.mtcoding.rodongin.handler.ex.CustomApiException;
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

    @Transactional
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

    @Transactional
    public void 이력서등록(int principalId, ResumeSaveInDto resumeSaveInDto) {

        try {
            resumeRepository.insert(principalId, resumeSaveInDto);
            if (resumeSaveInDto.getSchoolId() != null) {
                resumeGraduateRepository.insert(resumeSaveInDto.getId(), resumeSaveInDto.getSchoolId(),
                        resumeSaveInDto.getSchoolGraduate());
            }

            if (resumeSaveInDto.getCareerCompany() != null) {
                resumeCareerRepository.insert(resumeSaveInDto.getId(), resumeSaveInDto.getCareerCompany(),
                        resumeSaveInDto.getCareerStart(), resumeSaveInDto.getCareerEnd());
            }

            if (resumeSaveInDto.getLicenseId() != null) {
                resumeLicenseRepository.insert(resumeSaveInDto.getId(), resumeSaveInDto.getLicenseId(),
                        resumeSaveInDto.getLicenseIssuer());
            }

            if (resumeSaveInDto.getStackId() != null) {
                resumeStackRepository.insert(resumeSaveInDto.getId(), resumeSaveInDto.getStackId(),
                        resumeSaveInDto.getStackAcquisition());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomApiException("이력서 등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
