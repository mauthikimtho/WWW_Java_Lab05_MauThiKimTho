package www_lab5_mauthikimtho.frontend.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import www_lab5_mauthikimtho.backend.enums.CountryCode;
import www_lab5_mauthikimtho.backend.models.dto.CandidateForm;
import www_lab5_mauthikimtho.backend.models.entities.Address;
import www_lab5_mauthikimtho.backend.models.entities.Candidate;
import www_lab5_mauthikimtho.backend.models.entities.CandidateSkill;
import www_lab5_mauthikimtho.backend.reponsitories.CandidateSkillResponsitory;
import www_lab5_mauthikimtho.backend.reponsitories.SkillReponsitory;
import www_lab5_mauthikimtho.backend.services.AddressService;
import www_lab5_mauthikimtho.backend.services.CandidateService;
import www_lab5_mauthikimtho.backend.services.JobService;

import java.util.List;


@Controller
@RequestMapping("/candidates")
public class CandidateContronller {
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private SkillReponsitory skillRepository;
    @Autowired
    private CandidateSkillResponsitory candidateSkillRepository;
    @Autowired
    private JobService jobService;
    private static final Logger logger = LoggerFactory.getLogger(CandidateContronller.class);

    // Load dữ liệu  ứng viên phân trang
    @GetMapping("/list-paged")
    public String listCandidatesPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size); // Tạo Pageable
        Page<Candidate> candidatePage = candidateService.getAllCandidates(pageable); // Gọi service với Pageable
        model.addAttribute("candidatePage", candidatePage); // Truyền dữ liệu vào model
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        return "candidates/candidate-paging"; // Trả về tên view
    }


    // Load dữ liệu ứng viên không phân trang
    @GetMapping("/list")
    public String listCandidates(Model model) {
        List<Candidate> candidates = candidateService.getAllCandidates();
        model.addAttribute("candidates", candidates);
        return "candidates/candidate";
    }

    // Hiển thị form thêm ứng viên mới
    @GetMapping("/add")
    public String showAddCandidateForm(Model model) {
        model.addAttribute("candidateForm", new CandidateForm());
        return "candidates/add-candidate";
    }

    @PostMapping("/add")
    public String addCandidate(@ModelAttribute("candidateForm") CandidateForm candidateForm, RedirectAttributes redirectAttributes) {
        try{
            // Tạo Address mới
            Address address = new Address();
            address.setStreet(candidateForm.getStreet());
            address.setCity(candidateForm.getCity());
            address.setCountry(candidateForm.getCountry().getCode());
            address.setNumber(candidateForm.getNumber());
            address.setZipcode(candidateForm.getZipcode());

            addressService.createAddress(address);

            // Tạo Candidate mới
            Candidate candidate = new Candidate();
            candidate.setEmail(candidateForm.getEmail());
            candidate.setFullName(candidateForm.getFullName());
            candidate.setPhone(candidateForm.getPhone());
            candidate.setDob(candidateForm.getDob());
            candidate.setAddress(address);

            candidateService.createCandidate(candidate);

            redirectAttributes.addFlashAttribute("successMessage", "Thêm ứng viên thành công!");
            return "redirect:/candidate/list";
        } catch (Exception e){
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("errorMessage", "Thêm ứng viên thất bại!");
        return "redirect:/candidates/add";
    }

    // Xử lý xóa ứng viên
    @GetMapping("/delete/{id}")
    public String deleteCandidate(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            candidateService.deleteCandidate(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa ứng viên thành công!");
        } catch (ResourceAccessException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Xóa ứng viên thất bại!");
        }
        return "redirect:/candidates/list";
    }

    // Hiển thị form sửa ứng viên
    @GetMapping("/update/{id}")
    public String showEditCandidateForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Candidate candidate = candidateService.getCandidateById(id)
                .orElseThrow(() -> new ResourceAccessException("Ứng viên không tồn tại với ID: " + id));

        // Chuyển đổi từ Candidate sang CandidateForm
        CandidateForm candidateForm = new CandidateForm();
        candidateForm.setId(candidate.getId());
        candidateForm.setFullName(candidate.getFullName());
        candidateForm.setEmail(candidate.getEmail());
        candidateForm.setPhone(candidate.getPhone());
        candidateForm.setDob(candidate.getDob());
        candidateForm.setStreet(candidate.getAddress().getStreet());
        candidateForm.setCity(candidate.getAddress().getCity());
        candidateForm.setCountry(CountryCode.fromCode(candidate.getAddress().getCountry()));  // Chuyển mã quốc gia thành đối tượng CountryCode
        candidateForm.setNumber(candidate.getAddress().getNumber());
        candidateForm.setZipcode(candidate.getAddress().getZipcode());

        model.addAttribute("candidateForm", candidateForm);
        return "candidates/update-candidates";
    }


    // Xử lý cập nhật ứng viên
    @PostMapping("/update/{id}")
    public String updateCandidate(@PathVariable("id") Long id,
                                  @ModelAttribute("candidateForm") CandidateForm candidateForm,
                                  RedirectAttributes redirectAttributes) {
        try {
            Candidate existingCandidate = candidateService.getCandidateById(id)
                    .orElseThrow(() -> new ResourceAccessException("Ứng viên không tồn tại với ID: " + id));

            // Cập nhật thông tin ứng viên
            existingCandidate.setFullName(candidateForm.getFullName());
            existingCandidate.setEmail(candidateForm.getEmail());
            existingCandidate.setPhone(candidateForm.getPhone());
            existingCandidate.setDob(candidateForm.getDob());

            // Cập nhật địa chỉ
            Address existingAddress = existingCandidate.getAddress();
            existingAddress.setStreet(candidateForm.getStreet());
            existingAddress.setCity(candidateForm.getCity());
            existingAddress.setCountry(candidateForm.getCountry().getCode());
            existingAddress.setNumber(candidateForm.getNumber());
            existingAddress.setZipcode(candidateForm.getZipcode());

            // Lưu thông tin
            addressService.updateAddress(existingAddress);
            candidateService.updateCandidate(id, existingCandidate);

            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật ứng viên thành công!");
            return "redirect:/candidates/list";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật ứng viên thất bại!");
            return "redirect:/candidates/update/" + id;
        }
    }

    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("email", "");
        return "candidates/login";
    }

    // Xử lý đăng nhập
    @PostMapping("/login")
    public String handleLogin(@RequestParam("email") String email, Model model, RedirectAttributes redirectAttributes) {
        try {
            Candidate candidate = candidateService.findByEmail(email)
                    .orElseThrow(() -> new ResourceAccessException("Email không tồn tại: " + email));

            List<CandidateSkill> candidateSkills = candidateService.getSkillsByCandidateId(candidate.getId());

            // Ghi log thông tin candidate
            logger.info("Candidate: {}", candidate);

            // Ghi log thông tin skills
            if (candidateSkills != null && !candidateSkills.isEmpty()) {
                logger.info("Skills Count: {}", candidateSkills.size());
                for (CandidateSkill cs : candidateSkills) {
                    if (cs.getSkill() != null) {
                        logger.info("CandidateSkill ID: {}, Skill Name: {}", cs.getId(), cs.getSkill().getSkillName());
                    } else {
                        logger.warn("CandidateSkill ID: {} has null Skill", cs.getId());
                    }
                }
            } else {
                logger.warn("Skills list is null or empty for candidate ID: {}", candidate.getId());
            }

            model.addAttribute("candidate", candidate);
            model.addAttribute("candidateSkills", candidateSkills);

            return "candidates/profile"; // Trang hiển thị thông tin ứng viên
        } catch (ResourceAccessException e) {
            logger.error("ResourceNotFoundException: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/candidates/login";
        } catch (Exception e) {
            logger.error("Exception during login", e);
            redirectAttributes.addFlashAttribute("errorMessage", "Đăng nhập thất bại!");
            return "redirect:/candidates/login";
        }
    }




}
