/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.web;

import ec.edu.espe.shalued.modelo.Bodega;
import ec.edu.espe.shalued.modelo.Vegetal;
import ec.edu.espe.shalued.servicio.BodegaServicio;
import ec.edu.espe.shalued.web.utils.BaseBean;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.beanutils.BeanUtils;
import ec.edu.espe.shalued.web.utils.FacesUtil;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author SHANE
 */
@Named
@ViewScoped
public class BodegaBean extends BaseBean implements Serializable  
{
    private static final Logger LOG = Logger.getLogger(LoteBean.class.getName());
    
    private Bodega bodega;
    private Bodega vegetal;
    private List<Bodega> bodegas;
    private List<Vegetal> vegetales;
    private Bodega bodegaSeleccionado;
    private Vegetal vegetalSeleccionado;
    
    @Inject
    private BodegaServicio bodegaServicio;
    
    
    @PostConstruct
    public void postConstruct () {
        this.bodegas = this.bodegaServicio.obtenerBodegas();
        this.vegetales = this.bodegaServicio.obtenerVegetales();
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    public Bodega getBodegaSeleccionado() {
        return bodegaSeleccionado;
    }

    public void setBodegaSeleccionado(Bodega bodegaSeleccionado) {
        this.bodegaSeleccionado = bodegaSeleccionado;
    }

    public BodegaServicio getBodegaServicio() {
        return bodegaServicio;
    }

    public void setBodegaServicio(BodegaServicio bodegaServicio) {
        this.bodegaServicio = bodegaServicio;
    }

    public Vegetal getVegetalSeleccionado() {
        return vegetalSeleccionado;
    }

    public void setVegetalSeleccionado(Vegetal vegetalSeleccionado) {
        this.vegetalSeleccionado = vegetalSeleccionado;
    }
    
    public void agregar() {
        super.startAdd();
        this.bodega = new Bodega();
        this.bodegas = this.bodegaServicio.obtenerBodegas();
                
    }
    
   public void modificar() {
        super.starModify();
        this.bodega = new Bodega();
        try {
            BeanUtils.copyProperties(this.bodega, this.bodegaSeleccionado);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            FacesUtil.addMessageError(null, "Error al copiar las propiedades.");
            LOG.log(Level.SEVERE, "Error al copiar las propiedades.", ex);
        }
        this.bodegas = this.bodegaServicio.obtenerBodegas();
    }
   
       public void eliminar() {
        this.bodegaServicio.eliminarBodega(bodegaSeleccionado);
        this.bodegas = this.bodegaServicio.obtenerBodegas();
    }
    
    public void guardar() {
        if (super.isAdd()) {
            this.bodegaServicio.crearBodega(bodega,vegetalSeleccionado);
            FacesUtil.addMessageInfo("Se ha creado la bodega:" + this.bodega.getCodigoBodega());
        } else {
            this.bodegaServicio.modificarBodega(bodega,vegetalSeleccionado);
            FacesUtil.addMessageInfo("Se ha modificado la bodega" + this.bodega.getCodigoBodega());
        }
        this.bodegas = this.bodegaServicio.obtenerBodegas();
        super.reset();
        this.bodega = null;
    }
    
    public void cancelar() {
        super.reset();
        this.bodega = null;
    }
    
     public void buscarVegetalesDisponibles() {
        vegetales = bodegaServicio.obtenerVegetales();
        if (bodegas == null) {
            warn("No Existen Vegetales Disponibles por el Momento");
        }
    }
     
     
     
     
     
     
         public void warn(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", mensaje));
    }

    public List<Vegetal> getVegetales() {
        return vegetales;
    }

    public void setVegetales(List<Vegetal> vegetales) {
        this.vegetales = vegetales;
    }
         
         
         
}
