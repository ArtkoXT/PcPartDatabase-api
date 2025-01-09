package lt.ca.javau11.repositories;

import lt.ca.javau11.entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository  extends JpaRepository<Manufacturer, Long> {

}
