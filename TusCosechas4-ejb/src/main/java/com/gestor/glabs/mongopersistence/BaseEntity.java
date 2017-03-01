/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.glabs.mongopersistence;

import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

/**
 *
 * @author shane
 */
public abstract class BaseEntity implements Serializable {

    @Id
    protected ObjectId id;
    protected Date creationDate;
    protected Date lastChange;
    @Version
    private long version;

    public ObjectId getId() {
        return this.id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Date getLastChange() {
        return this.lastChange;
    }

    @PrePersist
    public void prePersist() {
        this.creationDate = (this.creationDate == null ? new Date() : this.creationDate);
        this.lastChange = (this.lastChange == null ? this.creationDate : new Date());
    }

    public String getIdString() {
        return this.id.toHexString();
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public abstract String toString();

}
