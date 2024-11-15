package www_lab5_mauthikimtho.backend.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "skill")
public class Skill {
    @Id
    @Column(name = "skill_id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "skill_description")
    private String skillDescription;

    @Size(max = 255)
    @Column(name = "skill_name")
    private String skillName;

    @Column(name = "type")
    private Byte type;

}