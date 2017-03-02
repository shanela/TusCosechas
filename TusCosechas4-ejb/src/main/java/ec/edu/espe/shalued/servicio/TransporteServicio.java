package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Dao.TransporteDao;
import ec.edu.espe.shalued.modelo.Transporte;
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
public class TransporteServicio implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(TransporteServicio.class.getName());
    
    MongoPersistence mp;
    private TransporteDao transporteDao;

    @PostConstruct
    public void postConstruct() {
        mp = new MongoPersistence();
        transporteDao = new TransporteDao(Transporte.class, mp.context());
    }

    public Transporte findById(String hexId) {
        ObjectId oId = new ObjectId(hexId);
        return this.mp.context().find(Transporte.class).field("id").equal(oId).get();
    }
    
    public Key<Transporte> crear(Transporte transporte) 
    {
       LOG.log(Level.FINE, "Va a crear el transporte:", transporte);
       Integer id =obtenerMaximoId()+1;
       transporte.setCodigoTransporte(id);
       return transporteDao.save(transporte);

    }
    
      public int obtenerMaximoId()
    {
       List<Transporte> transportes = transporteDao.createQuery().order("-codigoTransporte").asList(new FindOptions().limit(1));
       
       if ((transportes == null) || (transportes.isEmpty()))
       {
           return 0;
       }
       
        return transportes.get(0).getCodigoTransporte();
    }
    
    
    
    
    public void modificar(Transporte transporte) {
        LOG.log(Level.FINE, "Va a modificar el transporte:", transporte);
        try
         {
        Query<Transporte> query = transporteDao.createQuery().filter("codigoVegetal =", transporte.getCodigoTransporte());     
         UpdateOperations<Transporte> opera = transporteDao.createUpdateOperations().set("marca", transporte.getMarca())
                .set("modelo", transporte.getModelo())
                .set("anio", transporte.getAnio())
                .set("capacidadcarga", transporte.getCapacidadcarga())
                .set("volumen", transporte.getVolumen())
                .set("matricula", transporte.getMatricula())
                .set("conductor", transporte.getConductor());
         transporteDao.update(query, opera);
        LOG.log(Level.INFO, "Se ha modificado el transporte: ", transporte);
    }
        
        catch(Exception e) 
         {
            LOG.log(Level.SEVERE, "No se puedo modificar el transporte", e);       
         }
    }
    
    public void eliminar(Transporte transporte) {
        LOG.log(Level.FINE, "Va a eliminar el transporte de codigo:", transporte.getCodigoTransporte());
        transporteDao.deleteById(transporte.getId());
        LOG.log(Level.INFO, "Se ha eliminado el transporte de codigo: ", transporte.getCodigoTransporte());
    }
    
    public List<Transporte> obtenerTransportes() {
        return this.mp.context().find(Transporte.class).asList();
    }
    
    public List<Transporte> obtenerTransportesPorMatricula(String matricula) 
    {
     return   transporteDao.createQuery().field("matricula").containsIgnoreCase(matricula).asList();
        
    }
    
}
