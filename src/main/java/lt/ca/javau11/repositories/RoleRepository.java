package lt.ca.javau11.repositories;

import lt.ca.javau11.entities.ERole;
import lt.ca.javau11.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
