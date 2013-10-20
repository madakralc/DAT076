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
 *
 * @author dagf
 */
@XmlRootElement(name="ShopingList")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "ShopingList", propOrder = {
    "id","name"
})
public class ShoppingListProxy {
    
    private ShoppingList sl; 
    
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
    
    
}
