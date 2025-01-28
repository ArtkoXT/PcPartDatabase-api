package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Component;
import lt.ca.javau11.services.ComponentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/components")
public class ComponentController {

    ComponentService componentService;

    @GetMapping("/all")
    public List<Component> listAllComponents(){
        return componentService.listAllComponents();
    }

    @PostMapping("/add")
    public Component addNewComponent(@RequestBody Component component){
        return componentService.addNewComponent(component);
    }
}
