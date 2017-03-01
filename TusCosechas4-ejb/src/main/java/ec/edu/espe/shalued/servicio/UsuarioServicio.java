/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.servicio;

import com.gestor.glabs.mongopersistence.MongoPersistence;
import ec.edu.espe.shalued.hash.MD5;
import ec.edu.espe.shalued.modelo.Dao.UsuarioDao;
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
 * @author Luis
 */
@Stateless
@LocalBean
public class UsuarioServicio implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioServicio.class.getName());

    private UsuarioDao usuarioDao;
    MongoPersistence mp;

    @PostConstruct
    public void postConstruct() {
        mp = new MongoPersistence();
        usuarioDao = new UsuarioDao(Usuario.class, mp.context());
    }

    /// PREGUNTAR LUU
    public Usuario crear(Usuario usuario) {
        LOG.log(Level.FINE, "Va a crear el usuario:", usuario);
        usuario.setPassword(MD5.passwordToMD5(usuario.getPassword()));
        usuario.setCodigoUsuario(obtenerMaximoId() + 1);
        mp.context().save(usuario);
        return usuario;
    }

    public Usuario crear(Usuario usuario, String Estado, String Tipo) {
        LOG.log(Level.FINE, "Va a crear el usuario:", usuario);
        if (usuario != null) {
            usuario.setPassword(MD5.passwordToMD5(usuario.getPassword()));
            usuario.setEstado(Estado);
            usuario.setTipo(Tipo);

            mp.context().save(usuario);
        }
        return usuario;
    }

    public Usuario obtenerPorUsername(String usuNombre) {
        return usuarioDao.findOne("username", usuNombre);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioDao.find().asList();
    }

    public int obtenerMaximoId() {
        List<Usuario> usuarios = usuarioDao.createQuery().order("-codigoUsuario").asList(new FindOptions().limit(1));

        if ((usuarios == null) || (usuarios.isEmpty())) {
            return 0;
        }

        return usuarios.get(0).getCodigoUsuario();
    }
}
