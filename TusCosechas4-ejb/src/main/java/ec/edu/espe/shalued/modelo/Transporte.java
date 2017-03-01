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
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author luis
 */
@Entity(value="Transporte", noClassnameStored = true)
@Indexes({@Index(fields = @Field("codigoTransporte"))})
public class Transporte extends BaseEntity{
    
    private Integer codigoTransporte;
    private String marca;
    private String modelo;
    private Integer anio;
    private Integer capacidadcarga;
    private Integer volumen;
    private String matricula;
    
    @Reference
    private Empleado conductor;

    public Transporte() {
    }

    public Integer getCodigoTransporte() {
        return codigoTransporte;
    }

    public void setCodigoTransporte(Integer codigoTransporte) {
        this.codigoTransporte = codigoTransporte;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getCapacidadcarga() {
        return capacidadcarga;
    }

    public void setCapacidadcarga(Integer capacidadcarga) {
        this.capacidadcarga = capacidadcarga;
    }

    public Integer getVolumen() {
        return volumen;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Empleado getConductor() {
        return conductor;
    }

    public void setConductor(Empleado conductor) {
        this.conductor = conductor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.codigoTransporte);
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
        final Transporte other = (Transporte) obj;
        if (!Objects.equals(this.codigoTransporte, other.codigoTransporte)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transporte{" + "codigoTransporte=" + codigoTransporte + ", marca=" + marca + ", modelo=" + modelo + ", anio=" + anio + ", capacidadcarga=" + capacidadcarga + ", volumen=" + volumen + ", matricula=" + matricula + '}';
    }
}
