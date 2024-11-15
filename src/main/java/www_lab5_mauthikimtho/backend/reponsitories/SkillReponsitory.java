package www_lab5_mauthikimtho.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import www_lab5_mauthikimtho.backend.models.entities.Skill;

import java.util.List;

@Repository
public interface SkillReponsitory extends JpaRepository<Skill, Long> {
    // 1. Thống kê kỹ năng phổ biến
    @Query(value = "SELECT s.skill_name AS skillName, COUNT(cs.can_id) AS candidateCount " +
            "FROM skill s " +
            "JOIN candidate_skill cs ON s.skill_id = cs.skill_id " +
            "GROUP BY s.skill_name " +
            "ORDER BY candidateCount DESC", nativeQuery = true)
    List<Object[]> findPopularSkills();

    // 2. Dự đoán kỹ năng cần thiết dựa trên xu hướng
    // Giả định sử dụng bảng khác nếu có thông tin về xu hướng
    @Query(value = "SELECT s.skill_name AS skillName, COUNT(js.job_id) AS jobDemand " +
            "FROM skill s " +
            "JOIN job_skill js ON s.skill_id = js.skill_id " +
            "GROUP BY s.skill_name " +
            "ORDER BY jobDemand DESC", nativeQuery = true)
    List<Object[]> predictRequiredSkills();
}
