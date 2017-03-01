/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.web;

import ec.edu.espe.shalued.modelo.Cliente;
import ec.edu.espe.shalued.modelo.Empleado;
import ec.edu.espe.shalued.modelo.Usuario;
import ec.edu.espe.shalued.servicio.ClienteServicio;
import ec.edu.espe.shalued.servicio.EmpleadoServicio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author SHANE
 */
@Named(value = "credencialesBean")
@SessionScoped
public class credencialesBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(credencialesBean.class.getName());

    /**
     * Creates a new instance of credencialesBean
     */
    private Usuario ususarioSesion;
    private Cliente clienteSesion;
    private Empleado empleadoSesion;

    @Inject
    private ClienteServicio clienteServicio;
    @Inject
    private EmpleadoServicio empleadoServicio;

    public void StartSession(Usuario usuario) {
        if (usuario != null) {
            this.ususarioSesion = usuario;
            switch (this.ususarioSesion.getTipo()) {
                case "CLI":
                    Cliente cliente = this.clienteServicio.obtenerPorPKUsuario(usuario);
                    if (cliente != null) {
                        this.clienteSesion = cliente;
                    }
                    break;
                case "EMP":
//                    Empleado empleado = this.empleadoServicio.obtenerPorPKUsuario2(usuario.getUsername());
//                    if (empleado != null) {
//                        this.empleadoSesion = empleado;
//                    }
                    break;
                default:
                    LOG.log(Level.INFO, "El usuario contiene tipo conocido", usuario);
            }
        }

    }

    public Cliente getClienteSesion() {
        return clienteSesion;
    }

    public void setClienteSesion(Cliente clienteSesion) {
        this.clienteSesion = clienteSesion;
    }

    public Empleado getEmpleadoSesion() {
        return empleadoSesion;
    }

    public void setEmpleadoSesion(Empleado empleadoSesion) {
        this.empleadoSesion = empleadoSesion;
    }
    
    
    

}
