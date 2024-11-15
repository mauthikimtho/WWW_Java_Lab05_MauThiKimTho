package www_lab5_mauthikimtho.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobSkillForm {
    private Long jobId;
    private Long skillId;
    private int skillLevel;
    private String moreInfos;
}
