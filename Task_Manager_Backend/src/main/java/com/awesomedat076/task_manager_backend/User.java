/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

/**
 * Class that represents a user.
 * 
 * @author Oscar Blomqvist
 */
public class User extends Entity {
    
    /**
     * The folder that contains the users lists.
     */
    protected Folder folder;
    
    /**
     * The name of the user.
     */
    protected String name;
    
    /**
     * The email of the user.
     */
    protected String email;
    
    /**
     * @param id
     * @param name
     * @param email 
     * @param folder
     */
    public User(int id, String name, String email, Folder folder){
        super(id);
        this.name = name;
        this.email = email;
    }
    
    /**
     * @param id
     * @param name
     * @param email
     */
    public User(int id, String name, String email){
        super(id);
        this.name = name;
        this.email = email;
    }
    
}
