/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.modelo;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Reference;

/**
 *
 * @author luis
 */
@Embedded
public class DetallePedido {
    
    private Integer codigoDetallePedido;
    private Integer cantidad;
    private Double subtotal;
    
    @Reference
    private Vegetal vegetal;

    public DetallePedido() {
    }

    public Integer getCodigoDetallePedido() {
        return codigoDetallePedido;
    }

    public void setCodigoDetallePedido(Integer codigoDetallePedido) {
        this.codigoDetallePedido = codigoDetallePedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Vegetal getVegetal() {
        return vegetal;
    }

    public void setVegetal(Vegetal vegetal) {
        this.vegetal = vegetal;
    }
  
}
