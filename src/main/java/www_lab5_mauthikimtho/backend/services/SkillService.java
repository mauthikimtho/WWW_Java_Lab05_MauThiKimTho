package www_lab5_mauthikimtho.backend.services;

import www_lab5_mauthikimtho.backend.models.entities.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillService {
    List<Skill> getAllSkills();
    Optional<Skill> getSkillById(Long skillId);
    Skill createSkill(Skill skill);
    Skill updateSkill(Skill skill);
    void deleteSkill(Long skillId);
}
