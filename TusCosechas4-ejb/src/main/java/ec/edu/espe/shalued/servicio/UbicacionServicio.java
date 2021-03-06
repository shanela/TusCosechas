package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Canton;
import ec.edu.espe.shalued.modelo.Dao.CantonDao;
import ec.edu.espe.shalued.modelo.Dao.ProvinciaDao;
import ec.edu.espe.shalued.modelo.Provincia;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;

@Stateless
@LocalBean
public class UbicacionServicio implements Serializable {

    private static final Logger LOG = Logger.getLogger(UbicacionServicio.class.getName());
    MongoPersistence mp;
    private ProvinciaDao provinciaDao;
    private CantonDao cantonDao;

    //PREGUNTAR LUUU
    @PostConstruct
    public void init() {
        mp = new MongoPersistence();
        provinciaDao = new ProvinciaDao(Provincia.class, mp.context());
        cantonDao = new CantonDao(Canton.class, mp.context());
    }

    public List<Provincia> obtenerTodasProvincias() {
        return this.mp.context().find(Provincia.class).asList();
    }

    public List<Canton> obtenerTodasCantones() {
        return this.mp.context().find(Canton.class).asList();
    }

    public Canton crearCanton(Canton c) {
        LOG.log(Level.FINE, "Va a crear el canton:", c);
        c.setCodigoCanton(obtenerMaximoIdCanton() + 1);
        mp.context().save(c);
        return c;
    }

    public Provincia crearProvincia(Provincia p) {
        LOG.log(Level.FINE, "Va a crear el provincia:", p);
        p.setCodigoProvincia(obtenerMaximoIdProvincia() + 1);
        mp.context().save(p);
        return p;
    }

    public int obtenerMaximoIdCanton() {
        List<Canton> cantones = cantonDao.createQuery().order("-codigoCanton").asList(new FindOptions().limit(1));

        if ((cantones == null) || (cantones.isEmpty())) {
            return 0;
        }

        return cantones.get(0).getCodigoCanton();
    }

    public int obtenerMaximoIdProvincia() {
        List<Provincia> provincias = provinciaDao.createQuery().order("-codigoProvincia").asList(new FindOptions().limit(1));

        if ((provincias == null) || (provincias.isEmpty())) {
            return 0;
        }

        return provincias.get(0).getCodigoProvincia();
    }

    public List<Canton> obtenerCantonesporProvincia(Provincia p) {
        Provincia p1 = provinciaDao.findOne("codigoProvincia", p.getCodigoProvincia());

////        Query q=cantonDao.getEntityManager().createQuery("Select c from Canton c WHERE c.provincia=:p",Canton.class).setParameter("p", p1);
        Query<Canton> query = cantonDao.createQuery().filter("codigoProvincia =", p1.getCodigoProvincia());
        return query.asList();

    }

}
