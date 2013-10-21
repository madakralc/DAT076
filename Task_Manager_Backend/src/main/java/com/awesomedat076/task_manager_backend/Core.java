/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The core class is the container for the entity containers.
 * 
 * @author Oscar Blomqvist
 */
public class Core {
    
    /**
     * A singleton instance of the core class with the default persistence unit.
     */
    protected static Core me;
    
    /**
     * A flag that shows if any instance of core has created test data.
     */
    protected static boolean testDataAdded = false;
    
    /**
     * The name of the default persistence unit that should be used.
     */
    public static final String DEFAULT_PERSISTENCE_UNIT_NAME = "task_manager_pu";
    
    /**
     * The registry of the users.
     */
    protected UserRegistry userRegistry;
    
    /**
     * The registry of the shopping lists.
     */
    protected ListFolder listFolder;
    
    public Core (){
        createDAOs(DEFAULT_PERSISTENCE_UNIT_NAME);
        //addTestData();
    }
    
    public Core (String puName){
        createDAOs(puName);
        //addTestData();
    }
    
    private void createDAOs(String puName){
        userRegistry = new UserRegistry(puName);
        listFolder = new ListFolder(puName);
    }
    
    /**
     * Get the singleton instance of Core, with the default persistence unit.
     * 
     * @return 
     */
    public static Core getInstance(){
        if(me == null)
            me = new Core();
        return me;
    }
    
    /**
     * Adds some data to the database. Used for testing purposes.
     */
    public void addTestData(){
        if(testDataAdded){
            Logger.getAnonymousLogger().log(Level.INFO, "Test data allready added.");
            return;
        }
        
        /**
         * The usernames of the different users.
         */
        String oscarUsername = "oscar@test.se";
        String fredrikUsername = "fredrik@test.se";
        String adamUsername = "adam@test.se";
        String dagUsername = "dag@test.se";
        
        try {
            /**
             * Add some users to the database.
             */
            createNewUser(oscarUsername, "test", "test@test.se");
            createNewUser(fredrikUsername, "test", "fredrik@test.se");
            createNewUser(adamUsername, "test", "adam@test.se");
            createNewUser(dagUsername, "test", "dag@test.se");
        
                } catch (Exception ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**
         * Add some list to each user.
         */
        addList("grönsaker och sånt", "äpple;päron;citron;gurka;mandarin", oscarUsername);
        addList("tacos", "salsa;köttfärs;guacamole", oscarUsername);
        
        addList("städ", "hink;trasor;fönsterputs;grönsåpa", fredrikUsername);
        addList("tacos", "salsa;köttfärs;guacamole", fredrikUsername);
        
        addList("grönsaker och sånt", "äpple;päron;citron;gurka;mandarin", adamUsername);
        addList("snacks", "grillchips;dumle;djungelvrål", adamUsername);
        
        addList("städ", "hink;trasor;fönsterputs;grönsåpa", dagUsername);
        addList("snacks", "grillchips;dumle;djungelvrål", dagUsername);
        
        testDataAdded = true;
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
        if(!validateInput(username)){
            Logger.getAnonymousLogger().log(Level.INFO, "Username {0} is not valid.", username);
            return false;
        }for(TaskUser user : userRegistry.getUsers()){
            Logger.getAnonymousLogger().log(Level.INFO, "{0} ?= {1}", new Object[]{username, user.getName()});
            if(user.getName().equalsIgnoreCase(username)){
                Logger.getAnonymousLogger().log(Level.INFO, "it is a match!");
                return true;
            }
        }
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
        Logger.getAnonymousLogger().log(Level.INFO, "Password in db is:{0}", userpass);
        return validateInput(userpass)?userpass:"";
    }
    
    public boolean validateLogin(String username, String password)
    {
        try
        {   
            Logger.getAnonymousLogger().log(Level.INFO, "Password entered is: {0} : username = {1} : encrypted password is = {2}", new Object[]{password, username, EncryptPassword.encryptPassword(password, username)});
            Logger.getAnonymousLogger().log(Level.INFO, "ValidateUser={0}", validateUser(username));
            Logger.getAnonymousLogger().log(Level.INFO, "{0} ?= {1}", new Object[]{getUserPassword(username), EncryptPassword.encryptPassword(password, username)});
            return (validateUser(username) && getUserPassword(username).equalsIgnoreCase(EncryptPassword.encryptPassword(password, username)))?true:false;
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception EncryptPassword at:{0}", ex.getStackTrace());
            return false;
        }
    }
    
    public boolean addList(String name, String text, String username)
    {
        if(!validateInput(name) || !validateInput(text) || !validateUser(username))
            return false;
        //Check so listname is unique for this user
        if(validateList(name,username))
            return false;
        
        listFolder.add(new ShoppingList(name,text,username));
        
        return validateList(name,username)?true:false;
    }
    
    public boolean addList(String name, String username)
    {
        if(!validateInput(name) || !validateUser(username))
            return false;
        //Check so listname is unique for this user
        if(validateList(name,username))
            return false;
        
        listFolder.add(new ShoppingList(name,username));
        
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
        listFolder.remove(id);
        if(listFolder.find(id) instanceof ShoppingList)
            return false;
        return true;
    }
    
    public ShoppingList getList(int id)
    {
        ShoppingList list = null;
        if(id < 1)
            return list;
        list = listFolder.find(id);

        return list.getId() == id ? list : null;
    }
    
    public List<ShoppingList> getUserLists(String username)
    {
        if(!validateUser(username))
            return null;
        return listFolder.getByUsername(username);
    }
}
