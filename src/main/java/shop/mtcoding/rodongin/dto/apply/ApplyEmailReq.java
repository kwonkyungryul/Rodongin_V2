package shop.mtcoding.rodongin.dto.apply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Setter
    @Getter
    @NoArgsConstructor
    public class ApplyEmailReq {
        private Integer employeeId;
        private Integer companyId;
        private Integer status;
    }
