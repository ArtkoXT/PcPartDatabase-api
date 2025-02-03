package lt.ca.javau11;

import lt.ca.javau11.entities.Manufacturer;
import lt.ca.javau11.repositories.ManufacturerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    ManufacturerRepository manufacturerRepo;

    public DataSeeder(ManufacturerRepository manufacturerRepo) {
        this.manufacturerRepo = manufacturerRepo;
    }

    @Override
    public void run(String... args) {
        if( manufacturerRepo.count() == 0 ) {
            seedManufacturers(); // Adds default manufacturers to the database if none exist
        }
    }

    public void seedManufacturers(){

        Manufacturer[] manufacturers = createManufacturers();


        //Saves all manufacturers to the database
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
}
