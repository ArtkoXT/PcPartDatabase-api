package lt.ca.javau11.controllers;

import lombok.AllArgsConstructor;
import lt.ca.javau11.entities.User;
import lt.ca.javau11.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    UserService userService;


}
