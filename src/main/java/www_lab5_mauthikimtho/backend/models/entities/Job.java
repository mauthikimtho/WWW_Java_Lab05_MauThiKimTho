package www_lab5_mauthikimtho.backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "job")
public class Job {
    @Id
    @Column(name = "job_id", nullable = false)
    private Long id;

    @Size(max = 2000)
    @NotNull
    @Column(name = "job_desc", nullable = false, length = 2000)
    private String jobDesc;

    @Size(max = 255)
    @NotNull
    @Column(name = "job_name", nullable = false)
    private String jobName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    private Company company;

}