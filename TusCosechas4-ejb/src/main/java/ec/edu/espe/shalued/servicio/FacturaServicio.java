/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Cliente;
import ec.edu.espe.shalued.modelo.Dao.FacturaDao;
import ec.edu.espe.shalued.modelo.DetalleFactura;
import ec.edu.espe.shalued.modelo.DetallePedido;
import ec.edu.espe.shalued.modelo.Empleado;
import ec.edu.espe.shalued.modelo.Factura;
import ec.edu.espe.shalued.modelo.Pedido;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.FindOptions;

/**
 *
 * @author SHANE
 */
@Stateless
@LocalBean
public class FacturaServicio implements Serializable {

    private static final Logger LOG = Logger.getLogger(VegetalServicio.class.getName());

    MongoPersistence mp;
    private FacturaDao facturaDao;

    @PostConstruct
    public void postConstruct() {
        mp = new MongoPersistence();
        facturaDao = new FacturaDao(Factura.class, mp.context());
    }

    public List<Factura> obtenerAllFacturas() {
        return this.mp.context().find(Factura.class).asList();
    }

    public Factura obtenerFactura(String hexId) {
        ObjectId oId = new ObjectId(hexId);
        return this.mp.context().find(Factura.class).field("id").equal(oId).get();
    }

    public List<Factura> obtenerFacturasporCliente(Cliente c) {
        return facturaDao.createQuery().filter("codigoCliente =", c.getCodigoCliente()).asList();
    }

    public Factura generarFactura(Pedido p, Cliente c) {
        Factura f = new Factura();
        f.setCliente(c);
        f.setFecha(new Date());
        f.setCodigoFactura(obtenerMaximoId() + 1);
        
        float subtotal = 0.0f;
        List<DetalleFactura> detalles = new ArrayList<>();
        DetalleFactura detalle = null;
        int i = 0;
        if (p.getDetalle() != null) {
            for (DetallePedido dp : p.getDetalle()) {
                subtotal += dp.getSubtotal();
                detalle = new DetalleFactura();
                detalle.setCantidad(dp.getCantidad());
                detalle.setSubtotal(dp.getSubtotal());
                detalle.setDescripcion("Detalle " + (i + 1));
                detalle.setIndex(i);
                detalles.add(detalle);
                i++;
            }
        }
        f.setDetalleFacturaList(detalles);
        float iva = subtotal * 0.14f;
        float total = subtotal + iva;
        f.setTotal((double) total);
        f.setSubtotal((double) subtotal);
        f.setIva((double) iva);
        facturaDao.save(f);
        return f;
    }

    public int obtenerMaximoId() {
        List<Factura> usuarios = facturaDao.createQuery().order("-codigoFactura").asList(new FindOptions().limit(1));

        if ((usuarios == null) || (usuarios.isEmpty())) {
            return 0;
        }

        return usuarios.get(0).getCodigoFactura();
    }

}
