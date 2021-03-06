/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;

/**
 *
 * Our rest file used by the frontend to do many tasks. For example there are
 * "functions" to add users and show the lists in an xml format.
 * 
 * We are using many different "cases" of rest, for example both POST/GET,
 * and different params like FormParam and PathParam
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
        return Response.ok(String.valueOf(core.getUserRegistry().getCount()), MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("add_user")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createUser(@FormParam("username") String username, 
                               @FormParam("password") String password, 
                               @FormParam("email") String email){
        try{
            boolean success = core.createNewUser(username, password, email);
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
        Logger.getAnonymousLogger().log(Level.INFO, "Nu ska alla listor ha kommit fram");
        GenericEntity<List<ShoppingListProxy>> ge = new GenericEntity<List<ShoppingListProxy>>(proxyLists) {};
        
        return  Response.ok(ge).build();
    }

    @GET
    @Path("lists/{username}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getListsByUsername(@PathParam("username") String username){
        List<ShoppingListProxy> proxyLists = new ArrayList<>();
        List<ShoppingList> lists = core.getUserLists(username);
        if(lists == null) lists = new LinkedList(); 
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
    @Path("items/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getListItems(@PathParam("id") int id) {
        List<ItemProxy> proxyItems = new ArrayList<>();
        List<Item> items = core.getList(id).getTextAsItems();
        if(items == null) items = new LinkedList(); 
        
        for(Item item : items)
            proxyItems.add(new ItemProxy(item));
        
        GenericEntity<List<ItemProxy>> ge = new GenericEntity<List<ItemProxy>>(proxyItems) {};
        
        return  Response.ok(ge).build();
    }
    
    @GET
    @Path("add_item/{id}/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response addItemToList(@PathParam("id") int id, @PathParam("name") String name) {
        core.addItemToList(id, name);
        return  Response.ok().build();
    }

    
    @GET
    @Path("init")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response initDb() {
        Logger.getAnonymousLogger().log(Level.INFO, "initDB");
        Core.getInstance().addTestData();
        return Response.ok().build();
    }
    
    @POST
    @Path("id/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response setCurrentListId(@PathParam("id") int id){
        try{
            core.setCurrentId(id);
            return Response.ok().build();
        }catch(IllegalArgumentException | UriBuilderException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("id")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCurrentListId(){
        try{
            PrimitiveJSONWrapper currentId = new PrimitiveJSONWrapper(new Integer(core.getCurrentId()));
            Logger.getAnonymousLogger().log(Level.INFO, "jsonWrapperValue: {0}", currentId.getValue());
            return Response.ok(String.valueOf(core.getCurrentId()), MediaType.APPLICATION_JSON).build();
        }catch(IllegalArgumentException | UriBuilderException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
