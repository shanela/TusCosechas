/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.web.utils;


import ec.edu.espe.shalued.modelo.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class MenuSistema {

    private static final Logger LOG = Logger.getLogger(MenuSistema.class.getName());

    public static String GenerateMenuEmpleado(Usuario usuario) {
        String menu = "";
        if (usuario!= null) {
            switch (usuario.getTipo()) {
                case "GERENTE":
                    break;
                case "VENDEDOR":
                    break;
                case "CONDUCTOR":
                       menu = "/faces/views/PedidosTransportista";
                    break;
                case "BODEGUERO":
                    break;
                default:
                    LOG.log(Level.INFO, "Ningun rol de empleado seleccionado:", usuario.getTipo());
            }
        }
        return menu;
    }

    public static String GenerateMenuCliente() {
        String menu = "/faces/views/menuCli";
        return menu;
    }
    
    public static String GenerateMenuEmpleado() {
        String menu = "/faces/views/PedidosTransportista";
        return menu;
    }
    
    

}
