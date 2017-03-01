/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.modelo;

import com.gestor.glabs.mongopersistence.BaseEntity;
import java.util.Date;
import java.util.Objects;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author luis
 */
@Entity(value="Bodega", noClassnameStored = true)
@Indexes({@Index(fields = @Field("codigoBodega"))})
public class Bodega extends BaseEntity{
    
    private Integer codigoBodega;
    private Integer cantidad;
    private Date fechaingreso;
    private Date fechacaducidad;
    private String estado;
    
    @Reference
    private Lote lote;
    @Reference
    private Vegetal vegetal;

    public Bodega() {
    }

    public Integer getCodigoBodega() {
        return codigoBodega;
    }

    public void setCodigoBodega(Integer codigoBodega) {
        this.codigoBodega = codigoBodega;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public Date getFechacaducidad() {
        return fechacaducidad;
    }

    public void setFechacaducidad(Date fechacaducidad) {
        this.fechacaducidad = fechacaducidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Vegetal getVegetal() {
        return vegetal;
    }

    public void setVegetal(Vegetal vegetal) {
        this.vegetal = vegetal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.codigoBodega);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bodega other = (Bodega) obj;
        if (!Objects.equals(this.codigoBodega, other.codigoBodega)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bodega{" + "codigoBodega=" + codigoBodega + ", cantidad=" + cantidad + ", fechaingreso=" + fechaingreso + ", fechacaducidad=" + fechacaducidad + ", estado=" + estado + ", lote=" + lote + ", vegetal=" + vegetal + '}';
    }
}
