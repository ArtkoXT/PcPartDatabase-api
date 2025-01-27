package lt.ca.javau11.entities.types;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.Product;
import lt.ca.javau11.entities.ProductType;


@Entity
@Table(name = "cpus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CPU extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    @JoinColumn(name = "product_id")
//    private Product product;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @JsonBackReference("manufacturer")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "productType_id")
    @JsonBackReference("productType")
    private ProductType productType;

    private String model;
    private Float coreClock;
    private Float boostClock;
    private Integer tdp;
    private String integratedGraphics;
    private String architecture;
    private String socket;
    private Integer coreCount;
    private Integer threadCount;
    private Integer l2cache;
    private Integer l3cache;


    public CPU( String model, Float coreClock, Float boostClock, Integer tdp, String integratedGraphics, String architecture, String socket, Integer coreCount, Integer threadCount, Integer l2cache, Integer l3cache) {
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
