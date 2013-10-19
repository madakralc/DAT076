/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dagf
 */
@WebServlet(name = "TaskManagerServerlet", urlPatterns = {"/tskmgr/*"})
public class TaskManagerServerlet {
    



   // public List<Product> plist;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, Exception { 
         
        session = request.getSession();
        
        try{
           // container = (ContainerNavigator) session.getAttribute("container");
            Logger.getAnonymousLogger().log(Level.INFO,"Nu är vi i try: " );
        }
        catch(Exception e){
          
           // session.setAttribute("container", container);
            Logger.getAnonymousLogger().log(Level.INFO,"Nu är vi i catch: ");
        }
                
        String action = request.getParameter("action");
        String view = request.getParameter("view");
        
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        
        Logger.getAnonymousLogger().log(Level.INFO,"ContextListener objektet är skapat med så här många items, nu är vi i ProductServlet: " );
        
        //Om det kom in en action
        if (action != null) {
            switch (action) {
                case "login":
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    Logger.getAnonymousLogger().log(Level.INFO, "Action blev {0} username = {1} password = {2}", new Object[]{action, username, password});
                    //request.getRequestDispatcher("/index.jspx").forward(request, response);
                    if(username != null & password != null){
                        password = EncryptPassword.encryptPassword(password, username);
                        if(password.equals(password))
                        {
                            //Autentication passed
                             request.setAttribute("USERNAME", username);
                             request.getRequestDispatcher("URL").forward(request, response);
                        }else{
                            //Autentication failed
                            request.getRequestDispatcher("URL").forward(request, response);
                        }   
                    }else{
                        //Autentication failed, no password or no username
                        request.getRequestDispatcher("URL").forward(request, response);
                    }
                break;
                
                default:
                    ;
            }
        }     

        
    }
}