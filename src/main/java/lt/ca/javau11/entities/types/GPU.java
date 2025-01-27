package lt.ca.javau11.entities.types;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.Product;
import lt.ca.javau11.entities.ProductType;

@Entity
@Table(name = "gpus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GPU extends Product {

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
    private String gpuName;
    private String architecture;
    private Integer cores;
    private Integer tdp;
    private Integer baseClock;
    private Integer boostClock;
    private Integer memoryClock;
    private Integer memorySize;
    private String memoryType;
    private Integer memoryBus;
    private Float memoryBandwidth;

    public GPU(String model, String gpuName, String architecture, Integer cores, Integer tdp, Integer baseClock, Integer boostClock, Integer memoryClock, Integer memorySize, String memoryType, Integer memoryBus, Float memoryBandwidth) {
        this.model = model;
        this.gpuName = gpuName;
        this.architecture = architecture;
        this.cores = cores;
        this.tdp = tdp;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
        this.memoryClock = memoryClock;
        this.memorySize = memorySize;
        this.memoryType = memoryType;
        this.memoryBus = memoryBus;
        this.memoryBandwidth = memoryBandwidth;
    }
}
