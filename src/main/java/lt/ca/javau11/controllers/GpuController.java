package lt.ca.javau11.controllers;

import lt.ca.javau11.entities.types.GPU;
import lt.ca.javau11.entities.types.DTOs.GPUDto;
import lt.ca.javau11.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gpus")
@CrossOrigin
public class GpuController {

    ProductService productService;

    public GpuController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<GPUDto> getAllGPUs(){
        return productService.getAllGPUs();
    }

    @GetMapping("/{id}")
    public GPUDto getGpuByID(@PathVariable Long id){
        return productService.getGPUByID(id);
    }

    @PostMapping("/add")
    public GPU addNewGpu(@RequestBody GPU gpu){
        return productService.addGPU(gpu);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GPU> updateGPU(@PathVariable Long id, @RequestBody GPU gpu){
        return ResponseEntity.of(productService.updateGpu(id, gpu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GPU> deleteGPU(@PathVariable Long id) {
        boolean isDeleted = productService.deleteGPU(id);
        return isDeleted ?
                ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

}
