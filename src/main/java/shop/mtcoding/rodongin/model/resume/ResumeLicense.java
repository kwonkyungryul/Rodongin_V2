package shop.mtcoding.rodongin.model.resume;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResumeLicense {
    private Integer id;
    private Integer resumeId;
    private Integer licenseId;
    private String licenseIssuer;
    private Timestamp createdAt;

    public ResumeLicense(Integer resumeId, Integer licenseId, String licenseIssuer) {
        this.resumeId = resumeId;
        this.licenseId = licenseId;
        this.licenseIssuer = licenseIssuer;
    }
}
