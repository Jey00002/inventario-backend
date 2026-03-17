package bl.inventarios.Repositorio;

import bl.inventarios.modelo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data genera este método automáticamente
    // solo con escribir el nombre correcto
    Optional<User> findByUsername(String username);
}
