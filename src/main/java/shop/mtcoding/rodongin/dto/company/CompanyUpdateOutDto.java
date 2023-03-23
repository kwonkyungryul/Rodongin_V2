package shop.mtcoding.rodongin.dto.company;

import java.math.BigInteger;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CompanyUpdateOutDto {
        private int id;
        private String companyFullname;
        private String companyThumbnail;
        private Date companyEstablish;
        private BigInteger companySales;
        private int companyEmployeesNumber;
        private String companyIntroduction;
        private String companyHistory;
        private String companyVision;
        
        
}
