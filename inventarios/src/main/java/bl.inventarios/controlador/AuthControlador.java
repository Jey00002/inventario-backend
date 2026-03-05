package bl.inventarios.controlador;

import bl.inventarios.modelo.Usuario;
import bl.inventarios.seguridad.JwtUtil;
import bl.inventarios.servicio.UsuarioServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthControlador {

    private final UsuarioServicio usuarioServicio;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthControlador(UsuarioServicio usuarioServicio,
                           AuthenticationManager authManager,
                           JwtUtil jwtUtil) {
        this.usuarioServicio = usuarioServicio;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    // POST /auth/register — crea un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        usuarioServicio.registrar(usuario);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    // POST /auth/login — devuelve el token JWT
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        // Verifica usuario y contraseña
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuario.getUsername(),
                        usuario.getPassword()
                )
        );
        // Si llegó aquí, las credenciales son correctas
        UserDetails userDetails = usuarioServicio
                .loadUserByUsername(usuario.getUsername());

        // Genera y devuelve el token
        String token = jwtUtil.generarToken(userDetails.getUsername());
        return ResponseEntity.ok(token);
    }
}