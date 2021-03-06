/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Class used by the frontend to translate to xml so that the frontend can
 * interpret the data sent from the backend
 * 
 * @author dagf
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {
    "id", 
    "username", 
    "name", 
    "text"
})
@XmlRootElement(name = "ShoppingList")
public class ShoppingListProxy {
    
    private ShoppingList sl; 
    
    /**
     * Needed...
     */
    public ShoppingListProxy(){}
    
    public ShoppingListProxy(ShoppingList sl){
        this.sl = sl; 
    }
    
    @XmlElement(name="id")
    public int getId() {
        return sl.getId();
    }
    
    @XmlElement(name="name")
    public String getName() {
        return sl.getName();
    }
    
    @XmlElement(name="text")
    public String getText() {
        return sl.getText();
    }
    
    @XmlElement(name="username")
    public String getUsername() {
        return sl.getUsername();
    }
    
}
