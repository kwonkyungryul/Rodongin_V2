package shop.mtcoding.rodongin.model.subscribe;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SubscribeRepository {
    public Subscribe findByEmployeeIdAndAnnouncementId(@Param("employeeId") Integer employeeId, @Param("announcementId") Integer announcementId);

    public Integer findByAnnouncementIdCount(Integer announcementId);

    public int insert(@Param("employeeId") Integer employeeId, @Param("announcementId") Integer announcementId);

    public int deleteByEmployeeIdAndAnnouncementId(@Param("employeeId") Integer employeeId, @Param("announcementId") Integer announcementId);
}
