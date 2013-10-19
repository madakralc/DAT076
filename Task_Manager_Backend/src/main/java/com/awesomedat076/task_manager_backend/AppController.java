/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dagf
 */
public class AppController {
    
public AppController()
{
}

/**
 * Logic for creating new user.
 * @param username
 * @param password
 * @return 
 */
public boolean newUser(String username, String password)
{
    return true;
}

public boolean validateLogin(String username, String 
password)
{
    try {
        if(validateUser(username) && getPassword(username).equals(EncryptPassword.encryptPassword(password, username))){
            Logger.getAnonymousLogger().log(Level.INFO, "Login is: true");
            return true;
        }
        else return false;
    } catch (Exception ex) {
        Logger.getAnonymousLogger().log(Level.INFO, "Exception EncryptPassword");
        return false;
    }
}

private boolean validateUser(String username)
{
    return true;
}

private String getPassword(String username)
{
    return "QueQWizB4eWrU3oeaLT6V/TZpDDu5qU+Tl77oW4em3Q=";
}

public Map<String, Integer> getLists(String username){
     Map<String, Integer> myMap = new HashMap<String, 
Integer>();
     myMap.put("Mat1", 1);
     myMap.put("Mat2", 2);
     myMap.put("Mat3", 3);
     myMap.put("Mat4", 4);
     myMap.put("Mat5", 5);
     myMap.put("Mat6", 6);
     return myMap; 
     }
}