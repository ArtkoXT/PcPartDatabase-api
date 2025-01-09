package lt.ca.javau11.repositories;

import lt.ca.javau11.entities.types.GPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpuRepository extends JpaRepository<GPU, Long> {
}
