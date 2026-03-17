package bl.inventarios.Repositorio;

import bl.inventarios.modelo.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
