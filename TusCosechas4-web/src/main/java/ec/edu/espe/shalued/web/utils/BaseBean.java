/*
 * Corena
 * Sistema: Documental Corena
 * Creado: 9-Ene-2017 - 01:04:05
 * 
 * Los contenidos de este archivo son propiedad intelectual de Corena
 * y se encuentran protegidos por la licencia: "Corena JEE Products".
 * 
 * Usted puede encontrar una copia de esta licencia en: 
 *   http://www.corena.com.ec
 * 
 * Copyright 2017-2018 Corena Todos los derechos reservados.
 */
package ec.edu.espe.shalued.web.utils;

import java.io.Serializable;

/** 
 * BackingBean abstracto que ayuda en el manejo de controles y mensajes.
 * @author Henry Coral
 * @version 1.0
 */
public class BaseBean implements Serializable{
    
    private static final String MALE_NEW = "Nuevo ";
    private static final String FEMALE_NEW = "Nueva ";
    private static final String EDIT = "Edici\u00f3n de ";
    
    private static final long serialVersionUID = -2403645697817468487L;
    
    
    protected boolean add;
    protected boolean modify;
    protected boolean remove;
    protected String panelTitle;
    protected String gender="M";
    protected String entityName;
    protected boolean disbledButtons;
    
    
    public void reset() {
        this.add = false;
        this.modify = false;
        this.remove = false;
        this.disbledButtons = false;
    }
    
    public void startAdd() {
        this.add = true;
        this.modify = false;
        this.remove = false;
        this.disbledButtons = true;
        this.panelTitle = (gender.equals("M") ? MALE_NEW : FEMALE_NEW) + this.entityName;
    }
    
    public void starModify() {
        this.add = false;
        this.modify = true;
        this.remove = false;
        this.disbledButtons = true;
        this.panelTitle = EDIT + this.entityName;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public String getPanelTitle() {
        return panelTitle;
    }

    public void setPanelTitle(String panelTitle) {
        this.panelTitle = panelTitle;
    }
    
    public void setFemale() {
        this.gender = "F";
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public boolean isDisbledButtons() {
        return disbledButtons;
    }
    
}
