package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.types.DTOs.MotherboardDto;
import lt.ca.javau11.entities.types.Motherboard;
import lt.ca.javau11.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motherboards")
@AllArgsConstructor
public class MotherboardController {

    ProductService productService;

    @GetMapping("/all")
    public List<MotherboardDto> getAll(){
        return productService.getAllMotherboards();
    }

    @PutMapping("/{id}")
    public Motherboard updateMotherboard(@PathVariable Long id, @RequestBody Motherboard motherboard) {
        return productService.updateMotherboard(id, motherboard);
    }
}
