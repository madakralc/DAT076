/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Adam
 */

@Path("items")
public class TaskManagerResource {
   
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Item> selectAll() {
        Logger.getAnonymousLogger().log(Level.INFO, "*** SelectAll");
        
        AppController ap = new AppController();
        
        List<Item> items = (List<Item>) ap.getItemList(1337));
        return items;
    }
    
    
}
