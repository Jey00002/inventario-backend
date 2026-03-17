package bl.inventarios.controlador;

import bl.inventarios.modelo.Product;
import bl.inventarios.servicio.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("inventario-app")
@CrossOrigin(value = "http://localhost:3000/")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> products = this.productService.listProducts();
        logger.info("Products retrieved:");
        products.forEach(product -> logger.info(product.toString()));
        return products;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        logger.info("Deleting product with id: " + id);
        this.productService.deleteProduct(id);
    }

    @PostMapping("/products")
    public Product addProduct(@Valid @RequestBody Product product) {
        logger.info("Adding product: " + product.toString());
        return this.productService.saveProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable String id,
                                 @Valid @RequestBody Product product) {
        product.setId(id);
        logger.info("Updating product: " + product.toString());
        return this.productService.saveProduct(product);
    }

    @GetMapping("/products/paginated")
    public Page<Product> getProductsPaginated(
            @PageableDefault(size = 10) Pageable pageable) {
        return this.productService.listProductsPaginated(pageable);
    }
}