package lt.ca.javau11.entities.types.DTOs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.ProductType;
import lt.ca.javau11.entities.types.RAM;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RAMDto {

    private Long id;

    private Long manufacturer_id;
    private String manufacturer_name;

    private String productType;;

    private String model;
    private String size;
    private String speed;
    private String formFactor;
    private String modules;
    private String casLatency;
    private String timing;

    public RAMDto(RAM ram) {
        this.id = ram.getId();
        this.manufacturer_id = ram.getManufacturer().getId();
        this.manufacturer_name = ram.getManufacturer().getName();
        this.productType = ram.getProductType().getTypeName();
        this.model = ram.getModel();
        this.size = ram.getSize();
        this.speed = ram.getSpeed();
        this.formFactor = ram.getFormFactor();
        this.modules = ram.getModules();
        this.casLatency = ram.getCasLatency();
        this.timing = ram.getTiming();
    }
}
