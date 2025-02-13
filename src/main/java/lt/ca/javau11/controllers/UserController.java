package lt.ca.javau11.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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
    private JwtUtils jwtUtils;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(HttpServletRequest request) {

        String jwt = jwtUtils.getJwtFromCookies(request);

        if (jwt == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No JWT token found");

        String email = jwtUtils.getEmailFromJwtToken(jwt);
        if( email == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT token");
        return null;

    }
}
