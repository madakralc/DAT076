/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public boolean createNewUser(String username, String password, String email)
    {
        String encryptedPassword = null;
        if(validateUser(username) || !validateInput(password) || !validateInput(email))
            return false;
        try
        {
            encryptedPassword = EncryptPassword.encryptPassword(password, username);
        }catch(Exception ex)
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Exception EncryptPassword at:{0}", ex.getStackTrace());
        }
        if(validateInput(encryptedPassword))
            userRegistry.add(new TaskUser(username,encryptedPassword,email));
        else
        {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Encrypted password is not uset");
            return false;
        }
        
        return validateUser(username)?true:false;
    }
    /**
     * 
     * @param username
     * @return true if username doesn't exist else false
     */
    private boolean validateUser(String username)
    {
        if(!validateInput(username))
            return false;
        for(TaskUser user : userRegistry.getUsers())
            if(user.getName().equalsIgnoreCase(username))
                return true;
        return false;
        
    }
    private boolean validateInput(String input)
    {
        return (input == null || input.isEmpty() || input.equalsIgnoreCase(""))?false:true;
    }
    private String getUserPassword(String username)
    {
        if(!validateInput(username))
            return "";
        String userpass = userRegistry.find(username).getPassword();
        return validateInput(userpass)?userpass:"";
    }
    
    public boolean validateLogin(String username, String password)
    {
        try
        {
            return (validateUser(username) && getUserPassword(username).equals(EncryptPassword.encryptPassword(password, username)))?true:false;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception EncryptPassword at:{0}", ex.getStackTrace());
            return false;
        }
    }
    
    public boolean addList(String name, String text, String username)
    {
        if(!validateInput(name) || !validateInput(text) || validateUser(username))
            return false;
        //Check so listname is unique for this user
        if(validateList(name,username))
            return false;
        
        listFolder.add(new ShoppingList(name,text,username));
        
        return validateList(name,username)?true:false;
    }
    
    public boolean validateList(String listname, String username)
    {
        for(ShoppingList list : listFolder.getByUsername(username))
           if(list.getName().equalsIgnoreCase(listname))
               return true;
        return false;
    }
    
    public boolean removeList(int id)
    {
        if(id < 1)
            return false;
        return false;
    }
}
