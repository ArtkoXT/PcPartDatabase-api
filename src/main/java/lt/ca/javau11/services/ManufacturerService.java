package lt.ca.javau11.services;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.mappers.EntityMapper;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManufacturerService {

    ManufacturerRepository manufacturerRepo;
    EntityMapper entityMapper;


    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepo.findAll();
    }

    public Manufacturer addNewManufacturer(Manufacturer manufacturer) {
        return manufacturerRepo.save(manufacturer);
    }

    public Manufacturer getManufacturerByID(Long id) {
        return manufacturerRepo.findById(id).orElseThrow( () -> new NotFoundException("Manufacturer with ID " + id + " not found!"));
    }

    public Optional<Manufacturer> updateManufacturer(Long id, Manufacturer source) {
        Optional<Manufacturer> existingManufacturer = manufacturerRepo.findById(id);

        if( existingManufacturer.isPresent() ){
            Manufacturer target = existingManufacturer.get();

            entityMapper.updateManufacturer(source, target);

            return Optional.of(manufacturerRepo.save(target));
        }

        return Optional.empty();
    }

    public boolean deleteManufacturer(Long id){
        Optional<Manufacturer> manufacturer = manufacturerRepo.findById(id);

        if( manufacturer.isEmpty() )
            return false;

        manufacturerRepo.delete(manufacturer.get());
        return true;
    }
}
