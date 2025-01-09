package lt.ca.javau11.entities.types.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.types.Motherboard;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotherboardDto {

    private Long id;

    private Long manufacturer_id;
    private String manufacturer_name;

    private String productType;

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

    public MotherboardDto(Motherboard mb) {
        this.id = mb.getId();
        this.manufacturer_id = mb.getManufacturer().getId();
        this.manufacturer_name = mb.getManufacturer().getName();
        this.productType = mb.getProductType().getTypeName();
        this.model = mb.getModel();
        this.socket = mb.getSocket();
        this.formFactor = mb.getFormFactor();
        this.chipset = mb.getChipset();
        this.memoryMax = mb.getMemoryMax();
        this.memoryType = mb.getMemoryType();
        this.memorySlots = mb.getMemorySlots();
        this.pciex16slots = mb.getPciex16slots();
        this.m2slots = mb.getM2slots();
        this.usb2Headers = mb.getUsb2Headers();
        this.usb3Headers = mb.getUsb3Headers();
        this.wifi = mb.getWifi();
    }
}
