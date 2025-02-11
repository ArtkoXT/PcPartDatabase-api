package lt.ca.javau11.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
