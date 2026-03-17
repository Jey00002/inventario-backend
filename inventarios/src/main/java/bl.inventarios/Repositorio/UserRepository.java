package bl.inventarios.Repositorio;

import bl.inventarios.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    // Spring Data genera este método automáticamente
    // solo con escribir el nombre correcto
    Optional<Usuario> findByUsername(String username);
}
