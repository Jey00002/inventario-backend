package bl.inventarios.controlador;

import bl.inventarios.modelo.User;
import bl.inventarios.seguridad.JwtUtil;
import bl.inventarios.servicio.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          AuthenticationManager authManager,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Si no manda rol, por defecto es USER
        if (user.getRole() == null) {
            user.setRole(User.Role.USER);
        }
        userService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        User loggedUser = (User) userService.loadUserByUsername(user.getUsername());
        String token = jwtUtil.generateToken(loggedUser.getUsername());

        // Devuelve token Y rol
        return ResponseEntity.ok(Map.of(
                "token", token,
                "role", loggedUser.getRole().name()
        ));
    }
}