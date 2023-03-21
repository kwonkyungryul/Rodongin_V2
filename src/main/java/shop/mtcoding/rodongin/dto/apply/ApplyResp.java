package shop.mtcoding.rodongin.dto.apply;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class ApplyResp {
    
    @Setter
    @Getter
    public static class ApplyListRespDto {
        private String employeeFullname;
        private String employeeAddress;
        private String resumeTitle;
        private String resumeSalary;
        private String stackName;
        private String companyName;
        private Integer resumeId;
    }
}
