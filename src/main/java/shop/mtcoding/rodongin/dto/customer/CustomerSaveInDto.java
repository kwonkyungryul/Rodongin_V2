package shop.mtcoding.rodongin.dto.customer;

import lombok.Getter;
import lombok.Setter;

public class CustomerSaveInDto {

    private CustomerDto customer;

    @Setter
    @Getter
    public static class CustomerDto {
        private String customerTitle;
        private String customerContent;
    }

}
