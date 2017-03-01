/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.modelo.Dao.EmpleadoDao;
import ec.edu.espe.shalued.modelo.Dao.UsuarioDao;
import ec.edu.espe.shalued.modelo.Empleado;
import ec.edu.espe.shalued.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.mongodb.morphia.query.FindOptions;

/**
 *
 * @author SHANE
 */
@Stateless
@LocalBean
public class EmpleadoServicio implements Serializable {

    private static final Logger LOG = Logger.getLogger(EmpleadoServicio.class.getName());

    MongoPersistence mp;
    private EmpleadoDao empleadoDao;
    private UsuarioDao usuarioDao;

    public List<Empleado> obtenerUsuarios() {
        return this.mp.context().find(Empleado.class).asList();
    }

    @PostConstruct
    public void init() {
        mp = new MongoPersistence();
        empleadoDao = new EmpleadoDao(Empleado.class, mp.context());
        usuarioDao = new UsuarioDao(Usuario.class, mp.context());
    }

    public List<Empleado> obtenerPorPKUsuario(String nombre) {
        return empleadoDao.createQuery().field("nombres").containsIgnoreCase(nombre).asList();

    }

    public Empleado obtenerPorPKUsuario1(Integer id) {
        return empleadoDao.findOne("codigoEmpleado", id);
    }

    public Empleado obtenerRolUsuario(String rol) {
        return empleadoDao.findOne("rol", rol);
    }

    public Empleado crear(Empleado usuario) {
        LOG.log(Level.FINE, "Va a crear el usuario:", usuario);

        usuario.setCodigoEmpleado(obtenerMaximoId() + 1);
        mp.context().save(usuario);
        return usuario;
    }

    public int obtenerMaximoId() {
        List<Empleado> usuarios = empleadoDao.createQuery().order("-codigoEmpleado").asList(new FindOptions().limit(1));

        if ((usuarios == null) || (usuarios.isEmpty())) {
            return 0;
        }

        return usuarios.get(0).getCodigoEmpleado();
    }
}
//completar
