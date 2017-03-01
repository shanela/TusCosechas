/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.modelo;

import com.gestor.glabs.mongopersistence.BaseEntity;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author luis
 */
@Entity(value="Factura", noClassnameStored = true)
@Indexes({@Index(fields = @Field("codigoFactura"))})
public class Factura extends BaseEntity{
    
    private Integer codigoFactura;
    private Date fecha;
    private Double subtotal;
    private Double iva;
    private Double total;
    
    @Reference
    private Cliente cliente;
    
    @Embedded
    private List<DetalleFactura> detalleFacturaList;

    public Factura() {
    }

    public Integer getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.codigoFactura);
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
        final Factura other = (Factura) obj;
        if (!Objects.equals(this.codigoFactura, other.codigoFactura)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Factura{" + "codigoFactura=" + codigoFactura + ", fecha=" + fecha + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total + ", detalleFacturaList=" + detalleFacturaList + '}';
    }
    
    
}
