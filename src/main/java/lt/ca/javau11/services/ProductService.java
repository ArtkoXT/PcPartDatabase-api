package lt.ca.javau11.services;

import lt.ca.javau11.entities.types.CPU;
import lt.ca.javau11.entities.types.CPUDto;
import lt.ca.javau11.entities.types.GPU;
import lt.ca.javau11.entities.types.GPUDto;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.CpuRepository;
import lt.ca.javau11.repositories.GpuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    CpuRepository cpuRepo;
    GpuRepository gpuRepo;

    public ProductService( CpuRepository cpuRepo, GpuRepository gpuRepo) {
        this.cpuRepo = cpuRepo;
        this.gpuRepo = gpuRepo;
    }

    //CPU CRUD


    public List<CPU> getAllCpus() {
        return cpuRepo.findAll();
    }

    public CPU addCpu(CPU cpu) {
        return cpuRepo.save(cpu);
    }

    public CPUDto getCpuByID(Long id) {
        return cpuRepo.findById(id)
                .map(CPUDto::new)
                .orElseThrow( () -> new NotFoundException("Cpu with id " + id + " not found!"));
    }

    public Optional<CPU> updateCpu(Long id, CPU cpu) {
        Optional<CPU> updateCPU = cpuRepo.findById(id);
        if ( updateCPU.isPresent()) {
            CPU existingCPU = updateCPU.get();
                existingCPU.setModel(cpu.getModel());
                existingCPU.setManufacturer(cpu.getManufacturer());
                existingCPU.setCoreClock(cpu.getCoreClock());
                existingCPU.setBoostClock(cpu.getBoostClock());
                existingCPU.setTdp(cpu.getTdp());
                existingCPU.setIntegratedGraphics(cpu.getIntegratedGraphics());
                existingCPU.setArchitecture(cpu.getArchitecture());
                existingCPU.setSocket(cpu.getSocket());
                existingCPU.setCoreCount(cpu.getCoreCount());
                existingCPU.setThreadCount(cpu.getThreadCount());
                existingCPU.setL2cache(cpu.getL2cache());
                existingCPU.setL3cache(cpu.getL3cache());
            return Optional.of(cpuRepo.save(existingCPU));
        }
        return Optional.empty();

    }

    public boolean deleteCPU(Long id) {
        Optional<CPU> cpu = cpuRepo.findById(id);
        if ( cpu.isEmpty() )
            return false;

        cpuRepo.delete(cpu.get());
        return true;
    }

    //GPU CRUD


    public List<GPUDto> getAllGPUs() {
        return gpuRepo.findAll().stream().map(GPUDto::new).toList();
    }

    public GPU addGPU(GPU gpu) {
        return gpuRepo.save(gpu);
    }

    public GPUDto getGPUByID(Long id) {
        return gpuRepo.findById(id)
                .map(GPUDto::new)
                .orElseThrow( () -> new NotFoundException("Cpu with id " + id + " not found!"));
    }

    public Optional<GPU> updateGpu(Long id, GPU gpu) {
        Optional<GPU> updateGPU = gpuRepo.findById(id);
        if ( updateGPU.isPresent()) {
            GPU existingGPU = updateGPU.get();
            existingGPU.setModel(gpu.getModel());
            existingGPU.setManufacturer(gpu.getManufacturer());
            existingGPU.setGpuName(gpu.getGpuName());
            existingGPU.setArchitecture(gpu.getArchitecture());
            existingGPU.setCores(gpu.getCores());
            existingGPU.setTdp(gpu.getTdp());
            existingGPU.setBaseClock(gpu.getBaseClock());
            existingGPU.setBoostClock(gpu.getBoostClock());
            existingGPU.setMemoryClock(gpu.getMemoryClock());
            existingGPU.setMemorySize(gpu.getMemorySize());
            existingGPU.setMemoryType(gpu.getMemoryType());
            existingGPU.setMemoryBus(gpu.getMemoryBus());
            existingGPU.setMemoryBandwidth(gpu.getMemoryBandwidth());

            return Optional.of(gpuRepo.save(existingGPU));
        }
        return Optional.empty();

    }

    public boolean deleteGPU(Long id) {
        Optional<GPU> gpu = gpuRepo.findById(id);
        if ( gpu.isEmpty() )
            return false;

        gpuRepo.delete(gpu.get());
        return true;
    }

}
