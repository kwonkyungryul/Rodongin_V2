package shop.mtcoding.rodongin.model.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.resume.ResumeReq.ResumeLicenseSaveDto;
import shop.mtcoding.rodongin.dto.resume.ResumeResp.ResumeLicenseRespDto;

@Mapper
public interface ResumeLicenseRepository {

        public List<ResumeLicenseRespDto> findByResumeId(int resumeId);

        public void insert(@Param("resumeId") int resumeId, @Param("licenseId") int licenseId,
                        @Param("licenseIssuer") String licenseIssuer);

        public void insert(@Param("resumeId") int resumeId,
                        @Param("resumeLicenseSaveDto") ResumeLicenseSaveDto resumeLicenseSaveDto);

        public void updateByResumeId(@Param("resumeId") int resumeId, @Param("licenseId") int licenseId,
                        @Param("licenseIssuer") String licenseIssuer);

}
