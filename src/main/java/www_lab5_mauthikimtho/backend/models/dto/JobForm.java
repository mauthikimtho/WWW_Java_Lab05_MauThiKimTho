package www_lab5_mauthikimtho.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobForm {
    private Long id;
    private String jobTitle;
    private String jobDescription;
    private String jobType;  // e.g. Full-time, Part-time
    private Long companyId;
    private Long addressId;
}
