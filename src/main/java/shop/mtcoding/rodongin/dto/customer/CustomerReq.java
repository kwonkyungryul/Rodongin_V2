package shop.mtcoding.rodongin.dto.customer;

import lombok.Getter;
import lombok.Setter;

public class CustomerReq {

    @Setter
    @Getter
    public static class CustomerSaveReqDto {
        private String customerTitle;
        private String customerContent;
    }

    @Setter
    @Getter
    public static class CustomerUpdateReqDto {
        private String customerTitle;
        private String customerContent;

    }
}
