package shop.mtcoding.rodongin.model.master;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LicenseMasterRepository {
    public List<LicenseMaster> findAll();
}
