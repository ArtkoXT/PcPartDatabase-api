package lt.ca.javau11.entities.types;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.ProductType;

@Entity
@Table(name = "gpus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @JsonBackReference
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "productType_id")
    @JsonBackReference
    private ProductType productType;

    private String model;
    private String gpuName;
    private String architecture;
    private String cores;
    private String tdp;
    private String baseClock;
    private String boostClock;
    private String memoryClock;
    private String memorySize;
    private String memoryType;
    private String memoryBus;
    private String memoryBandwidth;

    public GPU(String model, String gpuName, String architecture, String cores, String tdp, String baseClock, String boostClock, String memoryClock, String memorySize, String memoryType, String memoryBus, String memoryBandwidth) {
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
