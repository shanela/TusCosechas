/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.web;

import ec.edu.espe.shalued.modelo.DetalleFactura;
import ec.edu.espe.shalued.modelo.Factura;
import ec.edu.espe.shalued.servicio.FacturaServicio;
import ec.edu.espe.shalued.web.utils.BaseBean;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author SHANE
 */
@Named
@ViewScoped
public class FacturaBean extends BaseBean implements Serializable  
{
    private static final Logger LOG = Logger.getLogger(LoteBean.class.getName());
    
     @Inject
    private FacturaServicio facturaServicio;
    @Inject
    private credencialesBean credencialesBean;
    
    private List<Factura> facturas;
    private Factura facturaSelected;
    private List<DetalleFactura> detalleFactura;
    
    @PostConstruct
    public void postContructor() 
    {
        obtenerFacturasPorCliente();
    }
    
    public void obtenerFacturasPorCliente()
    {    
       this.facturas = facturaServicio.obtenerFacturasporCliente(credencialesBean.getClienteSesion());
    }
    
     public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Factura getFacturaSelected() {
        return facturaSelected;
    }

    public void setFacturaSelected(Factura facturaSelected) {
        this.facturaSelected = facturaSelected;
    }

    public List<DetalleFactura> getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(List<DetalleFactura> detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
    
   
    
  //Mensajes
    public void info(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, ""));
    }

    public void warn(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", mensaje));
    }

    public void error(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", mensaje));
    }

    public void fatal(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", mensaje));
    }
    
}
