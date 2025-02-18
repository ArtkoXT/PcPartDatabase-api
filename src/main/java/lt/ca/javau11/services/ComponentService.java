package lt.ca.javau11.services;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Category;
import lt.ca.javau11.entities.Component;
import lt.ca.javau11.entities.DTOs.ComponentDto;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.mappers.EntityMapper;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.ComponentRepository;
import lt.ca.javau11.repositories.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ComponentService {

    ManufacturerRepository manufacturerRepo;
    ComponentRepository componentRepository;
    EntityMapper entityMapper;

    public List<ComponentDto> listComponentsByCategory(Category category) {
        return componentRepository.findByCategory(category).stream().map(ComponentDto::new).toList();
    }

    public List<ComponentDto> listAllComponents(){
        return componentRepository.findAll().stream().map(ComponentDto::new).toList();
    }

    public Optional<ComponentDto> getComponentByID(Long id){
        return componentRepository.findById(id).map(ComponentDto::new);
    }

    public boolean deleteComponent(Long id) {
        Optional<Component> component = componentRepository.findById(id);

        if(component.isEmpty())
            return false;

        componentRepository.delete(component.get());
        return true;
    }

    public ComponentDto addNewComponent(ComponentDto component) {
        validateProperties(component.getCategory(), component.getProperties());

        Component newComponent = new Component();

        newComponent.setId(component.getId());
        newComponent.setName(component.getName());
        newComponent.setManufacturer(manufacturerRepo.findById(component.getManufacturer_id()).orElseThrow( () -> new NotFoundException("Manufacturer not found!")));
        newComponent.setCategory(component.getCategory());
        newComponent.setPrice(component.getPrice());
        newComponent.setProperties(component.getProperties());

        componentRepository.save(newComponent);

        return toComponentDto(newComponent);
    }

    public ComponentDto updateComponent(Long id, ComponentDto component) {

        validateProperties(component.getCategory(), component.getProperties());

        Component target = componentRepository.findById(id)
                .orElseThrow( () -> new NotFoundException("Component not found!"));

        target.setName(component.getName());
        target.setCategory(component.getCategory());
        target.setPrice(component.getPrice());
        target.setProperties(component.getProperties());

        Manufacturer manufacturer = manufacturerRepo.findById(component.getManufacturer_id())
                .orElseThrow( () -> new NotFoundException("Manufacturer not found!"));

        target.setManufacturer(manufacturer);

        componentRepository.save(target);

        return toComponentDto(target);
    }

    public Category[] getAllCategories(){
        return Category.values();
    }

    private void validateProperties(Category category, JsonNode properties) {

        Map<String, List<String>> requiredProperties = Map.of(
                "CPU", List.of("coreClock", "boostClock", "tdp", "integratedGraphics", "architecture", "socket", "coreCount", "threadCount", "l2cache", "l3cache"),
                "GPU", List.of("graphicsProcessor", "architecture", "cores", "tdp", "baseClock", "boostClock", "memoryClock", "memorySize", "memoryType", "memoryBus", "memoryBandwidth"),
                "MOTHERBOARD", List.of("socket", "formFactor", "chipset", "memoryMax", "memoryType", "memorySlots", "pciex16slots", "m2slots", "usb2headers", "usb3headers", "wifi"),
                "RAM", List.of("size", "speed", "formFactor", "modules", "casLatency", "timing")
        );

        List<String> missingProps = new ArrayList<>();
        for(String prop : requiredProperties.get(category.toString())) {
            if (!properties.hasNonNull(prop) || properties.get(prop).asText().isEmpty())
                missingProps.add(prop);
        }

        if(!missingProps.isEmpty())
            throw new IllegalArgumentException("Error! " + category + " has missing properties: " + String.join(", ", missingProps));

    }

    private ComponentDto toComponentDto(Component component) {

        ComponentDto dto = new ComponentDto();
        dto.setId(component.getId());
        dto.setManufacturer_id(component.getManufacturer().getId());
        dto.setName(component.getName());
        dto.setManufacturer_name(component.getManufacturer().getName());
        dto.setCategory(component.getCategory());
        dto.setPrice(component.getPrice());
        dto.setProperties(component.getProperties());

        return dto;
    }
}
