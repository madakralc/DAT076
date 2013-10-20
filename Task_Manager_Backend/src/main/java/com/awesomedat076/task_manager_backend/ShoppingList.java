/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A shopping list object.
 * 
 * @author Oscar Blomqvist
 */
@Entity
public class ShoppingList implements Serializable {
    
    /**
     * The id of the shopping list.
     */
    @Id
    @GeneratedValue
    protected int id;
    
    /**
     * The name of the shopping list.
     */
    protected String name;
    
    /**
     * The text of the shopping list.
     */
    protected String text;
    
    /**
     * The name of the user that owns this shopping list.
     */
    protected String username;

    public ShoppingList() {
        this.name = "";
        this.text = "";
        this.username = "";
    }
    
    /**
     * @param id
     * @param name
     * @param text 
     */
    public ShoppingList(String name, String text, String username){
        this.name = name;
        this.text = text;
        this.username = username;
    }
    
    /**
     * @param id
     * @param name
     * @param text 
     * @param username
     */
    public ShoppingList(int id, String name, String text, String username){
        this.id = id;
        this.name = name;
        this.text = text;
        this.username = username;
    }
    
    /**
     * Returns the id of this entity.
     * 
     * @return
     */
    public int getId(){
        return id;
    }
    
    /**
     * 
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return
     */
    public String getText(){
        return text;
    }
    
    /**
     * 
     * @return
     */
    public String getUsername(){
        return username;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "List{" + "id=" + id + ", name=" + name + ", text=" + text + ", username=" + username + '}';
    }
    
}
