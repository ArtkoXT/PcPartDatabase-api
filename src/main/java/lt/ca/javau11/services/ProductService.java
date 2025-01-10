package lt.ca.javau11.services;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.mappers.EntityMapper;
import lt.ca.javau11.entities.types.*;
import lt.ca.javau11.entities.types.DTOs.*;
import lt.ca.javau11.exceptions.NotFoundException;
import lt.ca.javau11.repositories.CpuRepository;
import lt.ca.javau11.repositories.GpuRepository;
import lt.ca.javau11.repositories.MotherboardRepository;
import lt.ca.javau11.repositories.RamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    CpuRepository cpuRepo;
    GpuRepository gpuRepo;
    MotherboardRepository mbRepo;
    RamRepository ramRepo;
    EntityMapper entityMapper;


    public ProductService(CpuRepository cpuRepo, GpuRepository gpuRepo, MotherboardRepository mbRepo, RamRepository ramRepo, EntityMapper entityMapper) {
        this.cpuRepo = cpuRepo;
        this.gpuRepo = gpuRepo;
        this.mbRepo = mbRepo;
        this.ramRepo = ramRepo;
        this.entityMapper = entityMapper;
    }

    //
    //CPU CRUD operations
    //
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

    public CPU updateCpu(Long id, CPU source) {
        CPU target = cpuRepo.findById(id).orElseThrow( () -> new NotFoundException("Cpu with id " + id + " not found!") );

        entityMapper.updateProduct(source, target);

        return cpuRepo.save(target);

    }

    public boolean deleteCPU(Long id) {
        Optional<CPU> cpu = cpuRepo.findById(id);
        if ( cpu.isEmpty() )
            return false;

        cpuRepo.delete(cpu.get());
        return true;
    }
    //
    //GPU CRUD operations
    //
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

    public Optional<GPU> updateGpu(Long id, GPU source) {
        Optional<GPU> existingGPU = gpuRepo.findById(id);
        if ( existingGPU.isPresent()) {
            GPU target = existingGPU.get();

            entityMapper.updateProduct(source, target);

            return Optional.of(gpuRepo.save(target));
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
    //
    // Motherboard CRUD operations
    //
    public List<MotherboardDto> getAllMotherboards() {
        return mbRepo.findAll().stream().map(MotherboardDto::new).toList();
    }

    public Motherboard addNewMotherboard(Motherboard mb) {
        return mbRepo.save(mb);
    }

    public Motherboard getMotherboardByID(Long id){
        return mbRepo.findById(id).orElseThrow(() -> new NotFoundException("Motherboard with id " + id + " not found!"));
    }

    public Motherboard updateMotherboard(Long id, Motherboard source) {
        Motherboard target = mbRepo.findById(id)
                .orElseThrow( () -> new NotFoundException("Motherboard with id " + id + " not found!"));

        entityMapper.updateProduct(source,target);

        return mbRepo.save(target);
    }

    public boolean deleteMotherboard(Long id) {
        Optional<Motherboard> mb = mbRepo.findById(id);
        if (mb.isEmpty())
            return false;

        mbRepo.delete(mb.get());
        return true;
    }
    //
    // RAM CRUD operations
    //
    public List<RAMDto> getAllRAM(){
        return ramRepo.findAll().stream().map(RAMDto::new).toList();
    }

    public RAM addRAM(RAM ram){
        return ramRepo.save(ram);
    }

    public RAMDto getRamByID(Long id){
        return ramRepo.findById(id)
                .map(RAMDto::new)
                .orElseThrow( () -> new NotFoundException("RAM with id " + id + " not found!") );
    }

    public Optional<RAM> updateRAM(Long id, RAM source){
        Optional<RAM> existingRam = ramRepo.findById(id);

        if ( existingRam.isPresent() ){
            RAM target = existingRam.get();

            entityMapper.updateProduct(source, target);

            return Optional.of(ramRepo.save(source));
        }
        return Optional.empty();
    }

    public boolean deleteRAM(Long id){
        Optional<RAM> ram = ramRepo.findById(id);

        if (ram.isEmpty())
            return false;

        ramRepo.delete(ram.get());
        return true;
    }
}
