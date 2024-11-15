package www_lab5_mauthikimtho.backend.services;

import www_lab5_mauthikimtho.backend.models.entities.JobSkill;

import java.util.List;
import java.util.Optional;

public interface JobSkillService {
    List<JobSkill> getAllJobSkills();
    Optional<JobSkill> getJobSkillById(Long jobId, Long skillId);
    JobSkill createJobSkill(JobSkill jobSkill);
    JobSkill updateJobSkill(JobSkill jobSkill);
    void deleteJobSkill(Long jobId, Long skillId);
}
