/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.glabs.mongopersistence.config;

import com.gestor.glabs.mongopersistence.config.ConfigReader;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import java.util.logging.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author shane
 */
public class Connection {

    private static final Logger LOG = Logger.getLogger(Connection.class.getName());
    private static final Connection INSTANCE = new Connection();
    private final Datastore datastore;
    private final ConfigDTO config;

    private Connection() {
        ConfigReader reader = new ConfigReader();
        this.config = reader.getConfiguration();
        if (this.config != null) {
            MongoClientOptions mongoOptions = MongoClientOptions.builder().socketTimeout(this.config.getSocketTimeout().intValue()).connectTimeout(this.config.getConnectionTimeout().intValue()).build();
            MongoClient mongoClient;
            try {
                mongoClient = new MongoClient(new ServerAddress(this.config.getHost(), this.config.getPort().intValue()), mongoOptions);
            } catch (Exception e) {
                throw new RuntimeException("Error initializing MongoDB", e);
            }
            mongoClient.setWriteConcern(WriteConcern.SAFE);
            Morphia morphia = new Morphia();
            for (String packageN : this.config.getPackages()) {
                morphia.mapPackage(packageN, true);
            }
            this.datastore = morphia.createDatastore(mongoClient, this.config.getDatabase());
            this.datastore.ensureIndexes();
            LOG.info("Connection: " + this.config.toString() + " initialized");
        } else {
            LOG.info("Configuracion invalida, persistencia no disponible.");
            this.datastore = null;
        }
    }

    public static Connection instance() {
        return INSTANCE;
    }

    public Datastore getDatabase() {
        return this.datastore;
    }
}
