package lt.ca.javau11.repositories;

import lt.ca.javau11.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Long> {
}
