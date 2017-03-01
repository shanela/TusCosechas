/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Bodega;
import ec.edu.espe.shalued.modelo.Dao.BodegaDao;
import ec.edu.espe.shalued.modelo.Dao.VegetalDao;
import ec.edu.espe.shalued.modelo.Vegetal;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

/**
 *
 * @author SHANE
 */
@Stateless
@LocalBean
public class BodegaServicio implements Serializable {

    private static final Logger LOG = Logger.getLogger(VegetalServicio.class.getName());
    MongoPersistence mp;
    private BodegaDao bodegaDao;
    private VegetalDao vegetalDao;

    @PostConstruct
    public void postConstruct() {
        mp = new MongoPersistence();
        bodegaDao = new BodegaDao(Bodega.class, mp.context());
        vegetalDao = new VegetalDao(Vegetal.class,mp.context());
    }
        
     public Bodega findById(String hexId) {
        ObjectId oId = new ObjectId(hexId);
        return this.mp.context().find(Bodega.class).field("id").equal(oId).get();
    }
     
      public Key<Bodega> crearBodega(Bodega b,Vegetal v)
    {
       LOG.log(Level.FINE, "Va a crear la bodega:", b);
       Integer id =obtenerMaximoId()+1;     
       b.setCodigoBodega(id);
       b.setVegetal(v);
       
       return bodegaDao.save(b);
    }
      
     public int obtenerMaximoId()
    {
       List<Bodega> bodegas = bodegaDao.createQuery().order("-codigoBodega").asList(new FindOptions().limit(1));
       
       if ((bodegas == null) || (bodegas.isEmpty()))
       {
           return 0;
       }
       
        return bodegas.get(0).getCodigoBodega();
    }
    
      public void modificarBodega(Bodega b,Vegetal v ) 
     {
         try
         {
            LOG.log(Level.FINE, "Va a modificar la bodega:", b);
            Query<Bodega> query = bodegaDao.createQuery().filter("codigoBodega =", b.getCodigoBodega());
             UpdateOperations<Bodega> opera= bodegaDao.createUpdateOperations().set("cantidad", b.getCantidad())
                                                                               .set("fechaingreso", b.getFechaingreso()) 
                                                                               .set("fechacaducidad", b.getFechacaducidad()) 
                                                                               .set("estado", b.getEstado());
                                                                               
             bodegaDao.update(query, opera); 
         }
         catch(Exception e) 
         {
            LOG.log(Level.SEVERE, "No se puedo modificar la bodega", e);
                      }
     }
    
       public void eliminarBodega(Bodega b)
    {
        LOG.log(Level.FINE, "Va a eliminar la bodega:", b.getCodigoBodega());
        bodegaDao.deleteById(b.getId());
        LOG.log(Level.INFO, "Se ha la bodega: ", b.getCodigoBodega());
    }
      
    public List<Bodega> obtenerVegetalesdisponibles()
    {
        String estado = "VIGEN";
       return bodegaDao.createQuery().filter("estado =", estado).asList();        
    }
    
        public List<Vegetal> obtenerVegetales() {
        return this.mp.context().find(Vegetal.class).asList();
    }
     
    public Vegetal obtenerVegetal(Integer id) {
        return vegetalDao.findOne("codigoVegetal", id);
    }

    
     public List<Bodega> obtenerBodegas() {
        return this.mp.context().find(Bodega.class).asList();
    }
    public boolean actualizarCantidadBodega(Bodega b) {
        try { 
            LOG.log(Level.FINE, "Va actualizar la cantidad de bodega:", b);
            bodegaDao.createQuery().filter("codigoBodega =", b.getCodigoBodega());
            bodegaDao.createUpdateOperations().set("cantidad", b.getCantidad());
            LOG.log(Level.INFO, "Se ha actualizado la bodega:", b);
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "No se puede actualizar la cantidad de bodega", e);
        }
        return false;

    }
    
   
    
   
    
   
   

}
