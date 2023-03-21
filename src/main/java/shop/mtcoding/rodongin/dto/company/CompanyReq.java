package shop.mtcoding.rodongin.dto.company;

import lombok.Getter;
import lombok.Setter;

public class CompanyReq {

    @Setter
    @Getter
    public static class CompanyLoginReqDto {
        private String companyUsername;
        private String companyPassword;
    }

    @Setter
    @Getter
    public static class CompanyJoinReqDto {
        private String companyUsername;
        private String companyPassword;
        private String companyLicenseNumber;
        private String companyCeoName;
        private String companyFullname;
        private String companyTel;
        private String companyAddress;
        private String companyEmail;
    }
}
