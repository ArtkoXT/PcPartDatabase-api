package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.types.DTOs.RAMDto;
import lt.ca.javau11.entities.types.RAM;
import lt.ca.javau11.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ram")
public class RamController {

    ProductService productService;

    @GetMapping("/all")
    public List<RAMDto> getAllRAM(){
        return productService.getAllRAM();
    }

    @GetMapping("/{id}")
    public RAMDto getRamByID(@PathVariable Long id){
        return productService.getRamByID(id);
    }

    @PostMapping("/add")
    public RAM addNewRAM(@RequestBody RAM ram){
        return productService.addRAM(ram);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RAM> updateRAM(@PathVariable Long id, @RequestBody RAM ram) {
        return ResponseEntity.of(productService.updateRAM(id, ram));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RAM> deleteRAM(@PathVariable Long id) {
        boolean isDeleted = productService.deleteRAM(id);

        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
