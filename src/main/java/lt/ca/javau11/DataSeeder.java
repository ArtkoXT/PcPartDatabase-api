package lt.ca.javau11;

import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.entities.ProductType;
import lt.ca.javau11.entities.types.CPU;
import lt.ca.javau11.entities.types.GPU;
import lt.ca.javau11.entities.types.Motherboard;
import lt.ca.javau11.entities.types.RAM;
import lt.ca.javau11.repositories.CpuRepository;
import lt.ca.javau11.repositories.GpuRepository;
import lt.ca.javau11.repositories.ManufacturerRepository;
import lt.ca.javau11.repositories.ProductTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    ManufacturerRepository manufacturerRepo;
    ProductTypeRepository prRepo;
    GpuRepository gpuRepo;
    CpuRepository cpuRepo;

    public DataSeeder(ManufacturerRepository manufacturerRepo, ProductTypeRepository prRepo, GpuRepository gpuRepo, CpuRepository cpuRepo) {
        this.manufacturerRepo = manufacturerRepo;
        this.prRepo = prRepo;
        this.gpuRepo = gpuRepo;
        this.cpuRepo = cpuRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if( manufacturerRepo.count() == 0 ) {
            seedManufacturers(); // Adds default manufacturers and their products if none exist
        }
    }

    public void seedManufacturers(){

        List<CPU> cpus = createCPUs();
        List<GPU> gpus = createGPUs();
        List<Motherboard> mobos = createMobos();
        List<RAM> ram = createRam();
        Manufacturer[] manufacturers = createManufacturers();
        ProductType[] productTypes = createProductTypes();

        productTypes[0].addCPUs(cpus);
        productTypes[1].addGPUs(gpus);
        productTypes[2].addMobos(mobos);
        productTypes[3].addAllRam(ram);
        prRepo.saveAll(Arrays.asList(productTypes));

        //Add CPUs to AMD
        manufacturers[0].addCPU(cpus.get(0));
        manufacturers[0].addCPU(cpus.get(1));

        //Add GPUs to AMD
        manufacturers[0].addGPU(gpus.get(2));
        manufacturers[0].addGPU(gpus.get(3));

        //Add Intel cpus to intel
        manufacturers[1].addCPU(cpus.get(2));
        manufacturers[1].addCPU(cpus.get(3));

        //Adds Nvidia gpus to Nvidia
        manufacturers[2].addGPU(gpus.get(0));
        manufacturers[2].addGPU(gpus.get(1));

        //Adds motherboards to their manufacturers
            //ASUS
        manufacturers[3].addMobo(mobos.get(0));
        manufacturers[3].addMobo(mobos.get(1));
            //MSI
        manufacturers[4].addMobo(mobos.get(2));
        manufacturers[4].addMobo(mobos.get(3));

        //Adds RAM to their manufacturers
            //G.Skill
        manufacturers[5].addRam(ram.get(0));
        manufacturers[5].addRam(ram.get(3));
            //Corsair
        manufacturers[6].addRam(ram.get(1));
        manufacturers[6].addRam(ram.get(2));

        //Saves all manufacturers and gpus to the database
        manufacturerRepo.saveAll(Arrays.asList(manufacturers));

    }

    public Manufacturer[] createManufacturers(){
        Manufacturer[] manufacturers = {
                new Manufacturer("AMD"),
                new Manufacturer("Intel"),
                new Manufacturer("NVIDIA"),
                new Manufacturer("Asus"),
                new Manufacturer("MSI"),
                new Manufacturer("G.Skill"),
                new Manufacturer("Corsair")
        };
        return manufacturers;
    }

    public ProductType[] createProductTypes(){
        ProductType[] productTypes = {
                new ProductType("CPUs","https://t3.ftcdn.net/jpg/00/81/24/72/360_F_81247213_OYvGTCn5mnQQ2c0gWJ1U5ixcbmNBaMOp.jpg"),
                new ProductType("Graphic Cards","https://media.istockphoto.com/id/1180632542/photo/game-graphics-card-isolated-on-white-background.jpg?s=612x612&w=0&k=20&c=jgLiheKNlcuB4_D0ZGO9GdjouRQWPmPsIjP7jh-ivCk="),
                new ProductType("Motherboards","https://static.trackalacker.com/cdn-cgi/image/fit=pad,width=600,height=600,quality=85,format=auto/uploads/products/listings/photo_item/photo/102179/asus-pro-ws-w680-ace-ipmi-lga-1700-atx-motherboard.jpeg"),
                new ProductType("Memory","https://st2.depositphotos.com/2454597/9931/i/950/depositphotos_99312556-stock-photo-modern-ram-memory-modules-with.jpg")
        };
        return productTypes;
    }
    public List<CPU> createCPUs(){
        //Create AMD CPUs

        CPU cpu1 = new CPU(
                "Ryzen 7 7800X3D", "4.2 GHz",
                "5.0 GHz",
                "120 W",
                "AMD Radeon™ Graphics",
                "Zen 4",
                "AM5",
                "8",
                "16",
                "8 MB",
                "96 MB"
        );

        CPU cpu2 = new CPU(
            "Ryzen 7 9800X3D",
            "4.7 GHz",
            "5.2 GHz",
            "120 W",
            "AMD Radeon™ Graphics",
            "Zen 5",
            "AM5",
            "8",
            "16",
            "8 MB",
            "96 MB"
        );

        //Create Intel CPUs

        CPU cpu3 = new CPU(
            "i7 14700K",
            "3.4 GHz",
            "5.6 GHz",
            "125 W",
            "UHD Graphics 770",
            "Raptor Lake-R",
            "FCLGA1700",
            "20",
            "28",
            "28 MB",
            "33 MB"
        );

        CPU cpu4 = new CPU(
            "i7 13700K",
            "3.4 GHz",
            "5.4 GHz",
            "125 W",
            "UHD Graphics 770",
            "Raptor Lake-S",
            "FCLGA1700",
            "16",
            "24",
            "24 MB",
            "30 MB"
        );

        return List.of(cpu1, cpu2, cpu3 ,cpu4);
    }

    public List<GPU> createGPUs(){

        //Create NVIDIA GPUs

        GPU gpu1 = new GPU(
            "GeForce RTX 4080",
            "AD103",
            "Ada Lovelace",
            "9728",
            "320 W",
            "2205 MHz",
            "2505 MHz",
            "1400 MHz",
            "16 GB",
            "GDDR6X",
            "256 bit",
            "716.8 GB/s"
        );

        GPU gpu2 = new GPU(
            "GeForce RTX 3080",
            "GA102",
            "Ampere",
            "8704",
            "320 W",
            "1440 MHz",
            "1710 MHz",
            "1188 MHz",
            "10 GB",
            "GDDR6X",
            "320 bit",
            "760.3 GB/s"
        );

        //Create AMD GPUs

        GPU gpu3 = new GPU(
            "Radeon RX 7900 XTX",
            "Navi 31",
            "RDNA 3.0",
            "6144",
            "355 W",
            "1929 MHz",
            "2498 MHz",
            "2500 MHz",
            "24 GB",
            "GDDR6",
            "384 bit",
            "960.0 GB/s"
        );

        GPU gpu4 = new GPU(
            "Radeon RX 6950 XT",
            "Navi 21",
            "RDNA 2.0",
            "5120",
            "335 W",
            "1860 MHz",
            "2310 MHz",
            "2250 MHz",
            "16 GB",
            "GDDR6X",
            "256 bit",
            "567.0 GB/s"
        );

        return List.of(gpu1, gpu2, gpu3, gpu4);

    }

    public List<Motherboard> createMobos(){

        Motherboard motherboard1 = new Motherboard(
                "ROG STRIX Z790-A GAMING WIFI",
                "LGA1700",
                "ATX",
                "Intel Z790",
                "192 GB",
                "DDR5",
                "4",
                "3",
                "4",
                "2",
                "2",
                "Wi-Fi 6E"
                );
        Motherboard motherboard2 = new Motherboard(
                "ROG STRIX X870E-E GAMING WIFI",
                "AM5",
                "ATX",
                "AMD X870E",
                "192 GB",
                "DDR5",
                "4",
                "2",
                "5",
                "3",
                "3",
                "Wi-Fi 7"
        );

        Motherboard motherboard3 = new Motherboard(
                "MPG Z790 CARBON WIFI",
                "LGA1700",
                "ATX",
                "Intel Z790",
                "192 GB",
                "DDR5",
                "4",
                "2",
                "5",
                "2",
                "2",
                "Wi-Fi 6E"
        );

        Motherboard motherboard4 = new Motherboard(
                "MEG X870E GODLIKE EATX",
                "AM5",
                "EATX",
                "AMD X870E",
                "256 GB",
                "DDR5",
                "4",
                "2",
                "5",
                "2",
                "4",
                "Wi-Fi 7"
        );

        return List.of(motherboard1, motherboard2, motherboard3, motherboard4);
    }

    public List<RAM> createRam(){

        RAM ram1 = new RAM(
                "Trident Z5 RGB 64 GB",
                "64 GB",
                "DDR5-6400",
                "288-pin DIMM",
                "2 x 32 GB",
                "32",
                "32-39-39-102"
        );

        RAM ram2 = new RAM(
                "Vengeance 32 GB",
                "32 GB",
                "DDR5-6000",
                "288-pin DIMM",
                "2 x 16 GB",
                "30",
                "30-36-36-76"
        );

        RAM ram3 = new RAM(
                "Vengeance LPX 16 GB",
                "16 GB",
                "DDR4-3200",
                "288-pin DIMM",
                "2 x 8 GB",
                "16",
                "16-18-18-36"
        );

        RAM ram4 = new RAM(
                "Ripjaws V 32 GB",
                "32 GB",
                "DDR4-3200",
                "288-pin DIMM",
                "2 x 16 GB",
                "16",
                "16-18-18-38"
        );

        return List.of(ram1, ram2, ram3, ram4);
    }
}
