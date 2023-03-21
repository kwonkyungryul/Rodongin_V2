package shop.mtcoding.rodongin.model.resume;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.resume.ResumeResp.ResumeGraduateRespDto;

@Mapper
public interface ResumeGraduateRepository {

        public List<ResumeGraduateRespDto> findByResumeId(int resumeId);

        public void insert(@Param("resumeId") int resumeId, @Param("schoolId") int schoolId,
                        @Param("schoolGraduate") String schoolGraduate);

        public List<ResumeGraduate> findAll();

        public ResumeGraduate findById(int id);

        public int updateById(int id, ResumeGraduate apply);

        public int deleteById(int id);

        public void updateByResumeId(@Param("resumeId") int resumeId, @Param("schoolId") int schoolId,
                        @Param("schoolGraduate") String schoolGraduate);

        // public GraduateResp findById(int employeeId);

}
