package www_lab5_mauthikimtho.backend.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import www_lab5_mauthikimtho.backend.models.entities.Candidate;
import www_lab5_mauthikimtho.backend.models.entities.CandidateSkill;
import www_lab5_mauthikimtho.backend.reponsitories.CandidateResponsitory;
import www_lab5_mauthikimtho.backend.reponsitories.CandidateSkillResponsitory;
import www_lab5_mauthikimtho.backend.services.CandidateService;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateResponsitory candidateRepository;
    private final CandidateSkillResponsitory candidateSkillResponsitory;

    @Autowired
    public CandidateServiceImpl(CandidateResponsitory candidateRepository, CandidateSkillResponsitory candidateSkillResponsitory) {
        this.candidateRepository = candidateRepository;
        this.candidateSkillResponsitory = candidateSkillResponsitory;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate updateCandidate(Long id, Candidate candidate) {
        if (candidate.getId() == null) {
            throw new IllegalArgumentException("Candidate ID cannot be null");
        }

        Candidate existingCandidate = candidateRepository.findById(candidate.getId())
                .orElseThrow(() -> new ResourceAccessException("Candidate not found for this id :: " + candidate.getId()));

        existingCandidate.setFullName(candidate.getFullName());
        existingCandidate.setDob(candidate.getDob());
        existingCandidate.setEmail(candidate.getEmail());
        existingCandidate.setPhone(candidate.getPhone());
        existingCandidate.setAddress(candidate.getAddress());

        return candidateRepository.save(existingCandidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Candidate not found for this id :: " + id));
        candidateRepository.delete(candidate);
    }

    @Override
    public List<Candidate> getCandidatesBySkill(Long skillId) {
        return candidateRepository.findCandidatesBySkill(skillId);
    }

    @Override
    public List<Object[]> getCandidatesStatisticsByTime(String period) {
        if (!List.of("month", "quarter", "year").contains(period.toLowerCase())) {
            throw new IllegalArgumentException("Invalid period: " + period);
        }
        return candidateRepository.getCandidatesStatisticsByTime(period);
    }

    @Override
    public List<Object[]> getCandidatesStatisticsBySkill() {
        return candidateRepository.countCandidatesBySkill();
    }

    @Override
    public List<Object[]> getCandidatesStatisticsByLocation() {
        return candidateRepository.countCandidatesByLocation();
    }

    @Override
    public Page<Candidate> getAllCandidates(Pageable pageable) {
        return candidateRepository.findAll(pageable);
    }
    @Override
    public Optional<Candidate> findByEmail(String email) {
        return candidateRepository.findByEmail(email);
    }

    @Override
    public List<CandidateSkill> getSkillsByCandidateId(Long canId) {
        return candidateSkillResponsitory.findSkillsByCandidateId(canId);
    }
    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public List<CandidateSkill> getSkills(Long candidateId) {
        return candidateSkillResponsitory.findSkillsByCandidateId(candidateId);
    }
}
