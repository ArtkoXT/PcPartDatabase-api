package lt.ca.javau11.entities.DTOs;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.Category;
import lt.ca.javau11.entities.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentDto {

    private Long id;
    private Long manufacturer_id;
    private String name;
    private String manufacturer_name;
    private Category category;
    private Double price;
    private JsonNode properties;

    public ComponentDto(Component component) {
        this.id = component.getId();
        this.manufacturer_id = component.getManufacturer().getId();
        this.name = component.getName();
        this.manufacturer_name = component.getManufacturer().getName();
        this.category = component.getCategory();
        this.price = component.getPrice();
        this.properties = component.getProperties();
    }
}
