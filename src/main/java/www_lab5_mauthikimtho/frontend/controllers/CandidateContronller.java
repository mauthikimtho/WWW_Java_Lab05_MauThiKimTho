package www_lab5_mauthikimtho.frontend.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import www_lab5_mauthikimtho.backend.models.entities.Candidate;
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
        return "candidates/candidates-paging"; // Trả về tên view
    }


    // Load dữ liệu ứng viên không phân trang
    @GetMapping("/list")
    public String listCandidates(Model model) {
        List<Candidate> candidates = candidateService.getAllCandidates();
        model.addAttribute("candidates", candidates);
        return "candidates/candidates";
    }



}
