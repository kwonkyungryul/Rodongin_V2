package shop.mtcoding.rodongin.model.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.resume.ResumeResp.ResumeStackRespDto;
import shop.mtcoding.rodongin.dto.resume.ResumeStackDto;

@Mapper
public interface ResumeStackRepository {
        public int deleteByResumeId(int resumeId);

        public List<ResumeStackDto> findByResumeStackJoinStackMaster(int resumeId);

        List<ResumeStackRespDto> findByResumeId(int resumeId);

        public void insert(@Param("resumeId") int resumeId, @Param("stackId") int stackId,
                        @Param("stackAcquisition") String stackAcquisition);

        public void updateByResumeId(@Param("resumeId") int resumeId, @Param("stackId") int stackId,
                        @Param("stackAcquisition") String stackAcquisition);

        public List<ResumeStackDto> findByIdApply(Integer resumeId);

}
