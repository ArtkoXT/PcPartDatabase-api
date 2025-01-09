package lt.ca.javau11.entities.types;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.ProductType;


@Entity
@Table(name = "cpus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CPU {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "product_id")
//    private Product product;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @JsonBackReference
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "productType_id")
    @JsonBackReference
    private ProductType productType;

    private String model;
    private String coreClock;
    private String boostClock;
    private String tdp;
    private String integratedGraphics;
    private String architecture;
    private String socket;
    private String coreCount;
    private String threadCount;
    private String l2cache;
    private String l3cache;


    public CPU(String model, String coreClock, String boostClock, String tdp, String integratedGraphics, String architecture, String socket, String coreCount, String threadCount, String l2cache, String l3cache) {
        this.model = model;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.tdp = tdp;
        this.integratedGraphics = integratedGraphics;
        this.architecture = architecture;
        this.socket = socket;
        this.coreCount = coreCount;
        this.threadCount = threadCount;
        this.l2cache = l2cache;
        this.l3cache = l3cache;
    }

}
