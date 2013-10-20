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
 * Class that represents a user.
 * 
 * @author Oscar Blomqvist
 */
@Entity
public class TaskUser implements Serializable {
    
    /**
     * The folder that contains the users lists.
     */
    protected transient Folder folder;
    
    /**
     * The name of the user.
     */
    @Id
    @GeneratedValue
    protected String name;
    
    /**
     * The password of the user.
     */
    protected String password;
    
    /**
     * The email of the user.
     */
    protected String email;

    public TaskUser() {
        this.password = "";
        this.email = "";
    }
    
    /**
     * @param id
     * @param name
     * @param email
     */
    public TaskUser(String name, String password, String email){
        this.name = name;
        this.password = password;
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
    
    /**
     * Returns the password of the user.
     * 
     * @return 
     */
    public String getPassword(){
        return password;
    }
    
    @Override
    public String toString() {
        return "TaskUser{name=" + name + ", password=" + password + ", email=" + email + '}';
    }
    
}
