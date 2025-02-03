package lt.ca.javau11.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL)
    @JsonManagedReference("manufacturer")
    private List<Component> components = new ArrayList<>();


    public Manufacturer(Long id, String name, List<Component> components) {
        this.id = id;
        this.name = name;
        this.components = components;
    }

    public Manufacturer(String name, List<Component> components) {
        this.name = name;
        this.components = components;
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    public Manufacturer() {}

}
