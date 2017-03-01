/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.modelo;

import com.gestor.glabs.mongopersistence.BaseEntity;
import java.util.List;
import java.util.Objects;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

/**
 *
 * @author luis
 */
@Entity(value="Provincia", noClassnameStored = true)
@Indexes({@Index(fields = @Field("codigoProvincia"))})
public class Provincia extends BaseEntity{
    
    private Long codigoProvincia;
    private String nombre;
    
    @Embedded
    private List<Canton> catones;

    public Provincia() {
    }

 public Provincia(Long proCodigo) {
        this.codigoProvincia = proCodigo;
    }

    public Long getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(Long codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Canton> getCatones() {
        return catones;
    }

    public void setCatones(List<Canton> catones) {
        this.catones = catones;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.codigoProvincia);
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
        final Provincia other = (Provincia) obj;
        if (!Objects.equals(this.codigoProvincia, other.codigoProvincia)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Provincia{" + "codigoProvincia=" + codigoProvincia + ", nombre=" + nombre + ", catones=" + catones + '}';
    }
    
}
