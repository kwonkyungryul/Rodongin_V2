package shop.mtcoding.rodongin.model.announcement;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.announcement.*;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementReq.AnnouncementUpdateReqDto;
import shop.mtcoding.rodongin.dto.announcement.AnnouncementResp.AnnouncementDetailRespDto;

// 명사가 뒤 동사가 앞으로
@Mapper
public interface AnnouncementRepository {

        public int insert(@Param("companyId") int companyId,
                        @Param("announcementSaveInDto") AnnouncementSaveInDto announcementSaveInDto);

        public AnnouncementDetailOutDto findByIdJoinCompanyAndStack(int id);

        public int findAnnouncementCount(@Param("skills") List<String> skills, @Param("content") String content);

        public AnnouncementDetailRespDto findAnnouncementAndCompanyId(int id);

        public List<AnnouncementListDto> findAnnouncementlist(@Param("skills") List<String> skills,
                                                              @Param("content") String content, @Param("start") Integer start, @Param("end") Integer end);
        // public List<AnnouncementDetailReqDto> findByDetailUser(int id);

        public List<Announcement> findTopList();

        public List<Announcement> findAll();

        public Announcement findById(int id);

        public int deleteById(int id);

        public int updateById(@Param("announcementUpdateInDto") AnnouncementUpdateInDto announcementUpdateInDto,
                        @Param("id") int id);
}