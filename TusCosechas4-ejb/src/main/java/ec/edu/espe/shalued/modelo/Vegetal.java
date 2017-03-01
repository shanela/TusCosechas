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
@Entity(value="Vegetal", noClassnameStored = true)
@Indexes({@Index(fields = @Field("codigoVegetal"))})
public class Vegetal extends BaseEntity{
    
    private Integer codigoVegetal;
    private String nombre;
    private String especie;
    private Double peso;
    private Double precio;
    private Integer cantidadcajapequenia;
    private Integer cantidadcajamediana;
    private Integer cantidadcajagrande;

    public Vegetal() {
    }

    public Integer getCodigoVegetal() {
        return codigoVegetal;
    }

    public void setCodigoVegetal(Integer codigoVegetal) {
        this.codigoVegetal = codigoVegetal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidadcajapequenia() {
        return cantidadcajapequenia;
    }

    public void setCantidadcajapequenia(Integer cantidadcajapequenia) {
        this.cantidadcajapequenia = cantidadcajapequenia;
    }

    public Integer getCantidadcajamediana() {
        return cantidadcajamediana;
    }

    public void setCantidadcajamediana(Integer cantidadcajamediana) {
        this.cantidadcajamediana = cantidadcajamediana;
    }

    public Integer getCantidadcajagrande() {
        return cantidadcajagrande;
    }

    public void setCantidadcajagrande(Integer cantidadcajagrande) {
        this.cantidadcajagrande = cantidadcajagrande;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.codigoVegetal);
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
        final Vegetal other = (Vegetal) obj;
        if (!Objects.equals(this.codigoVegetal, other.codigoVegetal)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vegetal{" + "codigoVegetal=" + codigoVegetal + ", nombre=" + nombre + ", especie=" + especie + ", peso=" + peso + ", precio=" + precio + ", cantidadcajapequenia=" + cantidadcajapequenia + ", cantidadcajamediana=" + cantidadcajamediana + ", cantidadcajagrande=" + cantidadcajagrande + '}';
    }
    
}
