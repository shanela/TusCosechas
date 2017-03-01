/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.modelo;

import java.util.Objects;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author luis
 */
@Embedded
public class Canton{
    
    private Integer codigoCanton;
    private String nombre;
    @Reference
    private Provincia provincia;

    public Canton() {
    }

    public Integer getCodigoCanton() {
        return codigoCanton;
    }

    public void setCodigoCanton(Integer codigoCanton) {
        this.codigoCanton = codigoCanton;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codigoCanton);
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
        final Canton other = (Canton) obj;
        if (!Objects.equals(this.codigoCanton, other.codigoCanton)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Canton{" + "codigoCanton=" + codigoCanton + ", nombre=" + nombre + ", provincia=" + provincia + '}';
    }   
    
}
