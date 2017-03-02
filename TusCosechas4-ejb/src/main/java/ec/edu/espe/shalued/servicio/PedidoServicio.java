/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Bodega;
import ec.edu.espe.shalued.modelo.Cliente;
import ec.edu.espe.shalued.modelo.Dao.BodegaDao;
import ec.edu.espe.shalued.modelo.Dao.DetallePedidoDao;
import ec.edu.espe.shalued.modelo.Dao.PedidoDao;
import ec.edu.espe.shalued.modelo.DetallePedido;
import ec.edu.espe.shalued.modelo.Empleado;
import ec.edu.espe.shalued.modelo.Pedido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 *
 * @author SHANE
 */
@Stateless
@LocalBean
public class PedidoServicio implements Serializable {

    private static final Logger LOG = Logger.getLogger(VegetalServicio.class.getName());
    MongoPersistence mp;

    private PedidoDao pedidoDao;
    private DetallePedidoDao detallePedidoDao;
    private BodegaDao bodegaDao;

    @PostConstruct
    public void postConstruct() {
        mp = new MongoPersistence();
        bodegaDao = new BodegaDao(Bodega.class, mp.context());
        pedidoDao = new PedidoDao(Pedido.class, mp.context());
        detallePedidoDao = new DetallePedidoDao(DetallePedido.class, mp.context());
    }

    public List<Pedido> obtenerPedidosEnEspera() {
        String estado = "ESPER";
        return pedidoDao.createQuery().filter("estado =", estado).asList();
    }
    
    public List<Pedido> obtenerPedidosEnCamino() {
        String estado = "ENCAMI";
        return pedidoDao.createQuery().filter("estado =", estado).asList();
    }

    public List<Pedido> obtenertodoslosPedidos() {
        return this.mp.context().find(Pedido.class).asList();
    }

    public List<Pedido> obtenerPedidosPorCliente(Cliente c) {
        return pedidoDao.createQuery().field("cliente").equal(c).asList();
    }

    public List<Pedido> obtenerPedidosPorVendedor(Empleado e) {
            return pedidoDao.createQuery().field("rol").containsIgnoreCase(e.getRol()).asList();
        }

    
    public List<Pedido> obtenerEstados(Pedido p) {
        return pedidoDao.createQuery().field("estado").containsIgnoreCase(p.getEstado()).asList();
    }

    public boolean guardarPedido(Pedido p, Map<DetallePedido, Bodega> asignacionPedido) {
        try {
              Integer id =obtenerMaximoId()+1;
              p.setCodigoPedido(id);
            p.setEstado("ESPER");
            if (p.getDetalle() != null && !p.getDetalle().isEmpty()) {
                List<DetallePedido> list = new ArrayList<>(p.getDetalle());
                p.setDetalle(null);
                pedidoDao.save(p);

                for (DetallePedido d : list) {
                    actualizarDisponibilidad((Bodega) asignacionPedido.get(d), d.getCantidad());
                    d.setCodigoDetallePedido(p.getCodigoPedido());

                    detallePedidoDao.save(d);
                }
                return true;
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "No se pudo ingresar el Nuevo Pedido", e);
        }
        return false;
    }

    public boolean actualizarDisponibilidad(Bodega b, Integer cantReducir) {
        try {
            LOG.log(Level.FINE, "Va a modificar la bodega:", b);
            Query<Bodega> query = bodegaDao.createQuery().filter("codigoBodega =", b.getCodigoBodega());
            b.setCantidad(b.getCantidad() - cantReducir);
            UpdateOperations<Bodega> opera = bodegaDao.createUpdateOperations().set("cantidad", b.getCantidad());

            bodegaDao.update(query, opera);

            return true;

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "No se pudo actualizar la disponibilidad", e);
        }
        return false;
    }

    public void modificar(Pedido p) {
        LOG.log(Level.FINE, "Va a modificar el pedido:", p);

        Query<Pedido> query = pedidoDao.createQuery().filter("codigoPedido =", p.getCodigoPedido());
        UpdateOperations<Pedido> opera = pedidoDao.createUpdateOperations().set("fecha", p.getFecha())
                .set("estado", p.getEstado());

        pedidoDao.update(query, opera);

        LOG.log(Level.INFO, "Se ha modificado el pedido: ", p);
    }

      public int obtenerMaximoId()
    {
       List<Pedido> pedidos = pedidoDao.createQuery().order("-codigoPedido").asList(new FindOptions().limit(1));
       
       if ((pedidos == null) || (pedidos.isEmpty()))
       {
           return 0;
       }
       
        return pedidos.get(0).getCodigoPedido();
    }
    
    
    
}
