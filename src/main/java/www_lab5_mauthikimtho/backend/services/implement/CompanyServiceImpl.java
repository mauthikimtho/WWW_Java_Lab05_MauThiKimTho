package www_lab5_mauthikimtho.backend.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import www_lab5_mauthikimtho.backend.models.entities.Company;
import www_lab5_mauthikimtho.backend.reponsitories.CompanyReponsitory;
import www_lab5_mauthikimtho.backend.services.CompanyService;

import java.util.List;
import java.util.Optional;
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyReponsitory companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyReponsitory companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company company) {
        if (company.getId() == null) {
            throw new IllegalArgumentException("Company ID cannot be null");
        }

        Company existingCompany = companyRepository.findById(company.getId())
                .orElseThrow(() -> new ResourceAccessException("Company not found for this id :: " + company.getId()));

        existingCompany.setCompName(company.getCompName());
        existingCompany.setEmail(company.getEmail());
        existingCompany.setPhone(company.getPhone());
        existingCompany.setAddress(company.getAddress());
        existingCompany.setWebUrl(company.getWebUrl());
        existingCompany.setAbout(company.getAbout());

        return companyRepository.save(existingCompany);
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Company not found for this id :: " + id));
        companyRepository.delete(company);
    }

    @Override
    public Optional<Company> findCompanyByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    @Override
    public List<Object[]> getRecruitmentActivityStatistics() {
        return companyRepository.analyzeRecruitmentActivity();
    }

    @Override
    public List<Object[]> getCompanyStatisticsByIndustry() {
        return companyRepository.countCompaniesByIndustry();
    }
}
