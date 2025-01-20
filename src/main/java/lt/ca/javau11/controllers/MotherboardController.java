package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.types.DTOs.MotherboardDto;
import lt.ca.javau11.entities.types.Motherboard;
import lt.ca.javau11.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motherboards")
@AllArgsConstructor
@CrossOrigin
public class MotherboardController {

    ProductService productService;

    @GetMapping("/all")
    public List<MotherboardDto> getAll(){
        return productService.getAllMotherboards();
    }

    @GetMapping("/{id}")
    public Motherboard getMotherboardByID(@PathVariable Long id) {
        return productService.getMotherboardByID(id);
    }

    @PostMapping("/add")
    public Motherboard addNewMotherboard(@RequestBody Motherboard motherboard) {
        return productService.addNewMotherboard(motherboard);
    }

    @PutMapping("/{id}")
    public Motherboard updateMotherboard(@PathVariable Long id, @RequestBody Motherboard motherboard) {
        return productService.updateMotherboard(id, motherboard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Motherboard> deleteMotherboard(@PathVariable Long id) {
        boolean isDeleted = productService.deleteMotherboard(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
