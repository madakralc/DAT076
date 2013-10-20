/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

/**
 * The core class is the container for the entity containers.
 * 
 * @author Oscar Blomqvist
 */
public class Core {
    
    /**
     * The name of the default persistence unit that should be used.
     */
    public static final String DEFAULT_PERSISTENCE_UNIT_NAME = "lalala";
    
    /**
     * The registry of the users.
     */
    protected UserRegistry userRegistry;
    
    /**
     * The registry of the shopping lists.
     */
    protected ListFolder listFolder;
    
    public Core (){
        userRegistry = new UserRegistry(DEFAULT_PERSISTENCE_UNIT_NAME);
        listFolder = new ListFolder(DEFAULT_PERSISTENCE_UNIT_NAME);
    }
    
    public Core (String puName){
        userRegistry = new UserRegistry(puName);
        listFolder = new ListFolder(puName);
    }
    
    /**
     * Returns the user registry.
     * 
     * @return 
     */
    public UserRegistry getUserRegistry(){
        return userRegistry;
    }
    
    /**
     * Returns the list folder.
     * 
     * @return 
     */
    public ListFolder getListFolder(){
        return listFolder;
    }
    
}
