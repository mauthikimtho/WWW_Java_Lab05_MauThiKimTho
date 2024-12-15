package www_lab5_mauthikimtho.backend.models.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import www_lab5_mauthikimtho.backend.enums.CountryCode;

import java.time.LocalDate;
@Getter
@Setter
public class CandidateForm {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String street;
    private String city;
    private CountryCode country;
    private String number;
    private String zipcode;
}
