/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.web;

import ec.edu.espe.shalued.modelo.Bodega;
import ec.edu.espe.shalued.modelo.Canton;
import ec.edu.espe.shalued.modelo.Cliente;
import ec.edu.espe.shalued.modelo.DetallePedido;
import ec.edu.espe.shalued.modelo.Direccion;
import ec.edu.espe.shalued.modelo.Pedido;
import ec.edu.espe.shalued.modelo.Provincia;
import ec.edu.espe.shalued.modelo.Vegetal;
import ec.edu.espe.shalued.servicio.BodegaServicio;
import ec.edu.espe.shalued.servicio.PedidoServicio;
import ec.edu.espe.shalued.servicio.UbicacionServicio;
import ec.edu.espe.shalued.web.utils.BaseBean;
import ec.edu.espe.shalued.web.utils.FacesUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author SHANE
 */
@Named
@ViewScoped
public class PedidoBean extends BaseBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(PedidoBean.class.getName());

    //llamo a servicios
    @Inject
    private PedidoServicio pedidoServicio;
    @Inject
    private UbicacionServicio ubicacionServicio;
    @Inject
    private BodegaServicio bodegaServicio;
    @Inject
    private credencialesBean credencialesBean;

    //Listas 
    private List<Provincia> provincias;
    private List<Canton> cantones;
    private List<Pedido> pedidos;
    private List<Bodega> bodegas;
    private List<Pedido> estadosPedido;
    private List<Pedido> pedidosEmpleado;
    
    //Objetos
    private Map<DetallePedido, Bodega> asignacionBodegaDetalle;
    private Vegetal vegetal;
    private Bodega bodegaSelected;
    private Long cantonSelectedId;
    private Integer provinciaSelectedId;
    private Pedido pedido;
    private Cliente cliente;
    private Integer cant = 1;
    private Long idTemp = 1L;
    private Pedido pedidoSeleccionado;
    private Pedido pedidoEstadoSeleccionado;
    private Provincia provincia;
    private Canton canton;
    private String cantonNombre;

    public Vegetal getVegetal() {
        return vegetal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @PostConstruct
    public void postContructor() {
 //       this.provincias = this.ubicacionServicio.obtenerTodasProvincias();
        this.cantones = this.ubicacionServicio.obtenerTodasCantones();
        this.pedidos = this.pedidoServicio.obtenertodoslosPedidos();
        this.bodegas = bodegaServicio.obtenerVegetalesdisponibles();
        this.pedido = new Pedido();
        this.asignacionBodegaDetalle = new HashMap<>();
        this.pedidos= pedidoServicio.obtenerPedidosPorCliente(credencialesBean.getClienteSesion());
        Direccion d = new Direccion();
        pedido.setDireccion(d);
    }

    public void consultarBodegaDisponibilidad() {
        this.bodegas = bodegaServicio.obtenerVegetalesdisponibles();
    }

    //Funciones
    public void buscarVegetalesDisponibles() {
        bodegas = bodegaServicio.obtenerVegetalesdisponibles();
        if (bodegas == null) {
            warn("No Existen Vegetales Disponibles por el Momento");
        }
    }

    public void cargarCantonesporProvincia() {
        if (provinciaSelectedId != null) {
            Provincia p = new Provincia(provinciaSelectedId);
            this.cantones = ubicacionServicio.obtenerCantonesporProvincia(p);
        } else {
            cantones = null;
        }
    }
                            public void cargarEstadosVendedor() 
                            {
                                this.pedidos= pedidoServicio.obtenerEstados(pedidoEstadoSeleccionado);
                            }

                            public void cargarEstadosConductor() 
                            {

                                this.pedidos= pedidoServicio.obtenerEstados(pedidoEstadoSeleccionado);
                            }
      
    public void agregarNuevoItemVegetal() {
        Vegetal aux = bodegaSelected.getVegetal();
        if (aux != null) {
            DetallePedido detalle = new DetallePedido();
            detalle.setVegetal(aux);
            detalle.setCantidad(cant);
            detalle.setSubtotal((cant * aux.getPrecio()));

            if (pedido.getDetalle()== null) {
                pedido.setDetalle(new ArrayList<DetallePedido>());
            }
             pedido.getDetalle().add(detalle);
            asignacionBodegaDetalle.put(detalle, bodegaSelected);
            RequestContext.getCurrentInstance().execute("PF('dlg1').hide();");

            bodegaSelected = null;
            cant = 1;
        }

    }

    public void crearNuevoPedido() {
        Canton c = new Canton();
        c.setNombre(cantonNombre);
        pedido.getDireccion().setCanton(canton);
        pedido.setFecha(new Date());
        pedido.setCliente(credencialesBean.getClienteSesion());

        if (pedido.getDetalle()!= null && !pedido.getDetalle().isEmpty()) {
            if (pedidoServicio.guardarPedido(pedido, asignacionBodegaDetalle)) {
                info("El Pedido se a Guardado con Exito");
                // RequestContext.getCurrentInstance().execute("PF('dlg1').hide();");   // ejecutar javascrip

            }
        }
        pedido = new Pedido();
    }
    
    
       public void guardar() {
        if (super.isModify()) {
            this.pedidoServicio.modificar(pedido);
            FacesUtil.addMessageInfo("Se ha modificado el Pedido:" + this.pedido.getCodigoPedido());
        }
        this.pedidos = this.pedidoServicio.obtenerPedidosEnEspera();
        super.reset();
        this.pedido = null;
    }
    
    public String cambioEtiquetaEstadoPedido2(String estadoPedido) {
        if ("APROB".equals(estadoPedido)) {
            return "Aprobado";
        } else if ("ESPER".equals(estadoPedido)) {
            return "En Espera";
        } else if ("RECHA".equals(estadoPedido)) {
            return "Rechazado";
        } else if ("ENCAM".equals(estadoPedido)) {
            return "En Camino";
        } else if ("ENTRE".equals(estadoPedido)) {
            return "Entregado";
        }
        return "";
    }
    
   public void modificar() {
        super.starModify();
        this.pedido = new Pedido();
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
    
//    public PedidoEnum[] getValorEstados() {
//        return PedidoEnum.values();
//    }
//    
    public void setCant(Integer cant) {
        this.cant = cant;
    }

    public Integer getCant() {
        return cant;
    }

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<Canton> getCantones() {
        return cantones;
    }

    public void setCantones(List<Canton> cantones) {
        this.cantones = cantones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Bodega> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<Bodega> bodegas) {
        this.bodegas = bodegas;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getCantonSelectedId() {
        return cantonSelectedId;
    }

    public void setCantonSelectedId(Long cantonSelectedId) {
        this.cantonSelectedId = cantonSelectedId;
    }

    public Integer getProvinciaSelectedId() {
        return provinciaSelectedId;
    }

    public void setProvinciaSelectedId(Integer provinciaSelectedId) {
        this.provinciaSelectedId = provinciaSelectedId;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }
    

    public Bodega getBodegaSelected() {
        return bodegaSelected;
    }

    public void setBodegaSelected(Bodega bodegaSelected) {
        this.bodegaSelected = bodegaSelected;
    }

    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }

    public Pedido getPedidoEstadoSeleccionado() {
        return pedidoEstadoSeleccionado;
    }

    public void setPedidoEstadoSeleccionado(Pedido pedidoEstadoSeleccionado) {
        this.pedidoEstadoSeleccionado = pedidoEstadoSeleccionado;
    }

    public List<Pedido> getEstadosPedido() {
        return estadosPedido;
    }

    public void setEstadosPedido(List<Pedido> estadosPedido) {
        this.estadosPedido = estadosPedido;
    }

    public List<Pedido> getPedidosEmpleado() {
        return pedidosEmpleado;
    }

    public void setPedidosEmpleado(List<Pedido> pedidosEmpleado) {
        this.pedidosEmpleado = pedidosEmpleado;
    }

    public String getCantonNombre() {
        return cantonNombre;
    }

    public void setCantonNombre(String cantonNombre) {
        this.cantonNombre = cantonNombre;
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
