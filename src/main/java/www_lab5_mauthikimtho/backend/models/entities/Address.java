package www_lab5_mauthikimtho.backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 150)
    @Column(name = "street", length = 150)
    private String street;

    @Size(max = 50)
    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "country")
    private Short country;

    @Size(max = 20)
    @Column(name = "number", length = 20)
    private String number;

    @Size(max = 7)
    @Column(name = "zipcode", length = 7)
    private String zipcode;

}