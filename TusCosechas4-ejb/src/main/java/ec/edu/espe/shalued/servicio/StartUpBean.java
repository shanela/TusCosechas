/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.servicio;

import ec.edu.espe.shalued.modelo.Canton;
import ec.edu.espe.shalued.modelo.Cliente;
import ec.edu.espe.shalued.modelo.Empleado;
import ec.edu.espe.shalued.modelo.Lote;
import ec.edu.espe.shalued.modelo.Provincia;
import ec.edu.espe.shalued.modelo.Transporte;
import ec.edu.espe.shalued.modelo.Usuario;
import ec.edu.espe.shalued.modelo.Vegetal;

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
    
    @Inject
    TransporteServicio transporteServicio;
    
     @Inject
    VegetalServicio vegetalServicio;
     
    @Inject
    LoteServicio loteServicio;


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
       
         List<Transporte> transportes = transporteServicio.obtenerTransportes();
        if (transportes == null || transportes.isEmpty()) 
        {
            Usuario conductorusuario = new Usuario();
            conductorusuario.setEstado("ACT");
            conductorusuario.setPassword("1234");
            conductorusuario.setTipo("CON");
            conductorusuario.setUsername("conductor");
            usuarioServicio.crear(conductorusuario);
                        
            Empleado e = new Empleado();
            e.setApellidos("Salguero");
            e.setNombres("Edagar");
            e.setUsuario(conductorusuario);
            e.setCedula("0201093176");
            e.setRol("CON");
            e.setTelefono("0987654321");
            e.setDireccion("Sangolqui");
            empleadoServicio.crear(e);
            
            Transporte t = new Transporte();
            t.setMarca("Kia");
            t.setModelo("Sportach");
            t.setAnio(2016);
            t.setCapacidadcarga(60);
            t.setVolumen(40);
            t.setMatricula("ASD234");
            t.setConductor(e);
            transporteServicio.crear(t);
            
            Usuario conductorusuario1 = new Usuario();
            conductorusuario.setEstado("ACT");
            conductorusuario.setPassword("1234");
            conductorusuario.setTipo("CON");
            conductorusuario.setUsername("conductor1");
            usuarioServicio.crear(conductorusuario1);
            
            Empleado e1 = new Empleado();
            e1.setApellidos("Gavilanez");
            e1.setNombres("Marion");
            e1.setUsuario(conductorusuario1);
            e1.setCedula("1716308216");
            e1.setRol("CON");
            e1.setTelefono("0966644324");
            e1.setDireccion("San Gartolo");
            
            Transporte t1 = new Transporte();
            t1.setMarca("Ford");
            t1.setModelo("Revolutions");
            t1.setAnio(2017);
            t1.setCapacidadcarga(8000);
            t1.setVolumen(600);
            t1.setMatricula("WER204");
            t1.setConductor(e1);
            transporteServicio.crear(t1);
            
             Usuario conductorusuario2 = new Usuario();
            conductorusuario.setEstado("ACT");
            conductorusuario.setPassword("1234");
            conductorusuario.setTipo("CON");
            conductorusuario.setUsername("conductor2");
            usuarioServicio.crear(conductorusuario2);
            
            
            Empleado e2 = new Empleado();
            e2.setApellidos("Gavilanez");
            e2.setNombres("Marion");
            e2.setUsuario(conductorusuario2);
            e2.setCedula("1716308216");
            e2.setRol("CON");
            e2.setTelefono("0966644324");
            e2.setDireccion("San Gartolo");
            
            Transporte t2 = new Transporte();
            t2.setMarca("Ford");
            t2.setModelo("Revolutions");
            t2.setAnio(2017);
            t2.setCapacidadcarga(8000);
            t2.setVolumen(600);
            t2.setMatricula("WER204");
            t2.setConductor(e2);
            transporteServicio.crear(t2);
            
             Usuario conductorusuario3 = new Usuario();
            conductorusuario.setEstado("ACT");
            conductorusuario.setPassword("1234");
            conductorusuario.setTipo("CON");
            conductorusuario.setUsername("conductor3");
            usuarioServicio.crear(conductorusuario3);
            
            
             Empleado e3 = new Empleado();
            e3.setApellidos("Gavilanez");
            e3.setNombres("Marion");
            e3.setUsuario(conductorusuario3);
            e3.setCedula("1716308216");
            e3.setRol("CON");
            e3.setTelefono("0966644324");
            e3.setDireccion("San Gartolo");
            
            Transporte t3 = new Transporte();
            t3.setMarca("Ford");
            t3.setModelo("Revolutions");
            t3.setAnio(2017);
            t3.setCapacidadcarga(8000);
            t3.setVolumen(600);
            t3.setMatricula("WER204");
            t3.setConductor(e3);
            transporteServicio.crear(t3);       
        }
        
         List<Vegetal> vegetales = vegetalServicio.obtenerVegetal();
        if (vegetales == null || vegetales.isEmpty()) 
        {
            Vegetal v = new Vegetal();
            v.setNombre("Papa");
            v.setEspecie("Raiz");
            v.setPeso(0.06);
            v.setPrecio(0.20);
            v.setCantidadcajapequenia(40);
            v.setCantidadcajamediana(80);
            v.setCantidadcajagrande(160);
            vegetalServicio.crear(v);
            
            Vegetal v1 = new Vegetal();
            v1.setNombre("Coliflor");
            v1.setEspecie("Flor");
            v1.setPeso(0.45);
            v1.setPrecio(0.60);
            v1.setCantidadcajapequenia(30);
            v1.setCantidadcajamediana(60);
            v1.setCantidadcajagrande(90);
            vegetalServicio.crear(v1);
            
            Vegetal v2 = new Vegetal();
            v2.setNombre("Lechuga");
            v2.setEspecie("Hojas");
            v2.setPeso(0.55);
            v2.setPrecio(0.50);
            v2.setCantidadcajapequenia(20);
            v2.setCantidadcajamediana(40);
            v2.setCantidadcajagrande(80);
            vegetalServicio.crear(v2);
            
            Vegetal v3 = new Vegetal();
            v3.setNombre("Lenteja");
            v3.setEspecie("Semilla");
            v3.setPeso(0.05);
            v3.setPrecio(0.04);
            v3.setCantidadcajapequenia(100);
            v3.setCantidadcajamediana(200);
            v3.setCantidadcajagrande(400);
            vegetalServicio.crear(v3); 
            
            Vegetal v4 = new Vegetal();
            v4.setNombre("Rabano");
            v4.setEspecie("Raiz");
            v4.setPeso(0.35);
            v4.setPrecio(0.44);
            v4.setCantidadcajapequenia(30);
            v4.setCantidadcajamediana(60);
            v4.setCantidadcajagrande(80);
            vegetalServicio.crear(v4); 
        }
        
        
         List<Lote> lotes = loteServicio.obtenerLotes();
        if (lotes == null || lotes.isEmpty()) 
        {
            
            Lote l = new Lote();
            l.setNombre("Lote1");
            l.setArea(2000);
            loteServicio.crear(l);
            
            Lote l1 = new Lote();
            l1.setNombre("Lote2");
            l1.setArea(3000);
            loteServicio.crear(l1);
            
            Lote l2 = new Lote();
            l2.setNombre("Lote3");
            l2.setArea(20500);
            loteServicio.crear(l2);
            
            Lote l3 = new Lote();
            l3.setNombre("Lote4");
            l3.setArea(3400);
            loteServicio.crear(l3);
            
            
    }
}
}
