package www_lab5_mauthikimtho.backend.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import www_lab5_mauthikimtho.backend.models.entities.Address;
import www_lab5_mauthikimtho.backend.reponsitories.AddressResponsitory;
import www_lab5_mauthikimtho.backend.services.AddressService;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressResponsitory addressRepository;

    @Autowired
    public AddressServiceImpl(AddressResponsitory addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        if (address.getId() == null) {
            throw new IllegalArgumentException("Address ID cannot be null");
        }

        Address existingAddress = addressRepository.findById(address.getId())
                .orElseThrow(() -> new ResourceAccessException("Address not found for this id :: " + address.getId()));

        existingAddress.setStreet(address.getStreet());
        existingAddress.setCity(address.getCity());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setNumber(address.getNumber());
        existingAddress.setZipcode(address.getZipcode());

        return addressRepository.save(existingAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Address not found for this id :: " + id));
        addressRepository.delete(address);
    }
}
