package lt.ca.javau11.repositories;

import lt.ca.javau11.entities.types.CPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepository extends JpaRepository<CPU,Long> {

}
