/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestor.glabs.mongopersistence.config;

/**
 *
 * @author shane
 */
public class ConfigDTO {

    private String host;
    private Integer port;
    private String database;
    private Integer socketTimeout;
    private Integer connectionTimeout;
    private String[] packages;

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Integer getSocketTimeout() {
        return this.socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String[] getPackages() {
        return this.packages;
    }

    public void setPackages(String[] packages) {
        this.packages = packages;
    }

    public String toString() {
        String p1 = "ConfigDTO{host=" + this.host + ", port=" + this.port + ", database=" + this.database + ", packages:";
        if (this.packages != null) {
            for (String pac : this.packages) {
                p1 = p1 + pac + ", ";
            }
        }
        return p1 + '}';
    }
}
