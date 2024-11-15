package www_lab5_mauthikimtho.backend.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import www_lab5_mauthikimtho.backend.models.entities.Skill;
import www_lab5_mauthikimtho.backend.reponsitories.SkillReponsitory;
import www_lab5_mauthikimtho.backend.services.SkillService;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {
    private final SkillReponsitory skillRepository;

    @Autowired
    public SkillServiceImpl(SkillReponsitory skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Optional<Skill> getSkillById(Long skillId) {
        return skillRepository.findById(skillId);
    }

    @Override
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        if (skill.getId() == null) {
            throw new IllegalArgumentException("Skill ID cannot be null");
        }

        Skill existingSkill = skillRepository.findById(skill.getId())
                .orElseThrow(() -> new ResourceAccessException("Skill not found for this id :: " + skill.getId()));

        existingSkill.setSkillName(skill.getSkillName());
        existingSkill.setSkillDescription(skill.getSkillDescription());
        existingSkill.setType(skill.getType());

        return skillRepository.save(existingSkill);
    }

    @Override
    public void deleteSkill(Long skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceAccessException("Skill not found for this id :: " + skillId));
        skillRepository.delete(skill);
    }

}
