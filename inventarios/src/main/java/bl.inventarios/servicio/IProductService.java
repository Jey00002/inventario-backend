package bl.inventarios.servicio;


import bl.inventarios.modelo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IProductService {
        List<Product> listProducts();
        Page<Product> listProductsPaginated(Pageable pageable);
        Product findProductById(String id);
        Product saveProduct(Product product);
        void deleteProduct(String id);

}
