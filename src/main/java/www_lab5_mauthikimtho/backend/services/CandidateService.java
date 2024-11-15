package www_lab5_mauthikimtho.backend.services;

import www_lab5_mauthikimtho.backend.models.entities.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {
    List<Candidate> getAllCandidates();
    Optional<Candidate> getCandidateById(Long id);
    Candidate createCandidate(Candidate candidate);
    Candidate updateCandidate(Candidate candidate);
    void deleteCandidate(Long id);
    List<Candidate> getCandidatesBySkill(Long skillId);
    List<Object[]> getCandidatesStatisticsByTime(String period); // Thống kê theo thời gian (month, quarter, year)
    List<Object[]> getCandidatesStatisticsBySkill();
    List<Object[]> getCandidatesStatisticsByLocation();
}
