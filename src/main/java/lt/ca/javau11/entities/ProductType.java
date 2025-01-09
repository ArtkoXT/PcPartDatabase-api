package lt.ca.javau11.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.types.CPU;
import lt.ca.javau11.entities.types.GPU;
import lt.ca.javau11.entities.types.Motherboard;
import lt.ca.javau11.entities.types.RAM;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productTypes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeName;

    @OneToMany(mappedBy = "productType")
    @JsonManagedReference
    private List<CPU> cpus = new ArrayList<>();

    @OneToMany(mappedBy = "productType")
    @JsonManagedReference
    private List<GPU> gpus = new ArrayList<>();

    @OneToMany(mappedBy = "productType")
    @JsonManagedReference
    private List<Motherboard> mobos = new ArrayList<>();

    @OneToMany(mappedBy = "productType")
    @JsonManagedReference
    private List<RAM> ram = new ArrayList<>();

    public void addGPU(GPU gpu) {
        gpus.add(gpu);
        gpu.setProductType(this);
    }

    public void addAllRam(List<RAM> ramList) {
        ram.addAll(ramList);
        ramList.forEach( r -> r.setProductType(this));
    }

    public void addMobos(List<Motherboard> m){
        mobos.addAll(m);
        m.forEach( mo -> mo.setProductType(this));
    }

    public void addGPUs(List<GPU> gpulist) {
        gpus.addAll(gpulist);
        gpulist.forEach( g -> g.setProductType(this));
    }


    public void addCPUs(List<CPU> cpuList){
        cpus.addAll(cpuList);
        cpuList.forEach( c -> c.setProductType(this));
    }

    public ProductType(String typeName) {
        this.typeName = typeName;
    }
}
