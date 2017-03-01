package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Dao.TransporteDao;
import ec.edu.espe.shalued.modelo.Transporte;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.UpdateOperations;

@Stateless
@LocalBean
public class TransporteServicio {
    
    private static final Logger LOG = Logger.getLogger(TransporteServicio.class.getName());
    
    MongoPersistence mp;
    private TransporteDao transporteDao;

    @PostConstruct
    public void postConstruct() {
        mp = new MongoPersistence();
    }

    public Transporte findById(String hexId) {
        ObjectId oId = new ObjectId(hexId);
        return this.mp.context().find(Transporte.class).field("id").equal(oId).get();
    }
    
    public Transporte crear(Transporte transporte) {
        LOG.log(Level.FINE, "Va a crear el transporte:", transporte);
        mp.context().save(transporte);
        LOG.log(Level.INFO, "Se ha creado el transporte:", transporte);
        return transporte;
    }
    
    public void modificar(Transporte transporte) {
        LOG.log(Level.FINE, "Va a modificar el transporte:", transporte);
        Transporte tra = this.findById(transporte.getIdString());
        UpdateOperations ops1 = mp.context().createUpdateOperations(Transporte.class)
                .set("marca", tra.getMarca())
                .set("modelo", tra.getModelo())
                .set("anio", tra.getAnio())
                .set("capacidadcarga", tra.getCapacidadcarga())
                .set("volumen", tra.getVolumen())
                .set("matricula", tra.getMatricula())
                .set("conductor", tra.getConductor());
        this.mp.context().update(tra, ops1);
        LOG.log(Level.INFO, "Se ha modificado el transporte: ", transporte);
    }
    
    public void eliminar(Transporte transporte) {
        LOG.log(Level.FINE, "Va a eliminar el transporte de codigo:", transporte.getCodigoTransporte());
        transporteDao.deleteById(transporte.getId());
        LOG.log(Level.INFO, "Se ha eliminado el transporte de codigo: ", transporte.getCodigoTransporte());
    }
    
    public List<Transporte> obtenerTransportes() {
        return this.mp.context().find(Transporte.class).asList();
    }
    
}
