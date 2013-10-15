/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.Random;

/**
 * Abstract class that represents an entity.
 * 
 * @author Oscar Blomqvist
 */
public abstract class Entity {
    
    /**
     * The id of the entity.
     */
    protected int id;
    
    protected Entity(){
        this.id = new Random().nextInt(01750);
    }
    
    protected Entity(int id){
        this.id = id;
    }
    
    /**
     * Returns the id of this entity.
     * 
     * @return
     */
    public int getId(){
        return id;
    }
    
}
