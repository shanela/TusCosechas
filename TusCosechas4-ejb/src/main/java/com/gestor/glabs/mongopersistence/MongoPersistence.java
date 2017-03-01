/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.glabs.mongopersistence;

import com.gestor.glabs.mongopersistence.config.Connection;
import org.mongodb.morphia.Datastore;

/**
 *
 * @author shane
 */
public class MongoPersistence {

    private final Datastore mds;

    public MongoPersistence() {
        this.mds = Connection.instance().getDatabase();
    }

    public Datastore context() {
        return this.mds;
    }
}
