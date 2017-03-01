package ec.edu.espe.shalued.web;

import ec.edu.espe.shalued.modelo.Transporte;
import ec.edu.espe.shalued.servicio.TransporteServicio;
import ec.edu.espe.shalued.web.utils.BaseBean;
import ec.edu.espe.shalued.web.utils.FacesUtil;
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

/**
 *
 * @author 9egrgti
 */
@Named
@ViewScoped
public class TransporteBean extends BaseBean implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(TransporteBean.class.getName());
    
    private Transporte transporte;
    private List<Transporte> transportes;
    private Transporte transporteSeleccionado;
    
    @Inject
    private TransporteServicio transporteServicio;
    
    @PostConstruct
    public void postConstruct () {
        this.transportes = this.transporteServicio.obtenerTransportes();
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public List<Transporte> getTransportes() {
        return transportes;
    }

    public Transporte getTransporteSeleccionado() {
        return transporteSeleccionado;
    }

    public void setTransporteSeleccionado(Transporte transporteSeleccionado) {
        this.transporteSeleccionado = transporteSeleccionado;
    }

    public void agregar() {
        super.startAdd();
        this.transporte = new Transporte();
        this.transportes = this.transporteServicio.obtenerTransportes();
        
    }
    
    public void modificar() {
        super.starModify();
        this.transporte = new Transporte();
        try {
            BeanUtils.copyProperties(this.transporte, this.transporteSeleccionado);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            FacesUtil.addMessageError(null, "Error al copiar las propiedades.");
            LOG.log(Level.SEVERE, "Error al copiar las propiedades.", ex);
        }
        this.transportes = this.transporteServicio.obtenerTransportes();
    }
    
    public void eliminar() {
        
        this.transportes = this.transporteServicio.obtenerTransportes();
    }
    
    public void guardar() {
        if (super.isAdd()) {
            this.transporteServicio.crear(transporte);
            FacesUtil.addMessageInfo("Se ha creado el vegetal:" + this.transporte.getMarca() + " - " + this.transporte.getModelo());
        } else {
            this.transporteServicio.modificar(transporte);
            FacesUtil.addMessageInfo("Se ha modificado el vegetal:" + this.transporte.getMarca() + " - " + this.transporte.getModelo());
        }
        this.transportes = this.transporteServicio.obtenerTransportes();
        super.reset();
        this.transporte = null;
    }
    
    public void cancelar() {
        super.reset();
        this.transporte = null;
    }
    
}
