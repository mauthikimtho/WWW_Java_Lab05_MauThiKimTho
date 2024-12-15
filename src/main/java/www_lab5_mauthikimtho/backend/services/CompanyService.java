package www_lab5_mauthikimtho.backend.services;

import www_lab5_mauthikimtho.backend.models.entities.CandidateSkill;
import www_lab5_mauthikimtho.backend.models.entities.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyById(Long id);
    Company createCompany(Company company);
    Company updateCompany(Company company);
    void deleteCompany(Long id);
    Optional<Company> findCompanyByEmail(String email); // Xử lý đăng nhập
    List<Object[]> getRecruitmentActivityStatistics();
    List<Object[]> getCompanyStatisticsByIndustry();

    interface CandidateSkillService {

        // Phương thức tạo mới một CandidateSkill
        void createCandidateSkill(CandidateSkill candidateSkill);

        // Phương thức xóa một CandidateSkill
        void deleteCandidateSkill(Long id);

        // Phương thức tìm CandidateSkill theo ID
        CandidateSkill getCandidateSkillById(Long id);
    }
}
