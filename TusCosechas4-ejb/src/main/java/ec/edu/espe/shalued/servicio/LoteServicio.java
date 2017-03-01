package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import com.mongodb.operation.UpdateOperation;
import ec.edu.espe.shalued.modelo.Dao.LoteDao;
import ec.edu.espe.shalued.modelo.Lote;
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
public class LoteServicio implements Serializable{

    private static final Logger LOG = Logger.getLogger(LoteServicio.class.getName());
    MongoPersistence mp;
    private LoteDao loteDao;

    @PostConstruct
    public void postConstruct() {
        mp = new MongoPersistence();
        loteDao = new LoteDao(Lote.class, mp.context());
    }

    public Lote findById(String hexId) {
        ObjectId oId = new ObjectId(hexId);
        return this.mp.context().find(Lote.class).field("id").equal(oId).get();
    }
    
      public Lote obtenerLoteporCodigo(Integer id) {
        return loteDao.findOne("codigoLote", id);
    }

      public int obtenerMaximoId()
    {
       List<Lote> lotes = loteDao.createQuery().order("-codigoLote").asList(new FindOptions().limit(1));
       
       if ((lotes == null) || (lotes.isEmpty()))
       {
           return 0;
       }
       
        return lotes.get(0).getCodigoLote();
    }
      
    public Key<Lote> crear(Lote lote) 
    {
       LOG.log(Level.FINE, "Va a crear el lote:", lote);
       Integer id =obtenerMaximoId()+1;
       lote.setCodigoLote(id);
       return loteDao.save(lote);

    }

     public void eliminarLote(Lote lote)
    {
        LOG.log(Level.FINE, "Va a eliminar el lote:", lote);
        loteDao.deleteById(lote.getId());
    }
     
    
    public void modificar(Lote lote) {
       LOG.log(Level.FINE, "Va a modificar el lote:", lote);
        try
         {
            Query<Lote> query = loteDao.createQuery().filter("codigoLote =", lote.getCodigoLote());
            UpdateOperations<Lote> opera = loteDao.createUpdateOperations().set("nombre", lote.getNombre()).set("area", lote.getArea());
            loteDao.update(query, opera);
            LOG.log(Level.INFO, "Se ha modificado el lote: ", lote);
         }
         catch(Exception e) 
         {
            LOG.log(Level.SEVERE, "No se puedo modificar el lote", e);       
         }
        
    }

    public List<Lote> obtenerLotes() {
        return this.mp.context().find(Lote.class).asList();
    }

}
