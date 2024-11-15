package www_lab5_mauthikimtho.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import www_lab5_mauthikimtho.backend.models.entities.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyReponsitory extends JpaRepository<Company, Long> {
    boolean existsByEmail(String email);
    // 1. Tìm công ty theo email (đăng nhập)
    Optional<Company> findByEmail(String email);

    // 2. Thống kê số lượng công ty theo ngành nghề
    @Query(value = "SELECT c.comp_name AS companyName, COUNT(j.job_id) AS jobCount " +
            "FROM company c " +
            "JOIN job j ON c.comp_id = j.company " +
            "GROUP BY c.comp_name " +
            "ORDER BY jobCount DESC", nativeQuery = true)
    List<Object[]> countCompaniesByIndustry();

    // 3. Thống kê hoạt động tuyển dụng của công ty
    @Query(value = "SELECT c.comp_name AS companyName, COUNT(j.job_id) AS jobPosted, COUNT(js.skill_id) AS totalApplications " +
            "FROM company c " +
            "LEFT JOIN job j ON c.comp_id = j.company " +
            "LEFT JOIN job_skill js ON j.job_id = js.job_id " +
            "GROUP BY c.comp_name", nativeQuery = true)
    List<Object[]> analyzeRecruitmentActivity();
}
