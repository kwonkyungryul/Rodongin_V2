package shop.mtcoding.rodongin.dto.company;

import java.math.BigInteger;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;


    @Getter @Setter
public class CompanyDetailOutDto {
    private int id;
    private String companyThumbnail;
    private String companyFullname;
    private Date companyEstablish;
    private BigInteger companySales;
    private int companyEmployeesNumber;
    private String companyIntroduction;
    private String companyHistory;
    private String companyVision;
}


