/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.web;

import ec.edu.espe.shalued.modelo.Pedido;
import ec.edu.espe.shalued.servicio.PedidoServicio;
import ec.edu.espe.shalued.web.utils.BaseBean;
import ec.edu.espe.shalued.web.utils.FacesUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author SHANE
 */
@Named
@ViewScoped
public class VendedorBean extends BaseBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(PedidoBean.class.getName());

    @Inject
    private PedidoServicio pedidoServicio;
    @Inject
    private credencialesBean credencialesBean;

    private List<Pedido> pedidos;
    private Pedido pedido;
    private Pedido pedidoSeleccionado;
    private String estadodep;

    @PostConstruct
    public void postContructor() {
        this.pedidos = pedidoServicio.obtenerPedidosPorCliente(credencialesBean.getClienteSesion());
    }

    public void modificar() {
        super.starModify();
        this.pedido = new Pedido();
        pedido.setEstado(estadodep);
        try {
            BeanUtils.copyProperties(this.pedido, this.pedidoSeleccionado);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            FacesUtil.addMessageError(null, "Error al copiar las propiedades.");
            LOG.log(Level.SEVERE, "Error al copiar las propiedades.", ex);
        }
        this.pedidos = this.pedidoServicio.obtenerPedidosEnEspera();
    }

    public void cancelar() {
        super.reset();
        this.pedido = null;
    }

    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }

    public String getEstadodep() {
        return estadodep;
    }

    public void setEstadodep(String estadodep) {
        this.estadodep = estadodep;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
