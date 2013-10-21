/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
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
    public ShoppingList(String name, String username){
        this.name = name;
        this.text = "";
        this.username = username;
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
     * Adds the specified text as well as a ';' to the text variable. 
     * This is the manner of which items are added to the list.
     * 
     * @param item 
     */
    public void addItem(String item){
        if(!text.equals(""))
            text += ";";
        text += item;
    }
    
    /**
     * Returns the text of the list as the individual items, which in the text
     * is seperated by ';'.
     * 
     * @return 
     */
    public List<Item> getTextAsItems(){
        StringTokenizer st = new StringTokenizer(text, ";");
        List<Item> items = new ArrayList<>();
        
        while(st.hasMoreTokens())
            items.add(new Item(st.nextToken()));
        
        return items;
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
