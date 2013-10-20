/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dagf
 */
public class AppController {

    public AppController(){}

    /**
     * Logic for creating new user.
     * @param username
     * @param password
     * @return 
     */

    
    public Map<String, Integer> getLists(String username){
         Map<String, Integer> myMap = new HashMap<>();
         myMap.put("Mat1", 1337);
         myMap.put("Mat2", 2);
         myMap.put("Mat3", 3);
         myMap.put("Mat4", 4);
         myMap.put("Mat5", 5);
         myMap.put("Mat6", 6);

         return myMap; 
    }


    public List<Item> getItemList(int listId){
        LinkedList<Item> itemList = new LinkedList<>(); 
        //Separera vid ";"
        StringTokenizer st = new StringTokenizer(getItemData(listId), ";");
        while(st.hasMoreTokens())
        {
            itemList.add(new Item(st.nextToken()));
        }
         return itemList; 
    }

    private String getItemData(int listId){
        //DETTA MÅSTE ÄNDRAS TILL EN RIKTIG DATASOURCE! 
        if(listId == 1337) return "Blöjor;Mjölk;Tomat;Fisk;Chabli;ÖL";
        else return "";
    }
    
}