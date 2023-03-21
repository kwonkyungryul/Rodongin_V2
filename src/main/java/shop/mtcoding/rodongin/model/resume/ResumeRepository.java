package shop.mtcoding.rodongin.model.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.employee.EmployeeResp.ResumeApplyListRespDto;
import shop.mtcoding.rodongin.dto.resume.ResumeReq.ResumeSaveDto;
import shop.mtcoding.rodongin.dto.resume.ResumeReq.ResumeUpdateDto;
import shop.mtcoding.rodongin.dto.resume.ResumeResp.ResumeListRespDto;

@Mapper
public interface ResumeRepository {

    public List<Resume> findAll();

    public Resume findById(int id);

    public int updateById(@Param("id") int id, @Param("resumeUpdateDto") ResumeUpdateDto resumeUpdateDto);

    public int deleteById(int id);

    public List<ResumeApplyListRespDto> findByApplyResumeIdWithCareer(int applyResumeId);

    public List<ResumeListRespDto> findByEmpId(int employeeId);

    public List<Resume> findByEmployeeId(int employeeId);

    public int insert(@Param("employeeId") int employeeId, @Param("resumeSaveDto") ResumeSaveDto resumeSaveDto);

    public List<Resume> findByResumeId(int resumeId);
}
