package lt.ca.javau11.repositories;

import lt.ca.javau11.entities.types.RAM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepository extends JpaRepository<RAM, Long> {
}
