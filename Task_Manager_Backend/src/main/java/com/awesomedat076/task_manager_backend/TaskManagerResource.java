/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Adam
 */

@Path("items")
public class TaskManagerResource {

    
    @GET
    @Path("/items")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ItemProxy> getListItems(@QueryParam("listId") int listId) {
        //****************************************************************REMOVE IN FINAL ************************DEBUGG! 
        ShoppingList sl = Core.getInstance().getList(listId);
        Logger.getAnonymousLogger().log(Level.INFO, "getListItems for list{0}", listId);
        
        LinkedList<ItemProxy> items =  new LinkedList();
        
        StringTokenizer st = new StringTokenizer(sl.getText(), ";");
        while(st.hasMoreTokens())
        {
            items.add(new ItemProxy(new Item(st.nextToken())));
        }

        return items;
    }
    
    
    @GET
    @Path("/itemList")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ShoppingListProxy> getUsersLists(@QueryParam("USERNAME") String username) {
        Logger.getAnonymousLogger().log(Level.INFO, "getListItems for list{0}", username);
        
        List<ShoppingList> list;
        List<ShoppingListProxy> proxyList = new LinkedList();
        
        list = (LinkedList<ShoppingList>) Core.getInstance().getUserLists(username); 
        for(ShoppingList l : list) {
            proxyList.add(new ShoppingListProxy(l));
        }
        return proxyList;
    }
    
    @GET
    @Path("/init")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String initDb() {
        
        Logger.getAnonymousLogger().log(Level.INFO, "intiDB}");
        Core.getInstance().addTestData();
        return "data"; 
    }
    
}
