package shop.mtcoding.rodongin.model.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.employee.EmployeeResp.ResumeApplyListRespDto;
import shop.mtcoding.rodongin.dto.resume.ResumeDetailOutDto;
import shop.mtcoding.rodongin.dto.resume.ResumeDto;
import shop.mtcoding.rodongin.dto.resume.ResumeReq.ResumeSaveDto;
import shop.mtcoding.rodongin.dto.resume.ResumeReq.ResumeUpdateDto;
import shop.mtcoding.rodongin.dto.resume.ResumeResp.ResumeListRespDto;
import shop.mtcoding.rodongin.dto.resume.ResumeSaveInDto;
import shop.mtcoding.rodongin.dto.resume.ResumeUpdateInDto;

@Mapper
public interface ResumeRepository {

    public ResumeDetailOutDto findByResumeJoinAndEmployee(int id);

    public List<Resume> findAll();

    public Resume findById(int id);

    public int updateById(@Param("id") int id, @Param("resumeUpdateInDto") ResumeUpdateInDto resumeUpdateInDto);

    public int deleteById(int id);

    public List<ResumeApplyListRespDto> findByApplyResumeIdWithCareer(int applyResumeId);

    public List<ResumeDto> findByEmpId(int employeeId);

    public int insert(@Param("employeeId") int employeeId, @Param("resumeSaveInDto") ResumeSaveInDto resumeSaveInDto);

    public List<Resume> findByResumeId(int resumeId);

    public ResumeDto findByIdApply(Integer id);
}
