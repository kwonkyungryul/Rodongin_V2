package shop.mtcoding.rodongin.dto.resume;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ResumeLicenseDto {
    private Integer id;
    private LicenseMasterDto licenseMasterDto;
    private String licenseIssuer;
    private Timestamp createdAt;

    @Getter @Setter @NoArgsConstructor
    public static class LicenseMasterDto {
        private Integer id;
        private String licenseName;
    }
}