package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Category;
import lt.ca.javau11.entities.Component;
import lt.ca.javau11.entities.ComponentDto;
import lt.ca.javau11.services.ComponentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/components")
@CrossOrigin
public class ComponentController {

    ComponentService componentService;

    @GetMapping("/all")
    public List<ComponentDto> listAllComponents(){
        return componentService.listAllComponents();
    }

    @GetMapping("/categories")
    public Category[] getAllCategories() {
        return componentService.getAllCategories();
    }

    @GetMapping("/categories/{category}")
    public List<ComponentDto> listComponentsByCategory(@PathVariable String category) {
        return componentService.listComponentsByCategory( Category.valueOf(category.toUpperCase()) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentDto> getComponentByID(@PathVariable Long id) {
        return ResponseEntity.of(componentService.getComponentByID(id));
    }

    @PostMapping("/add")
    public Component addNewComponent(@RequestBody Component component){
        return componentService.addNewComponent(component);
    }

    @PutMapping("/{id}")
    public Component updateComponent(@PathVariable Long id, @RequestBody Component component) {
        return componentService.updateComponent(id, component);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Component> deleteComponent(@PathVariable Long id) {
        boolean isDeleted = componentService.deleteComponent(id);

        return isDeleted ?
                ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
