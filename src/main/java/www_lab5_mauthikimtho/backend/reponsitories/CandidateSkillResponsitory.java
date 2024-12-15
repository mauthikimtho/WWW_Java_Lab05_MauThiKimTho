package www_lab5_mauthikimtho.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import www_lab5_mauthikimtho.backend.models.entities.CandidateSkill;
import www_lab5_mauthikimtho.backend.models.entities.CandidateSkillId;

import java.util.List;

@Repository
public interface CandidateSkillResponsitory extends JpaRepository<CandidateSkill, CandidateSkillId> {
    // 1. Lấy danh sách kỹ năng của một ứng viên
    @Query(value = "SELECT cs.* FROM candidate_skill cs WHERE cs.can_id = :candidateId", nativeQuery = true)
    List<CandidateSkill> findSkillsByCandidateId(@Param("candidateId") Long candidateId);

    // 2. Xóa kỹ năng của ứng viên
//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM candidate_skill WHERE can_id = :candidateId AND skill_id = :skillId", nativeQuery = true)
//    void deleteSkill(@Param("candidateId") Long candidateId, @Param("skillId") Long skillId);

    // 3. Thêm kỹ năng mới cho ứng viên
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO candidate_skill (can_id, skill_id, skill_level, more_infos) " +
            "VALUES (:candidateId, :skillId, :skillLevel, :moreInfos)", nativeQuery = true)
    void addSkill(@Param("candidateId") Long candidateId,
                  @Param("skillId") Long skillId,
                  @Param("skillLevel") int skillLevel,
                  @Param("moreInfos") String moreInfos);
    public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {

    }
}
