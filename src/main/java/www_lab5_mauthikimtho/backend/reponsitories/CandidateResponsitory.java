package www_lab5_mauthikimtho.backend.reponsitories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import www_lab5_mauthikimtho.backend.models.entities.Candidate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateResponsitory extends JpaRepository<Candidate, Long> {

    // 1. Tìm ứng viên theo email (phục vụ đăng nhập)
    Optional<Candidate> findByEmail(String email);

    // 2. Tìm ứng viên theo kỹ năng cụ thể
    @Query(value = "SELECT c.* FROM candidate c " +
            "JOIN candidate_skill cs ON c.id = cs.can_id " +
            "JOIN skill s ON cs.skill_id = s.skill_id " +
            "WHERE s.skill_name = :skillName AND cs.skill_level >= :minSkillLevel",
            nativeQuery = true)
    List<Candidate> findBySkill(@Param("skillName") String skillName,
                                @Param("minSkillLevel") int minSkillLevel);

    // 3. Thống kê số lượng ứng viên theo tháng/quý/năm
    @Query(value = "SELECT YEAR(c.created_date) AS year, MONTH(c.created_date) AS month, COUNT(*) AS count " +
            "FROM candidate c " +
            "GROUP BY YEAR(c.created_date), MONTH(c.created_date) " +
            "ORDER BY year DESC, month DESC",
            nativeQuery = true)
    List<Object[]> countCandidatesByMonth();

    @Query(value = "SELECT YEAR(c.created_date) AS year, QUARTER(c.created_date) AS quarter, COUNT(*) AS count " +
            "FROM candidate c " +
            "GROUP BY YEAR(c.created_date), QUARTER(c.created_date) " +
            "ORDER BY year DESC, quarter DESC",
            nativeQuery = true)
    List<Object[]> countCandidatesByQuarter();

    @Query(value = "SELECT YEAR(c.created_date) AS year, COUNT(*) AS count " +
            "FROM candidate c " +
            "GROUP BY YEAR(c.created_date) " +
            "ORDER BY year DESC",
            nativeQuery = true)
    List<Object[]> countCandidatesByYear();

    // 4. Thống kê ứng viên theo kỹ năng
    @Query(value = "SELECT s.skill_name AS skillName, COUNT(c.id) AS candidateCount " +
            "FROM skill s " +
            "JOIN candidate_skill cs ON s.skill_id = cs.skill_id " +
            "JOIN candidate c ON cs.can_id = c.id " +
            "GROUP BY s.skill_name " +
            "ORDER BY candidateCount DESC",
            nativeQuery = true)
    List<Object[]> countCandidatesBySkill();

    // 5. Thống kê ứng viên theo vị trí địa lý
    @Query(value = "SELECT ad.city AS city, COUNT(c.id) AS count " +
            "FROM address ad " +
            "JOIN candidate c ON c.address = ad.id " +
            "GROUP BY ad.city " +
            "ORDER BY count DESC",
            nativeQuery = true)
    List<Object[]> countCandidatesByLocation();

    // 6. Danh sách ứng viên (hỗ trợ phân trang)
    @Query(value = "SELECT c.* FROM candidate c ORDER BY c.id DESC",
            countQuery = "SELECT COUNT(*) FROM candidate c",
            nativeQuery = true)
    Page<Candidate> findAllCandidates(Pageable pageable);

    // 7. Tìm ứng viên theo ID (hỗ trợ cập nhật/xóa)
    Optional<Candidate> findById(Long id);

    // 8. Tìm ứng viên phù hợp cho một công việc
    @Query(value = "SELECT DISTINCT c.* FROM candidate c " +
            "JOIN candidate_skill cs ON c.id = cs.can_id " +
            "JOIN job_skill js ON cs.skill_id = js.skill_id " +
            "WHERE js.job_id = :jobId AND cs.skill_level >= js.skill_level",
            nativeQuery = true)
    List<Candidate> findSuitableCandidatesForJob(@Param("jobId") Long jobId);
}
