package shop.mtcoding.rodongin.dto.resume;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ResumeStackDto {
    private Integer id;
    private StackMasterDto stackMasterDto;
    private String stackAcquisition;
    private Timestamp createdAt;

    @Getter @Setter @NoArgsConstructor
    public static class StackMasterDto {
        private Integer id;
        private String stackName;
    }
}
