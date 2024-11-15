package www_lab5_mauthikimtho.backend.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import www_lab5_mauthikimtho.backend.models.entities.JobSkill;
import www_lab5_mauthikimtho.backend.reponsitories.JobSkillRepository;
import www_lab5_mauthikimtho.backend.services.JobSkillService;

import java.util.List;
import java.util.Optional;

@Service
public class JobSkillServiceImpl implements JobSkillService {
    private final JobSkillRepository jobSkillRepository;

    @Autowired
    public JobSkillServiceImpl(JobSkillRepository jobSkillRepository) {
        this.jobSkillRepository = jobSkillRepository;
    }

    @Override
    public List<JobSkill> getAllJobSkills() {
        return jobSkillRepository.findAll();
    }

    @Override
    public Optional<JobSkill> getJobSkillById(Long jobId, Long skillId) {
        return jobSkillRepository.findById(jobId, skillId);
    }

    @Override
    public JobSkill createJobSkill(JobSkill jobSkill) {
        return jobSkillRepository.save(jobSkill);
    }

    @Override
    public JobSkill updateJobSkill(JobSkill jobSkill) {
        if (jobSkill.getJob().getId() == null || jobSkill.getSkill().getId() == null) {
            throw new IllegalArgumentException("Job ID and Skill ID cannot be null");
        }

        JobSkill existingJobSkill = jobSkillRepository.findById(jobSkill.getId().getJobId(), jobSkill.getSkill().getId())
                .orElseThrow(() -> new ResourceAccessException("JobSkill not found for this jobId and skillId"));

        existingJobSkill.setSkillLevel(jobSkill.getSkillLevel());
        existingJobSkill.setMoreInfos(jobSkill.getMoreInfos());

        return jobSkillRepository.save(existingJobSkill);
    }

    @Override
    public void deleteJobSkill(Long jobId, Long skillId) {
        JobSkill jobSkill = jobSkillRepository.findById(jobId, skillId)
                .orElseThrow(() -> new ResourceAccessException("JobSkill not found for this jobId and skillId"));
        jobSkillRepository.delete(jobSkill);
    }
}
