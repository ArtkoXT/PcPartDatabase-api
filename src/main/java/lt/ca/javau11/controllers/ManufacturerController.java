package lt.ca.javau11.controllers;

import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.services.ManufacturerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {

    ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/all")
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerService.getAllManufacturers();
    }

    @GetMapping("/{id}")
    public Manufacturer getManufacturerByID(@PathVariable Long id){
        return manufacturerService.getManufacturerByID(id);
    }
}
