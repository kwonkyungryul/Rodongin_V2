package shop.mtcoding.rodongin.model.master;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StackMasterRepository {

    public List<StackMaster> findAll();

    public StackMaster findById(int id); 

    public StackMaster findByIdAnnouncement(int id);

}
