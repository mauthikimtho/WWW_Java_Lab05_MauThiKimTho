package www_lab5_mauthikimtho.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import www_lab5_mauthikimtho.backend.models.entities.JobSkill;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {
    // 1. Lấy danh sách kỹ năng cần thiết cho một công việc
    @Query(value = "SELECT js.* FROM job_skill js WHERE js.job_id = :jobId", nativeQuery = true)
    List<JobSkill> findSkillsByJobId(@Param("jobId") Long jobId);

    // 2. Thêm kỹ năng mới cho một công việc
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO job_skill (job_id, skill_id, skill_level, more_infos) " +
            "VALUES (:jobId, :skillId, :skillLevel, :moreInfos)", nativeQuery = true)
    void addSkillToJob(@Param("jobId") Long jobId,
                       @Param("skillId") Long skillId,
                       @Param("skillLevel") int skillLevel,
                       @Param("moreInfos") String moreInfos);

    // Truy vấn tìm kiếm JobSkill theo jobId và skillId
    @Query("SELECT js FROM JobSkill js WHERE js.id = :jobId AND js.id = :skillId")
    Optional<JobSkill> findById(@Param("jobId") Long jobId, @Param("skillId") Long skillId);
}
