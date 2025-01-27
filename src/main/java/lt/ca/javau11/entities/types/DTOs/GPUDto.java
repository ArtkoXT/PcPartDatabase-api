package lt.ca.javau11.entities.types.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.ca.javau11.entities.types.GPU;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPUDto {

    private Long id;

    private Long manufacturer_id;
    private String manufacturer_name;

    private String productType;

    private String model;
    private String gpuName;
    private String architecture;
    private Integer cores;
    private String tdp;
    private String baseClock;
    private String boostClock;
    private String memoryClock;
    private String memorySize;
    private String memoryType;
    private String memoryBus;
    private String memoryBandwidth;

    public GPUDto(GPU gpu){
        this.id = gpu.getId();
        this.manufacturer_id = gpu.getManufacturer().getId();
        this.manufacturer_name = gpu.getManufacturer().getName();
        this.productType = gpu.getProductType().getTypeName();
        this.model = gpu.getModel();
        this.gpuName = gpu.getGpuName();
        this.architecture = gpu.getArchitecture();
        this.cores = gpu.getCores();
        this.tdp = gpu.getTdp() + " W";
        this.baseClock = gpu.getBaseClock() + " MHz";
        this.boostClock = gpu.getBoostClock() + " MHz";
        this.memoryClock = gpu.getMemoryClock() + " MHz";
        this.memorySize = gpu.getMemorySize() + " GB";
        this.memoryType = gpu.getMemoryType();
        this.memoryBus = gpu.getMemoryBus() + " bit";
        this.memoryBandwidth = gpu.getMemoryBandwidth() + " GB/s";
    }

}
