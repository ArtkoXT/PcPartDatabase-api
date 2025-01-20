package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.ProductType;
import lt.ca.javau11.entities.types.DTOs.ProductTypeDTO;
import lt.ca.javau11.services.ProductTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/productTypes")
public class ProductTypeController {

    ProductTypeService ptService;

    @GetMapping("/all")
    public List<ProductTypeDTO> getAllTypes(){
        return ptService.getAllTypes();
    }

    @PostMapping("/add")
    public ProductType addNewType(@RequestBody ProductType productType){
        return ptService.addNewType(productType);
    }

    @GetMapping("/{id}")
    public ProductType getTypeByID(@PathVariable Long id) {
        return ptService.getTypeByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductType> updateType(@PathVariable Long id, @RequestBody ProductType productType){
        return ResponseEntity.of(ptService.updateType(id, productType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductType> deleteType(@PathVariable Long id) {
        boolean isDeleted = ptService.deleteType(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
