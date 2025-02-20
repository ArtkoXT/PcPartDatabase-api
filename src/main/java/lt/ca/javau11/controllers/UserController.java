package lt.ca.javau11.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.Topic;
import lt.ca.javau11.entities.User;
import lt.ca.javau11.security.jwt.JwtUtils;
import lt.ca.javau11.security.services.UserDetailsImpl;
import lt.ca.javau11.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
