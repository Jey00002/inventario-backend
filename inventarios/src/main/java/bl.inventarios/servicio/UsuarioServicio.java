package bl.inventarios.servicio;

import bl.inventarios.Repositorio.UsuarioRepository;
import bl.inventarios.modelo.Usuario;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UsuarioServicio implements IUsuarioServicio {

    private final UsuarioRepository usuarioRepository;

    // Ya no va en el constructor
    @Lazy
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioServicio(UsuarioRepository usuarioRepository,
                           @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + username));
    }

    public Usuario registrar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
}