package shop.mtcoding.rodongin.dto.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerUpdateInDto {
    private String customerTitle;
    private String customerContent;

    @Builder
    public CustomerUpdateInDto(String customerTitle, String customerContent) {
        this.customerTitle = customerTitle;
        this.customerContent = customerContent;
    }
}
