/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

/**
 * A shopping list object.
 * 
 * @author Oscar Blomqvist
 */
public class ShoppingList {
    
    /**
     * The id of the shopping list.
     */
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
     * @param id
     * @param name
     * @param text 
     */
    public ShoppingList(int id, String name, String text){
        this.id = id;
        this.name = name;
        this.text = text;
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
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", text=" + text + '}';
    }
    
}
