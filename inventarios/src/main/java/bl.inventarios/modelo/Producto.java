package bl.inventarios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity// se crea la tabla de db

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
   String idproducto;
    @NotBlank(message = "La descripción es obligatoria")
    String descripcion;

    @Min(value = 0, message = "El precio no puede ser negativo")
    double precio;

    @NotNull(message = "La existencia es obligatoria")
    @Min(value = 0, message = "La existencia no puede ser negativa")
    Integer existencia;

//-----------------------------------------
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    //----------------------------------------


    public Producto() {
    }

    public Producto(String descripcion, Integer existencia, String idproducto, double precio) {
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.idproducto = idproducto;
        this.precio = precio;
    }
    //--------------------------------------


    @Override
    public String toString() {
        return "Producto{" +
                "descripcion='" + descripcion + '\'' +
                ", idproducto=" + idproducto +
                ", precio=" + precio +
                ", existencia=" + existencia +
                '}';
    }
}
