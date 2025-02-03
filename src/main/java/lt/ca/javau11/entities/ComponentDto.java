package lt.ca.javau11.entities;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentDto {

    private Long id;
    private String name;
    private String manufacturer_name;
    private Category category;
    private Double price;
    private JsonNode properties;

    public ComponentDto(Component component) {
        this.id = component.getId();
        this.name = component.getName();
        this.manufacturer_name = component.getManufacturer().getName();
        this.category = component.getCategory();
        this.price = component.getPrice();
        this.properties = component.getProperties();
    }
}
