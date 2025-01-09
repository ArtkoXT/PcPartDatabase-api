package lt.ca.javau11.entities.types.DTOs;

import lombok.Data;
import lt.ca.javau11.entities.types.CPU;


@Data
public class CPUDto {

    private Long id;

    private Long manufacturer_id;
    private String manufacturer_name;

    private String productType;

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

    public CPUDto(Long id, Long manufacturer_id, String manufacturer_name, String model, String coreClock, String boostClock, String tdp, String integratedGraphics, String architecture, String socket, String coreCount, String threadCount, String l2cache, String l3cache) {
        this.id = id;
        this.manufacturer_id = manufacturer_id;
        this.manufacturer_name = manufacturer_name;
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

    public CPUDto(CPU cpu) {
        this.id = cpu.getId();
        this.manufacturer_id = cpu.getManufacturer().getId();
        this.manufacturer_name = cpu.getManufacturer().getName();
        this.productType = cpu.getProductType().getTypeName();
        this.model = cpu.getModel();
        this.coreClock = cpu.getCoreClock();
        this.boostClock = cpu.getBoostClock();
        this.tdp = cpu.getTdp();
        this.integratedGraphics = cpu.getIntegratedGraphics();
        this.architecture = cpu.getArchitecture();
        this.socket = cpu.getSocket();
        this.coreCount = cpu.getCoreCount();
        this.threadCount = cpu.getThreadCount();
        this.l2cache = cpu.getL2cache();
        this.l3cache = cpu.getL3cache();
    }

    public CPUDto() {}
}
