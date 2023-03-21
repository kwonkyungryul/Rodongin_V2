package shop.mtcoding.rodongin.model.company;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.company.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.rodongin.dto.company.CompanyReq.CompanyLoginReqDto;

@Mapper
public interface CompanyRepository {

    public Company findByCompanyUsername(String companyUsername);

    public Company findByCompanyNameAndPassword(CompanyLoginReqDto companyLoginReqDto);

    public List<Company> findAll();

    public Company findById(int id);

    public int insert(CompanyJoinReqDto companyJoinReqDto);

    public int updateById(@Param("id") int id, @Param("companyFullname") String companyFullname, 
    @Param("companyThumbnail") String companyThumbnail, @Param("companyEstablish") Date companyEstablish,
    @Param("companySales") BigInteger companySales,
    @Param("companyEmployeesNumber") int companyEmployeesNumber,
    @Param("companyIntroduction")  String companyIntroduction,
    @Param("companyHistory")   String companyHistory,@Param("companyVision")  String companyVision );
    public int deleteById(int id);

}
