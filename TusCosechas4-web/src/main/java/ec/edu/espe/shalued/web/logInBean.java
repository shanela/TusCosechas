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
import ec.edu.espe.shalued.servicio.UsuarioServicio;
import ec.edu.espe.shalued.web.utils.FacesUtil;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author luis
 */
@Named(value = "logInBean")
@ViewScoped
public class logInBean implements Serializable {

    /**
     * Creates a new instance of logInBean
     */
    private String username;
    private String password;
    private Usuario register;
    private String passwordConfirm;
    private Cliente nuevoCliente;
    private Empleado nuevoEmpleado;

    private static final Logger LOG = Logger.getLogger(logInBean.class.getName());

    @Inject
    private UsuarioServicio usuarioServicio;
    @Inject
    private ClienteServicio clienteServicio;
    @Inject
    private EmpleadoServicio empleadoServicio;
    @Inject
    private credencialesBean credenciales;

    @PostConstruct
    public void init() {
        this.register = new Usuario();
        this.nuevoCliente = new Cliente();
        this.username = "";
        this.password = "";
    }

    public String login() {
        String url = "#";
        Usuario user = this.usuarioServicio.obtenerPorUsername(this.username);
        if (user != null) {
            url = "/faces/views/menu";
            String UsuTipo = user.getTipo();

            switch (UsuTipo) {
                case "CLI":
                    Cliente cliente = this.clienteServicio.obtenerPorPKUsuario(user);
                    credenciales.setClienteSesion(cliente);
                    url = "NuevoPedido.xhtml";
                    break;
                case "EMP":
                    Empleado empleado = this.empleadoServicio.obtenerRolUsuario(UsuTipo);                            
                    credenciales.setEmpleadoSesion(empleado);
                    
//                    if()
//                    {
//                    
//                    }
                    url ="";
                    break;
                default:
                    LOG.log(Level.INFO, "El usuario contiene tipo conocido", user);
            }
        }
        return url;
    }

    public String registerCliente() {
        String url = "";
        if (this.register != null) {
            if (this.register.getPassword().equals(this.passwordConfirm)) {
                this.usuarioServicio.crear(this.register, "ACTIV", "CLI");
                
              //  this.clienteServicio.crear(this.nuevoCliente, this.register);
                this.credenciales.StartSession(this.register);
                url = "/faces/views/logIn.xhtml";
            } else {
                FacesUtil.addMessageError(null, "La contraseña no coinside");
            }
        } else {
            FacesUtil.addMessageInfo("El usuario " + this.username + " ya esta registrado.");
        }
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getRegister() {
        return register;
    }

    public void setRegister(Usuario register) {
        this.register = register;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Cliente getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(Cliente nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public Empleado getNuevoEmpleado() {
        return nuevoEmpleado;
    }

    public void setNuevoEmpleado(Empleado nuevoEmpleado) {
        this.nuevoEmpleado = nuevoEmpleado;
    }

}
