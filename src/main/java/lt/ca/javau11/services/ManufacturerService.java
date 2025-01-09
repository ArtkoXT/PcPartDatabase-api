package lt.ca.javau11.services;

import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerService {

    ManufacturerRepository manufacturerRepo;

    public ManufacturerService(ManufacturerRepository manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepo.findAll();
    }

    public Manufacturer getManufacturerByID(Long id) {
        return manufacturerRepo.findById(id).orElseThrow( () -> new NotFoundException("Manufacturer with ID " + id + " not found!"));
    }
}
