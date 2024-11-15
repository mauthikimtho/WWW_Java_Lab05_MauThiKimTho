package www_lab5_mauthikimtho.backend.services;

import www_lab5_mauthikimtho.backend.models.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> getAllAddresses();
    Optional<Address> getAddressById(Long id);
    Address createAddress(Address address);
    Address updateAddress(Address address);
    void deleteAddress(Long id);
}
