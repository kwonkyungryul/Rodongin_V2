package shop.mtcoding.rodongin.model.apply;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.apply.ApplyResp.ApplyListRespDto;

@Mapper
public interface ApplyRepository {
    public int insert(@Param("announcementId") int announcementId, @Param("resumeId") int resumeId);

    public List<Apply> findAll();

    public Apply findById(int id);

    public int updateById(int id, Apply apply);

    public int deleteById(int id);

    public List<ApplyListRespDto> findByCompanyId(int announcmentId);
}
