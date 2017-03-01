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
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author SHANE
 */
@Stateless
@LocalBean
public class EmpleadoServicio implements Serializable {

    private static final Logger LOG = Logger.getLogger(VegetalServicio.class.getName());

    MongoPersistence mp;
    private EmpleadoDao empleadoDao;
    private UsuarioDao usuarioDao;

    public List<Empleado> obtenerUsuarios() {
        return this.mp.context().find(Empleado.class).asList();
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
}
//completar