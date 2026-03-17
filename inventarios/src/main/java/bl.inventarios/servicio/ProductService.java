package bl.inventarios.servicio;

import bl.inventarios.Repositorio.ProductoRepository;
import bl.inventarios.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> listarProductos() {
        return this.productoRepository.findAll();
    }

    @Override
    public Page<Producto> listarProductosPaginados(Pageable pageable) {
        return this.productoRepository.findAll(pageable);
    }

    @Override
    public Producto buscarProductoPorID(String idproducto) {
        return this.productoRepository.findById(idproducto).orElse(null);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void borrarProducto(String idproducto) {
        this.productoRepository.deleteById(idproducto);
    }
}