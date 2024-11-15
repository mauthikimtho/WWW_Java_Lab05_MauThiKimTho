package www_lab5_mauthikimtho.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PredictiveForm {
    private Long companyId;
    private String predictionType;
    private String period;
}
