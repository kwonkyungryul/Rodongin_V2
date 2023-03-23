package shop.mtcoding.rodongin.model.company;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import shop.mtcoding.rodongin.dto.company.CompanyDetailOutDto;
import shop.mtcoding.rodongin.dto.company.CompanyJoinInDto;
import shop.mtcoding.rodongin.dto.company.CompanyLoginInDto;
import shop.mtcoding.rodongin.dto.company.CompanyUpdateInDto;

@Mapper
public interface CompanyRepository {

    public Company findByCompanyUsername(String companyUsername);

    public Company findByCompanyNameAndPassword(CompanyLoginInDto companyLoginInDto);

    public List<Company> findAll();

    // public Company findById(int id);

    public int insert(CompanyJoinInDto companyJoinInDto);

    public int updateById(@Param("companyUpdateInDto") CompanyUpdateInDto companyUpdateInDto,
    @Param("id") int id);

    public int deleteById(int id);

    public CompanyDetailOutDto findById(@Param("id") int id);


}
