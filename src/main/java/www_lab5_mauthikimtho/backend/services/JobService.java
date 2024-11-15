package www_lab5_mauthikimtho.backend.services;

import www_lab5_mauthikimtho.backend.models.entities.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    List<Job> getAllJobs();
    Optional<Job> getJobById(Long jobId);
    Job createJob(Job job);
    Job updateJob(Job job);
    void deleteJob(Long jobId);
}
