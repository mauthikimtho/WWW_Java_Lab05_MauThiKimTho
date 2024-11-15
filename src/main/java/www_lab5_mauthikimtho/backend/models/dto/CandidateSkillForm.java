package www_lab5_mauthikimtho.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateSkillForm {
    private Long candidateId;
    private Long skillId;
    private int skillLevel;
    private String moreInfos;
}
