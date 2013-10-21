/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

/**
 * This is a class represents  an item in the the item list stored in the database. 
 * @author dagf
 */
public class Item {
    
    private String name;
    
    public Item(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
}
