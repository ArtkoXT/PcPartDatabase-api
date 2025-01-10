package lt.ca.javau11.entities.types;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.Product;
import lt.ca.javau11.entities.ProductType;

@Entity
@Table(name = "ram")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RAM extends Product {
    //Manufacturer
    //Model
    //Speed
    //Form factor
    //Modules
    //CAS latency
    //Timing

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @JsonBackReference("manufacturer")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "productType_id")
    @JsonBackReference("productType")
    private ProductType productType;

    private String model;
    private String size;
    private String speed;
    private String formFactor;
    private String modules;
    private String casLatency;
    private String timing;

    public RAM(String model, String size, String speed, String formFactor, String modules, String casLatency, String timing) {
        this.model = model;
        this.size = size;
        this.speed = speed;
        this.formFactor = formFactor;
        this.modules = modules;
        this.casLatency = casLatency;
        this.timing = timing;
    }
}
