package lt.ca.javau11.repositories;

import lt.ca.javau11.entities.Category;
import lt.ca.javau11.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, Long> {

    List<Component> findByCategory(Category category);
}
