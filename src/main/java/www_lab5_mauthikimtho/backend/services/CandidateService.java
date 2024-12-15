package www_lab5_mauthikimtho.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import www_lab5_mauthikimtho.backend.models.entities.Candidate;
import www_lab5_mauthikimtho.backend.models.entities.CandidateSkill;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    List<Candidate> getAllCandidates();
    Optional<Candidate> getCandidateById(Long id);
    Candidate createCandidate(Candidate candidate);
    Candidate updateCandidate(Long id, Candidate candidate);
    void deleteCandidate(Long id);
    List<Candidate> getCandidatesBySkill(Long skillId);
    List<Object[]> getCandidatesStatisticsByTime(String period); // Thống kê theo thời gian (month, quarter, year)
    List<Object[]> getCandidatesStatisticsBySkill();
    List<Object[]> getCandidatesStatisticsByLocation();
    Page<Candidate> getAllCandidates(Pageable pageable);

    Optional<Candidate> findByEmail(String email);

    List<CandidateSkill> getSkillsByCandidateId(Long canId);
}
