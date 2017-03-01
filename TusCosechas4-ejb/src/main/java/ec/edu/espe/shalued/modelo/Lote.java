/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.modelo;

import com.gestor.glabs.mongopersistence.BaseEntity;
import java.util.Objects;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

/**
 *
 * @author luis
 */
@Entity(value="Lote", noClassnameStored = true)
@Indexes({@Index(fields = @Field("codigoLote"))})
public class Lote extends BaseEntity{
    
    private Integer codigoLote;
    private String nombre;
    private Integer area;

    public Lote() {
    }

    public Integer getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(Integer codigoLote) {
        this.codigoLote = codigoLote;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.codigoLote);
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
        final Lote other = (Lote) obj;
        if (!Objects.equals(this.codigoLote, other.codigoLote)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lote{" + "codigoLote=" + codigoLote + ", nombre=" + nombre + ", area=" + area + '}';
    }
}
