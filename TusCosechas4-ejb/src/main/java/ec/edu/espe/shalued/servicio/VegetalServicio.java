package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
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

@Stateless
@LocalBean
public class VegetalServicio implements Serializable{
    
    private static final Logger LOG = Logger.getLogger(VegetalServicio.class.getName());
    
    MongoPersistence mp;
    private VegetalDao vegetalDao;

    @PostConstruct
    public void postConstruct() {
        mp = new MongoPersistence();
        vegetalDao = new VegetalDao(Vegetal.class, mp.context());
    }

    public Vegetal findById(String hexId) {
        ObjectId oId = new ObjectId(hexId);
        return this.mp.context().find(Vegetal.class).field("id").equal(oId).get();
    }
    
//    public Vegetal crear(Vegetal vegetal)
//    {
//        LOG.log(Level.FINE, "Va a crear el vegetal:", vegetal);
//        mp.context().save(vegetal);
//        LOG.log(Level.INFO, "Se ha creado el vegetal:", vegetal);
//        return vegetal;
//    }
    
    public Key<Vegetal> crear(Vegetal vegetal) 
    {
       LOG.log(Level.FINE, "Va a crear el vegetal:", vegetal);
       Integer id =obtenerMaximoId()+1;
       vegetal.setCodigoVegetal(id);
       return vegetalDao.save(vegetal);

    }
    
      public int obtenerMaximoId()
    {
       List<Vegetal> vegetales = vegetalDao.createQuery().order("-codigoVegetal").asList(new FindOptions().limit(1));
       
       if ((vegetales == null) || (vegetales.isEmpty()))
       {
           return 0;
       }
       
        return vegetales.get(0).getCodigoVegetal();
    }
    
    
    public void modificar(Vegetal vegetal) {
        LOG.log(Level.FINE, "Va a modificar el vegetal:", vegetal);
        try
         {
          Query<Vegetal> query = vegetalDao.createQuery().filter("codigoVegetal =", vegetal.getCodigoVegetal());
           UpdateOperations<Vegetal> opera = vegetalDao.createUpdateOperations().set("nombre", vegetal.getNombre())
                                             .set("especie", vegetal.getEspecie())
                                             .set("peso", vegetal.getPeso())
                                             .set("precio", vegetal.getPrecio())
                                             .set("cantidadcajapequenia", vegetal.getCantidadcajapequenia())
                                             .set("cantidadcajamediana", vegetal.getCantidadcajamediana())
                                             .set("cantidadcajagrande", vegetal.getCantidadcajagrande());
           vegetalDao.update(query, opera);
          LOG.log(Level.INFO, "Se ha modificado el vegetal: ", vegetal);
         }
        catch(Exception e) 
         {
            LOG.log(Level.SEVERE, "No se puedo modificar el vegetal", e);       
         }
    }
    
    
     public void eliminarVegetal(Vegetal vegetal)
    {
        LOG.log(Level.FINE, "Va a eliminar el vegetal:", vegetal);
        vegetalDao.deleteById(vegetal.getId());
        LOG.log(Level.INFO, "Se ha eliminado el vegetal de codigo: ", vegetal);
    }
       
    public List<Vegetal> obtenerVegetal() {
        return this.mp.context().find(Vegetal.class).asList();
    }
    
    public Vegetal obtenerVegetalporCodigo(Integer id) {
        return vegetalDao.findOne("codigoVegetal", id);
    }
    
    public List<Vegetal> obtenerVegetalesPorNombre(String nombre) 
    {
     return   vegetalDao.createQuery().field("nombre").containsIgnoreCase(nombre).asList();
        
    }
    
}
