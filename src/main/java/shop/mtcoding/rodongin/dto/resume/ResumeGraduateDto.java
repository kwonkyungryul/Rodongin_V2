package shop.mtcoding.rodongin.dto.resume;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ResumeGraduateDto {
    private Integer id;
    private SchoolMasterDto schoolMasterDto;
    private String schoolGraduate;
    private Timestamp createdAt;

    @Getter @Setter @NoArgsConstructor
    public static class SchoolMasterDto {
        private Integer id;
        private String schoolName;
    }
}
