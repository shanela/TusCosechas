/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.modelo;

import org.mongodb.morphia.annotations.Embedded;

/**
 *
 * @author luis
 */
@Embedded
public class Direccion {
    
    @Embedded
    private Canton canton;
    private String calleprincipal;
    private String callesecundaria;
    private String codigopostal;
}
