package lt.ca.javau11.repositories;

import lt.ca.javau11.entities.types.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
}
