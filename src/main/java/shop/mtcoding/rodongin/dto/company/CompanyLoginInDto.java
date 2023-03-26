package shop.mtcoding.rodongin.dto.company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CompanyLoginInDto {
    private String companyUsername;
    private String companyPassword;
}
