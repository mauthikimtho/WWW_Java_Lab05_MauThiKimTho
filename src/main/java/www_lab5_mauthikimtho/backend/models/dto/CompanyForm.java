package www_lab5_mauthikimtho.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyForm {
    private Long id;
    private String email;
    private String compName;
    private String phone;
    private String webUrl;
    private String about;
    private Long addressId;
}
