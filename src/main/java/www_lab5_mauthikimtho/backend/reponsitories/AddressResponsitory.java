package www_lab5_mauthikimtho.backend.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import www_lab5_mauthikimtho.backend.models.entities.Address;

@Repository
public interface AddressResponsitory extends JpaRepository<Address, Long> {
}
