package lt.ca.javau11.services;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Category;
import lt.ca.javau11.entities.Component;
import lt.ca.javau11.repositories.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ComponentService {

    ComponentRepository componentRepository;

    public List<Component> listAllComponents(){
        return componentRepository.findAll();
    }

    public Component addNewComponent(Component component) {
        validateComponent(component.getCategory(), component.getProperties());

        return componentRepository.save(component);
    }

    private void validateComponent(Category category, JsonNode properties) {

        Map<String, List<String>> requiredProperties = Map.of(
                "CPU", List.of("coreClock", "boostClock", "tdp", "integratedGraphics", "architecture", "socket", "coreCount", "threadCount", "l2cache", "l3cache"),
                "GPU", List.of("graphicsProcessor", "architecture", "cores", "tdp", "baseClock", "boostClock", "memoryClock", "memorySize", "memoryType", "memoryBus", "memoryBandwidth"),
                "MOTHERBOARD", List.of("socket", "formFactor", "chipset", "memoryMax", "memoryType", "memorySlots", "pciex16slots", "m2slots", "usb2headers", "usb3headers", "wifi"),
                "RAM", List.of("size", "speed", "formFactor", "modules", "casLatency", "timing")
        );

        List<String> missingProps = new ArrayList<>();
        for(String prop : requiredProperties.get(category.toString())) {
            if (!properties.has(prop))
                missingProps.add(prop);
        }

        if(!missingProps.isEmpty())
                    throw new IllegalArgumentException("Error! " + category + " has missing properties: " + String.join(", ", missingProps));

    }
}
