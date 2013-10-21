/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Adam
 */
@Path("items")
public class TaskManagerResource {
    
    @Context
    private UriInfo uriInfo;
    
    protected Core core = Core.getInstance();
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUsers(){
        List<TaskUserProxy> proxyUsers = new ArrayList<>();
        List<TaskUser> users = core.getUserRegistry().getUsers();
        
        for(TaskUser user : users)
            proxyUsers.add(new TaskUserProxy(user));
        
        GenericEntity<List<TaskUserProxy>> gl = new GenericEntity<List<TaskUserProxy>>(proxyUsers) {};
        
        return  Response.ok(gl).build();
    }
    
    @GET
    @Path("user_count")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getUserCount(){
        PrimitiveJSONWrapper pjw = new PrimitiveJSONWrapper(core.getUserRegistry().getCount());
        Logger.getAnonymousLogger().log(Level.INFO, "Amount of users: {0}", core.getUserRegistry().getCount());
        return Response.ok(pjw, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createUser(@FormParam("name") String name, 
                               @FormParam("password") String password, 
                               @FormParam("email") String email){
        try{
            boolean success = core.createNewUser(name, password, email);
            return Response.ok(String.valueOf(success)).build();
        }catch(IllegalArgumentException | UriBuilderException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getList(@PathParam("id") int id){
        return  Response.ok(new ShoppingListProxy(core.getList(id))).build();
    }
    
    @GET
    @Path("lists")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getLists(){
        List<ShoppingListProxy> proxyLists = new ArrayList<>();
        List<ShoppingList> lists = core.getListFolder().getLists();
        
        for(ShoppingList list : lists)
            proxyLists.add(new ShoppingListProxy(list));
        
        GenericEntity<List<ShoppingListProxy>> ge = new GenericEntity<List<ShoppingListProxy>>(proxyLists) {};
        
        return  Response.ok(ge).build();
    }

    @GET
    @Path("lists/{username}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getListsByUsername(@PathParam("username") String username){
        List<ShoppingListProxy> proxyLists = new ArrayList<>();
        List<ShoppingList> lists = core.getUserLists(username);
        
        for(ShoppingList list : lists)
            proxyLists.add(new ShoppingListProxy(list));
        
        GenericEntity<List<ShoppingListProxy>> ge = new GenericEntity<List<ShoppingListProxy>>(proxyLists) {};
        
        return  Response.ok(ge).build();
    }
    
    @POST
    @Path("add/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createList(@FormParam("name") String name, @PathParam("username") String username){
        try{
            boolean success = core.addList(name, username);
            return Response.ok(String.valueOf(success)).build();
        }catch(IllegalArgumentException | UriBuilderException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DELETE
    @Path("remove/{id}")
    public Response removeList(@PathParam("id") int id) {
        try{
            boolean success = core.removeList(id);
            return Response.ok(String.valueOf(success)).build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("items")
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
    @Path("itemList")
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
    
    @POST
    @Path("init")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response initDb() {
        Logger.getAnonymousLogger().log(Level.INFO, "initDB}");
        Core.getInstance().addTestData();
        return Response.ok().build();
    }
    
}
