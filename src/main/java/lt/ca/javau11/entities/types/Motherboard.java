package lt.ca.javau11.entities.types;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.ProductType;

@Entity
@Table(name = "motherboards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motherboard {
    //Manufacturer
    //Model
    //Socket
    //Form factor
    //Chipset
    //Memory max
    //Memory type
    //Memory Slots
    //PCIe x16 slots
    //M.2 Slots
    //USB 2.0 headers
    //USB 3.2 headers
    //Wireless networking

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
    private String socket;
    private String formFactor;
    private String chipset;
    private String memoryMax;
    private String memoryType;
    private String memorySlots;
    private String pciex16slots;
    private String m2slots;
    private String usb2Headers;
    private String usb3Headers;
    private String wifi;

    public Motherboard(String model, String socket, String formFactor, String chipset, String memoryMax, String memoryType, String memorySlots, String pciex16slots, String m2slots, String usb2Headers, String usb3Headers, String wifi) {
        this.model = model;
        this.socket = socket;
        this.formFactor = formFactor;
        this.chipset = chipset;
        this.memoryMax = memoryMax;
        this.memoryType = memoryType;
        this.memorySlots = memorySlots;
        this.pciex16slots = pciex16slots;
        this.m2slots = m2slots;
        this.usb2Headers = usb2Headers;
        this.usb3Headers = usb3Headers;
        this.wifi = wifi;
    }
}
