/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.modelo.Dao;

import ec.edu.espe.shalued.modelo.Pedido;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author SHANE
 */
public class PedidoDao extends BasicDAO<Pedido,ObjectId>{
    
    public PedidoDao(Class<Pedido> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
    
}
