/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Cliente;
import ec.edu.espe.shalued.modelo.Dao.FacturaDao;
import ec.edu.espe.shalued.modelo.Factura;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;

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
       
    public List<Factura> obtenerFacturasporCliente(Cliente c)
    {
       return facturaDao.createQuery().filter("codigoCliente =", c.getCodigoCliente()).asList();        
    }
    

}
