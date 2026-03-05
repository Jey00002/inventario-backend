package bl.inventarios.controlador;

import bl.inventarios.modelo.Producto;
import bl.inventarios.servicio.ProductoServicio;
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
@RequestMapping("inventario-app")             //http://localhost:8082/inventario-app
@CrossOrigin(value = "*") // puerto aleatorio de ANGULAR
public class ProductoControlador {
   private static final Logger logger = LoggerFactory.getLogger(ProductoControlador.class);

   @Autowired
   private ProductoServicio productoServicio;

   @GetMapping("/productos")//http://localhost:8080/inventario-app/productos
   public List<Producto> obtenerProductos(){
       List<Producto> productos= this.productoServicio.listarProductos();
       logger.info("Productos obtenidos:");
       productos.forEach(producto -> logger.info(producto.toString()));
       return productos;
   }

    @DeleteMapping("/productos/{id}")
    public void eliminarProducto(@PathVariable String id) {
        logger.info("Producto a eliminar id: " + id);
        this.productoServicio.borrarProducto(id);
    }

    @PostMapping("/productos")
    public Producto agregarProducto(@Valid @RequestBody Producto producto) {
        logger.info("Producto a agregar: " + producto.toString());
        return this.productoServicio.guardarProducto(producto);
    }

    @PutMapping("/productos/{id}")
    public Producto actualizarProducto(@PathVariable String id,
                                       @Valid @RequestBody Producto producto) {
        producto.setIdproducto(id);
        logger.info("Producto a actualizar: " + producto.toString());
        return this.productoServicio.guardarProducto(producto);
    }

    @GetMapping("/productos/paginado")
    public Page<Producto> obtenerProductosPaginados(
            @PageableDefault(size = 10) Pageable pageable) {
        return this.productoServicio.listarProductosPaginados(pageable);
    }
}
