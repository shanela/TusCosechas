package ec.edu.espe.shalued.web;

import ec.edu.espe.shalued.modelo.Lote;
import ec.edu.espe.shalued.servicio.LoteServicio;
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
public class LoteBean extends BaseBean implements Serializable  {
    
    private static final Logger LOG = Logger.getLogger(LoteBean.class.getName());
    
    private Lote lote;
    private List<Lote> lotes;
    private Lote loteSeleccionado;
    
    @Inject
    private LoteServicio loteServicio;
    
    @PostConstruct
    public void postConstruct () {
        this.lotes = this.loteServicio.obtenerLotes();
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public Lote getLoteSeleccionado() {
        return loteSeleccionado;
    }

    public void setLoteSeleccionado(Lote loteSeleccionado) {
        this.loteSeleccionado = loteSeleccionado;
    }
    
    public void agregar() {
        super.startAdd();
        this.lote = new Lote();
        this.lotes = this.loteServicio.obtenerLotes();
        
    }
    
    public void modificar() {
        super.starModify();
        this.lote = new Lote();
        try {
            BeanUtils.copyProperties(this.lote, this.loteSeleccionado);
           } catch (IllegalAccessException | InvocationTargetException ex) {
            FacesUtil.addMessageError(null, "Error al copiar las propiedades.");
            LOG.log(Level.SEVERE, "Error al copiar las propiedades.", ex);
        }
        this.lotes = this.loteServicio.obtenerLotes();
    }
    
    public void eliminar() {
        this.loteServicio.eliminarLote(loteSeleccionado);
        this.lotes = this.loteServicio.obtenerLotes();
    }
    
    public void guardar() {
        if (super.isAdd()) {
            this.loteServicio.crear(lote);
            FacesUtil.addMessageInfo("Se ha creado el usuario:" + this.lote.getNombre());
        } else {
            this.loteServicio.modificar(lote);
            FacesUtil.addMessageInfo("Se ha modificado el usuario:" + this.lote.getNombre());
        }
        this.lotes = this.loteServicio.obtenerLotes();
        super.reset();
        this.lote = null;
    }
    
    public void cancelar() {
        super.reset();
        this.lote = null;
    }
    
}
