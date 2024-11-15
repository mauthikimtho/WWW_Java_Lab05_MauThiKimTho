package www_lab5_mauthikimtho.backend.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import www_lab5_mauthikimtho.backend.models.entities.Job;
import www_lab5_mauthikimtho.backend.reponsitories.JobReponsitory;
import www_lab5_mauthikimtho.backend.services.JobService;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final JobReponsitory jobRepository;

    @Autowired
    public JobServiceImpl(JobReponsitory jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Optional<Job> getJobById(Long jobId) {
        return jobRepository.findById(jobId);
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        if (job.getId() == null) {
            throw new IllegalArgumentException("Job ID cannot be null");
        }

        Job existingJob = jobRepository.findById(job.getId())
                .orElseThrow(() -> new ResourceAccessException("Job not found for this id :: " + job.getId()));

        existingJob.setJobName(job.getJobName());
        existingJob.setJobDesc(job.getJobDesc());
        existingJob.setCompany(job.getCompany());

        return jobRepository.save(existingJob);
    }

    @Override
    public void deleteJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceAccessException("Job not found for this id :: " + jobId));
        jobRepository.delete(job);
    }

}
