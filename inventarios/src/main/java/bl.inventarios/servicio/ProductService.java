package bl.inventarios.servicio;

import bl.inventarios.Repositorio.ProductRepository;
import bl.inventarios.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Page<Product> listProductsPaginated(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Product findProductById(String id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        this.productRepository.deleteById(id);
    }
}