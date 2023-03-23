package shop.mtcoding.rodongin.dto.company;

import java.math.BigInteger;
import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter @Setter
public class CompanyUpdateInDto {
        private String companyFullname;
        private String companyThumbnail;
        private Date companyEstablish;
        private BigInteger companySales;
        private int companyEmployeesNumber;
        private String companyIntroduction;
        private String companyHistory;
        private String companyVision;
        
        @Builder
        public CompanyUpdateInDto(String companyFullname, String companyThumbnail, Date companyEstablish,
                BigInteger companySales, int companyEmployeesNumber, String companyIntroduction, String companyHistory,
                String companyVision) {
            this.companyFullname = companyFullname;
            this.companyThumbnail = companyThumbnail;
            this.companyEstablish = companyEstablish;
            this.companySales = companySales;
            this.companyEmployeesNumber = companyEmployeesNumber;
            this.companyIntroduction = companyIntroduction;
            this.companyHistory = companyHistory;
            this.companyVision = companyVision;
        }
        
}
