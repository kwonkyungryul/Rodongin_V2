package shop.mtcoding.rodongin.model.resume;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResumeCareerRepository {

    public List<ResumeCareer> findByResumeId(int resumeId);

    public void insert(@Param("resumeId") int resumeId, @Param("careerCompany") String careerCompany,
            @Param("careerStart") Date careerStart,
            @Param("careerEnd") Date careerEnd);

    public List<ResumeCareer> findAll();

    public ResumeCareer findById(int id);

    public int updateById(int id, ResumeCareer apply);

    public int deleteById(int id);

    public void updateByResumeId(@Param("resumeId") int resumeId, @Param("careerCompany") String careerCompany,
            @Param("careerStart") Date careerStart,
            @Param("careerEnd") Date careerEnd);
}
