package lt.ca.javau11.services;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.User;
import lt.ca.javau11.entities.mappers.EntityMapper;
import lt.ca.javau11.exceptions.UserAlreadyExistsException;
import lt.ca.javau11.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepo;
    EntityMapper entityMapper;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserByID(Long id) {
        return userRepo.findById(id);
    }

    public User addUser(User user) {
        if(userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Error, user with this email already exists");
        }
        return userRepo.save(user);
    }

    public Optional<User> updateUser(User source, Long id) {
        Optional<User> userToUpdate = userRepo.findById(id);

        if(userToUpdate.isPresent()) {
            User target = userToUpdate.get();
            entityMapper.updateUser(source, target);
            return Optional.of(userRepo.save(target));
        }

        return Optional.empty();

    }

    public boolean deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);

        if(user.isEmpty())
            return false;

        userRepo.delete(user.get());
        return true;
    }

}
