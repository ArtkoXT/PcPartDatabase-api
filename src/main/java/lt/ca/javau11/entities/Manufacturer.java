package lt.ca.javau11.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lt.ca.javau11.entities.types.CPU;
import lt.ca.javau11.entities.types.GPU;
import lt.ca.javau11.entities.types.Motherboard;
import lt.ca.javau11.entities.types.RAM;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manufacturers")
@Getter
@Setter
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany( mappedBy = "manufacturer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CPU> cpus = new ArrayList<>();

    @OneToMany( mappedBy = "manufacturer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<GPU> gpus = new ArrayList<>();

    @OneToMany( mappedBy = "manufacturer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Motherboard> mobos = new ArrayList<>();

    @OneToMany( mappedBy = "manufacturer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<RAM> ram = new ArrayList<>();

    public void addCPU(CPU cpu ) {
        cpus.add(cpu);
        cpu.setManufacturer(this);
    }

    public void addGPU(GPU gpu ) {
        gpus.add(gpu);
        gpu.setManufacturer(this);
    }

    public void addMobo(Motherboard mobo){
        mobos.add(mobo);
        mobo.setManufacturer(this);
    }

    public void addRam(RAM rm){
        ram.add(rm);
        rm.setManufacturer(this);
    }

    public void removeCPU(CPU cpu) {
        cpus.remove(cpu);
        cpu.setManufacturer(null);
    }

    public Manufacturer(Long id, String name, List<CPU> cpus) {
        this.id = id;
        this.name = name;
        this.cpus = cpus;
    }

    public Manufacturer(String name, List<CPU> cpus) {
        this.name = name;
        this.cpus = cpus;
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    public Manufacturer() {}

}
