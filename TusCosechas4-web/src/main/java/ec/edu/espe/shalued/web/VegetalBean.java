package ec.edu.espe.shalued.web;

import ec.edu.espe.shalued.modelo.Vegetal;
import ec.edu.espe.shalued.servicio.VegetalServicio;
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
public class VegetalBean extends BaseBean implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(VegetalBean.class.getName());
    
    private Vegetal vegetal;
    private List<Vegetal> vegetales;
    private Vegetal vegetalSeleccionado;
    
    @Inject
    private VegetalServicio vegetalServicio;
    
    @PostConstruct
    public void postConstruct () {
        this.vegetales = this.vegetalServicio.obtenerVegetal();
    }

    public Vegetal getVegetal() {
        return vegetal;
    }

    public void setVegetal(Vegetal vegetal) {
        this.vegetal = vegetal;
    }

    public List<Vegetal> getVegetales() {
        return vegetales;
    }

    public Vegetal getVegetalSeleccionado() {
        return vegetalSeleccionado;
    }

    public void setVegetalSeleccionado(Vegetal vegetalSeleccionado) {
        this.vegetalSeleccionado = vegetalSeleccionado;
    }
    
    public void agregar() {
        super.startAdd();
        this.vegetal = new Vegetal();
        this.vegetales = this.vegetalServicio.obtenerVegetal();
        
    }
    
    public void modificar() {
        super.starModify();
        this.vegetal = new Vegetal();
        try {
            BeanUtils.copyProperties(this.vegetal, this.vegetalSeleccionado);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            FacesUtil.addMessageError(null, "Error al copiar las propiedades.");
            LOG.log(Level.SEVERE, "Error al copiar las propiedades.", ex);
        }
        this.vegetales = this.vegetalServicio.obtenerVegetal();
    }
    
    public void eliminar() {
        this.vegetalServicio.eliminarVegetal(vegetalSeleccionado);
        this.vegetales = this.vegetalServicio.obtenerVegetal();
    }
    
    public void guardar() {
        if (super.isAdd()) {
            this.vegetalServicio.crear(vegetal);
            FacesUtil.addMessageInfo("Se ha creado el vegetal:" + this.vegetal.getNombre());
        } else {
            this.vegetalServicio.modificar(vegetal);
            FacesUtil.addMessageInfo("Se ha modificado el vegetal:" + this.vegetal.getNombre());
        }
        this.vegetales = this.vegetalServicio.obtenerVegetal();
        super.reset();
        this.vegetal = null;
    }
    
    public void cancelar() {
        super.reset();
        this.vegetal = null;
    }
    
}
