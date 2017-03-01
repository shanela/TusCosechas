/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Cliente;
import ec.edu.espe.shalued.modelo.Dao.ClienteDao;
import ec.edu.espe.shalued.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.FindOptions;

/**
 *
 * @author SHANE
 */
@Stateless
@LocalBean
public class ClienteServicio implements Serializable {

    private static final Logger LOG = Logger.getLogger(VegetalServicio.class.getName());

    MongoPersistence mp;
    private ClienteDao clienteDao;

    public Cliente obtenerPorPKUsuario(String usuNombre) {
        return clienteDao.findOne("username", usuNombre);
    }

    @PostConstruct
    public void init() {
        mp = new MongoPersistence();
        clienteDao = new ClienteDao(Cliente.class, mp.context());
    }

    ///PREGUNTAR LUU
    public Cliente obtenerPorPKUsuario(Usuario usuNombre) {
        return this.clienteDao.findOne("usuario", usuNombre);
    }

    public Key<Cliente> crear(Cliente c) {
        LOG.log(Level.FINE, "Va a crear el cliente:", c);
        Integer id = obtenerMaximoId() + 1;
        c.setCodigoCliente(id);
        return clienteDao.save(c);

    }

    public int obtenerMaximoId() {
        List<Cliente> cli = clienteDao.createQuery().order("-codigoCliente").asList(new FindOptions().limit(1));

        if ((cli == null) || (cli.isEmpty())) {
            return 0;
        }

        return cli.get(0).getCodigoCliente();
    }

}
