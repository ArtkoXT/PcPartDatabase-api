package lt.ca.javau11.controllers;

import lt.ca.javau11.entities.types.CPU;
import lt.ca.javau11.entities.types.DTOs.CPUDto;
import lt.ca.javau11.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cpus")
public class CpuController {

    ProductService productService;

    public CpuController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<CPU> getAllCpus(){
        return productService.getAllCpus();
    }

    @GetMapping("/{id}")
    public CPUDto getCpuByID(@PathVariable Long id) {
        return productService.getCpuByID(id);
    }

    @PostMapping("/add")
    public CPU addCpu(@RequestBody CPU cpu) {
        return productService.addCpu(cpu);
    }

    @PutMapping("/{id}")
    public CPU updateCPU(@PathVariable Long id, @RequestBody CPU cpu) {
        return productService.updateCpu(id, cpu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CPU> deleteCPU(@PathVariable Long id) {
        boolean isDeleted = productService.deleteCPU(id);
        return isDeleted ?
                ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
