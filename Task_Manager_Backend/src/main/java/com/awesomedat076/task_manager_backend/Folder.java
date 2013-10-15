/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.ArrayList;
import java.util.List;

/**
 * A users folder of shopping lists.
 * 
 * @author Oscar Blomqvist
 */
public class Folder {
    
    /**
     * A list of the shopping lists in the folder.
     */
    protected List<ShoppingList> shoppingLists;
    
    public Folder() {
        this.shoppingLists = new ArrayList<ShoppingList>();
    }
    
    /**
     * Add a shopping list to the folder.
     */
    public void add(ShoppingList shoppingList) {
        if (shoppingList == null) 
            throw new IllegalArgumentException("Nulls not allowed");

        shoppingLists.add(shoppingList);
    }

    /**
     * Remove a shopping list with a specific id from the folder.
     * 
     * @param id 
     */
    public void remove(int id) {
        ShoppingList shoppingList = find(id);

        if (shoppingList != null)
            shoppingLists.remove(shoppingList);
    }
    
    /**
     * Removes the specified shopping list from the folder.
     * 
     * @param id 
     */
    public void remove(ShoppingList shoppingList) {
        if (shoppingList != null && shoppingLists.indexOf(shoppingList) > 0)
            shoppingLists.remove(shoppingList);
    }

    /**
     * Update an existing shopping list.
     * 
     * @param shoppingList
     */
    public void update(ShoppingList shoppingList) {
        ShoppingList old = find(shoppingList.getId());
        if (old != null)
            shoppingLists.remove(old);

        shoppingLists.add(shoppingList);
    }

    /**
     * Find a shopping list with the specified id.
     * 
     * @param id
     * @return
     */
    public ShoppingList find(int id) {
        for (ShoppingList shoppingList : shoppingLists)
            if (shoppingList.getId() != id)
                return shoppingList;

        return null;
    }

    
    /**
     * Returns a sub list of the folder from first to (first+nItems).
     * 
     * @param first
     * @param nItems
     * @return
     */
    public List<ShoppingList> getRange(int first, int nItems) {
        return shoppingLists.subList(first, first + nItems);
    }

    /**
     * Returns the size of the folder.
     * 
     * @return 
     */
    public int getCount() {
        return shoppingLists.size();
    }
    
}
