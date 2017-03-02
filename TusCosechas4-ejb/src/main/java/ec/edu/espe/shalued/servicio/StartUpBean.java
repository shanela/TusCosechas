/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.servicio;

import ec.edu.espe.shalued.modelo.Canton;
import ec.edu.espe.shalued.modelo.Cliente;
import ec.edu.espe.shalued.modelo.Empleado;
import ec.edu.espe.shalued.modelo.Provincia;
import ec.edu.espe.shalued.modelo.Usuario;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 *
 * @author SHANE
 */
@Startup
@Singleton
public class StartUpBean {
    //se cargan los usuarios por defecto en caso de que es la primera vez que se inicia el servidor

    @Inject
    UsuarioServicio usuarioServicio;
    @Inject
    EmpleadoServicio empleadoServicio;
    @Inject
    ClienteServicio clienteServicio;
    
    @Inject
    UbicacionServicio ubicacionServicio;


    @PostConstruct
    public void init() 
    {
        List<Usuario> list = usuarioServicio.obtenerTodos();
        if (list == null || list.isEmpty()) {
            System.out.println("Lista vacia se van a crear los usuarios por defecto");

            Usuario clienteusuario = new Usuario();
            clienteusuario.setEstado("ACT");
            clienteusuario.setPassword("1234");
            clienteusuario.setTipo("CLI");
            clienteusuario.setUsername("shane");

            Usuario bodeguerousuario = new Usuario();
            bodeguerousuario.setEstado("ACT");
            bodeguerousuario.setPassword("1234");
            bodeguerousuario.setTipo("BOD");
            bodeguerousuario.setUsername("bodeguero");

            Usuario conductorusuario = new Usuario();
            conductorusuario.setEstado("ACT");
            conductorusuario.setPassword("1234");
            conductorusuario.setTipo("CON");
            conductorusuario.setUsername("conductor");

            Usuario gerenteusuario = new Usuario();
            gerenteusuario.setEstado("ACT");
            gerenteusuario.setPassword("1234");
            gerenteusuario.setTipo("GER");
            gerenteusuario.setUsername("gerente");

            Usuario vendedorusuario = new Usuario();
            vendedorusuario.setEstado("ACT");
            vendedorusuario.setPassword("1234");
            vendedorusuario.setTipo("GER");
            vendedorusuario.setUsername("vendedor");

            usuarioServicio.crear(gerenteusuario);
            usuarioServicio.crear(clienteusuario);
            usuarioServicio.crear(vendedorusuario);
            usuarioServicio.crear(conductorusuario);
            usuarioServicio.crear(bodeguerousuario);

            Cliente c = new Cliente();
            c.setCedula("0201710241");
            c.setApellidos("Camacho");
            c.setNombres("Shanela");
            c.setCodigoCliente(1);
            c.setDireccion("Guaranda");
            c.setTelefono("0983015478");
            c.setUsuario(clienteusuario);

            clienteServicio.crear(c);

            Empleado e = new Empleado();
            e.setApellidos("Castillo");
            e.setNombres("Ricardo");
            e.setUsuario(gerenteusuario);
            e.setCedula("0604133546");
            e.setRol("GER");
            e.setTelefono("0983015478");
            e.setDireccion("armenia 1");

            empleadoServicio.crear(e);

            e = new Empleado();
            e.setApellidos("Paz");
            e.setNombres("Maria");
            e.setUsuario(vendedorusuario);
            e.setCedula("1721406963");
            e.setRol("VEN");
            e.setTelefono("0983015478");
            e.setDireccion("California");

            empleadoServicio.crear(e);

            e = new Empleado();
            e.setApellidos("Galarza");
            e.setNombres("Mauricio");
            e.setUsuario(conductorusuario);
            e.setCedula("1791413237");
            e.setRol("CON");
            e.setTelefono("0983015478");
            e.setDireccion("Desvío");

            empleadoServicio.crear(e);

            e = new Empleado();
            e.setApellidos("Flores");
            e.setNombres("Cristian");
            e.setUsuario(bodeguerousuario);
            e.setCedula("0601124578");
            e.setRol("BOD");
            e.setTelefono("0983015478");
            e.setDireccion("Miraflores");

            empleadoServicio.crear(e);

        }
        
        
        
        List<Provincia> provincias = ubicacionServicio.obtenerTodasProvincias();
        if (provincias == null || provincias.isEmpty()) 
        {
            Provincia p = new Provincia();          
            p.setNombre("Bolivar");      
            ubicacionServicio.crearProvincia(p);
            
            Provincia p1 = new Provincia();
            p1.setNombre("Pichincha");      
            ubicacionServicio.crearProvincia(p1);
            
            Provincia p2 = new Provincia();
            p2.setNombre("Azuay");      
            ubicacionServicio.crearProvincia(p2);
            
            Provincia p3 = new Provincia();
            p3.setNombre("Chimborazo");      
            ubicacionServicio.crearProvincia(p3);
            
            Provincia p4 = new Provincia();
            p4.setNombre("Guayas");      
            ubicacionServicio.crearProvincia(p4);         
            
            
        }
        
              List<Canton> cantones = ubicacionServicio.obtenerTodasCantones();
        if (cantones == null || cantones.isEmpty()) 
        {
            Canton c = new Canton();
            
            c.setNombre("Guaranda");      
            ubicacionServicio.crearCanton(c);          
            Canton c1 = new Canton();
            c1.setNombre("Chillanes");      
            ubicacionServicio.crearCanton(c1);
            
            Canton c2 = new Canton();
            c2.setNombre("Cayambe");      
            ubicacionServicio.crearCanton(c2);            
            Canton c3 = new Canton();
            c3.setNombre("Mejia");      
            ubicacionServicio.crearCanton(c3);
            
            Canton c4 = new Canton();
            c4.setNombre("Riobamba");      
            ubicacionServicio.crearCanton(c4);
            Canton c5 = new Canton();
            c5.setNombre("Guano");      
            ubicacionServicio.crearCanton(c5);
            
            
           
        }
       
        
        
    }
}
