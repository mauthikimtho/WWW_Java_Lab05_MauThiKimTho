package www_lab5_mauthikimtho.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import www_lab5_mauthikimtho.backend.models.entities.Candidate;
import www_lab5_mauthikimtho.backend.models.entities.Job;

import java.util.List;

@Repository
public interface JobReponsitory extends JpaRepository<Job, Long> {
    // 1. Lấy danh sách ứng viên cho một công việc
    @Query(value = "SELECT DISTINCT c.* FROM candidate c " +
            "JOIN candidate_skill cs ON c.id = cs.can_id " +
            "JOIN job_skill js ON cs.skill_id = js.skill_id " +
            "WHERE js.job_id = :jobId AND cs.skill_level >= js.skill_level", nativeQuery = true)
    List<Candidate> findCandidatesForJob(@Param("jobId") Long jobId);

    // 2. Thống kê số lượng việc làm theo loại hình
    @Query(value = "SELECT j.job_name AS jobType, COUNT(j.job_id) AS jobCount " +
            "FROM job j " +
            "GROUP BY j.job_name " +
            "ORDER BY jobCount DESC", nativeQuery = true)
    List<Object[]> countJobsByType();

    // 3. Thống kê ứng viên theo việc làm
    @Query(value = "SELECT j.job_name AS jobName, COUNT(cs.can_id) AS candidateCount " +
            "FROM job j " +
            "JOIN job_skill js ON j.job_id = js.job_id " +
            "JOIN candidate_skill cs ON js.skill_id = cs.skill_id " +
            "GROUP BY j.job_name " +
            "ORDER BY candidateCount DESC", nativeQuery = true)
    List<Object[]> analyzeCandidatesByJob();
}
