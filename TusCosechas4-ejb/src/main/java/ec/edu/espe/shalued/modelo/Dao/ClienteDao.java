/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.shalued.modelo.Dao;

import ec.edu.espe.shalued.modelo.Cliente;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author SHANE
 */
public class ClienteDao extends BasicDAO<Cliente,ObjectId>{
    
    public ClienteDao(Class<Cliente> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
    
}
