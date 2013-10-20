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
 * @author Adam
 * This class is used for converting the item to a format the web pages can interpret.
 */
@XmlRootElement(name="Item")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "Item", propOrder = {
    "name"
})

public class ItemProxy{

    // The wrapped product
    private Item item;

    protected ItemProxy() { // Must have
    }
   
    public ItemProxy(Item item) { 
        this.item = item; 
    }
    
    @XmlElement(name="name")
    public String getName() {
        return item.getName();
    }
}