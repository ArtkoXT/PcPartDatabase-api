package lt.ca.javau11.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "components")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    @JsonBackReference("manufacturer")
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "json")
    private JsonNode properties;

    public Component(String name, Manufacturer manufacturer, Double price, Category category, JsonNode properties) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.category = category;
        this.properties = properties;
    }
}
