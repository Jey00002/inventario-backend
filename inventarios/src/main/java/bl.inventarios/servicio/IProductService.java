package bl.inventarios.servicio;

import bl.inventarios.modelo.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IProductoServicio {
    List<Producto> listarProductos();
    Page<Producto> listarProductosPaginados(Pageable pageable);
    Producto buscarProductoPorID(String idproducto);
    Producto guardarProducto(Producto producto);
    void borrarProducto(String idproducto);
}
