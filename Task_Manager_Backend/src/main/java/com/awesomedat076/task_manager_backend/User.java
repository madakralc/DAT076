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
public class User {
    
    /**
     * The folder that contains the users lists.
     */
    protected transient Folder folder;
    
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
     */
    public User(String name, String email){
        this.name = name;
        this.email = email;
    }
    
    /**
     * Adds a shopping list to the users folder.
     * 
     * @param shoppingList 
     */
    public void addShoppingListToFolder(ShoppingList shoppingList) {
        folder.add(shoppingList);
    }

    /**
     * Removes a shopping list from the users folder.
     * 
     * @param shoppingList 
     */
    public void removeShoppingListFromFolder(ShoppingList shoppingList) {
        folder.remove(shoppingList.getId());
    }

    /**
     * Clears the users folder.
     */
    public void emptyFolder() {
        folder = new Folder();
    }

    /**
     * Get the users folder.
     * 
     * @return 
     */
    public Folder getFolder() {
        return folder;
    }

    /**
     * Returns the users email.
     * 
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the name of the user.
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "User{" + ", name=" + name + ", email=" + email + '}';
    }
    
}
