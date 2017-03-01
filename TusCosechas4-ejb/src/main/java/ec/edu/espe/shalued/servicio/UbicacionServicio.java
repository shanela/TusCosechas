package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Canton;
import ec.edu.espe.shalued.modelo.Dao.CantonDao;
import ec.edu.espe.shalued.modelo.Dao.LoteDao;
import ec.edu.espe.shalued.modelo.Dao.ProvinciaDao;
import ec.edu.espe.shalued.modelo.Lote;
import ec.edu.espe.shalued.modelo.Provincia;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.mongodb.morphia.query.Query;

@Stateless
@LocalBean
public class UbicacionServicio implements Serializable{

    private static final Logger LOG = Logger.getLogger(UbicacionServicio.class.getName());
    MongoPersistence mp;
    private ProvinciaDao provinciaDao;
    private CantonDao cantonDao;

    //PREGUNTAR LUUU
  
    public List<Provincia> obtenerTodasProvincias() {
        return this.mp.context().find(Provincia.class).asList();
    }
    
     public List<Canton> obtenerTodasCantones() {
        return this.mp.context().find(Canton.class).asList();
    }
     
     public List<Canton> obtenerCantonesporProvincia(Provincia p) {
        Provincia p1 = provinciaDao.findOne("codigoProvincia", p.getCodigoProvincia());

////        Query q=cantonDao.getEntityManager().createQuery("Select c from Canton c WHERE c.provincia=:p",Canton.class).setParameter("p", p1);
            Query<Canton> query = cantonDao.createQuery().filter("codigoProvincia =", p1.getCodigoProvincia());
      return query.asList();

    }

}
