package lt.ca.javau11.controllers;

import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.services.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/manufacturers")
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

    @PostMapping("/add")
    public Manufacturer addNewManufacturer(@RequestBody Manufacturer manufacturer) {
        return manufacturerService.addNewManufacturer(manufacturer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable Long id, @RequestBody Manufacturer manufacturer) {
        return ResponseEntity.of(manufacturerService.updateManufacturer(id, manufacturer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Manufacturer> deleteManufacturer(@PathVariable Long id) {
        boolean isDeleted = manufacturerService.deleteManufacturer(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}