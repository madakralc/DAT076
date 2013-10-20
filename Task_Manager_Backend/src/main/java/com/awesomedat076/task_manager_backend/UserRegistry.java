/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Handles the database communication with the users.
 *
 * @author Oscar Blomqvist
 */
public class UserRegistry {
    
    /**
     * The entity manager factory of the user registry.
     */
    private EntityManagerFactory emf;
    
    public UserRegistry(String puName){
        emf = Persistence.createEntityManagerFactory(puName);
    }

    /**
     * Creates a new instance of the UserRegistry.
     * 
     * @param puName
     * @return 
     */
    public static UserRegistry newInstance(String puName) {
        return new UserRegistry(puName);
    }

    /**
     * Gets the user with a specific name.
     * 
     * @param name
     * @return 
     */
    public List<TaskUser> getByName(String name) {
        List<TaskUser> found = new ArrayList<>();
        for (TaskUser c : getUsers())
            if (c.getName().equals(name))
                found.add(c);
        
        return found;
    }
    
    /**
     * Adds a user to the user registry.
     * 
     * @param t
     */
    public void add(TaskUser user) {
        
        if (user == null)
            throw new IllegalArgumentException("Nulls not allowed");
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    /**
     * Create and return an entity manager.
     * 
     * @return 
     */
    protected EntityManager getEntityManager() {
        EntityManager em = emf.createEntityManager();
        return em;
    }

    /**
     * Removes a user from the user registry.
     * 
     * @param name 
     */
    public void remove(String name) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TaskUser user = (TaskUser) em.getReference(TaskUser.class, name);
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Updates a user in the user registry.
     * 
     * @param user 
     */
    public void update(TaskUser user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Find a user in the user registry with a specified name.
     * 
     * @param name
     * @return 
     */
    public TaskUser find(String name) {
        EntityManager em = getEntityManager();
        TaskUser user = (TaskUser) em.find(TaskUser.class, name);
        return user;
    }
    
    /**
     * Gets a list of all the users.
     * 
     * @return 
     */
    public List<TaskUser> getUsers() {
        return get(true, 0, 0);
    }

    /**
     * Gets a sublist of user from the user registry. The sublist consists of
     * first - (first + nItems).
     * 
     * @param first
     * @param nItems
     * @return 
     */
    public List<TaskUser> getRange(int first, int nItems) {
        return get(false, first, nItems);
    }

    /**
     * Returns the size of the user registry.
     * 
     * @return 
     */
    public int getCount() {
        EntityManager em = getEntityManager();
        int count = -1;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TaskUser> rt = cq.from(TaskUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            count = ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
        } finally {
            em.close();
        }
        return count;
    }
    
    /**
     * Gets a sublist of all the users in the user registry. 
     */
    protected List<TaskUser> get(boolean all, int first, int max) {
        EntityManager em = getEntityManager();
        List<TaskUser> found = new ArrayList<>();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TaskUser.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(max);
                q.setFirstResult(first);
            }
            found.addAll(q.getResultList());
        } catch (Exception ex) {
        } finally {
            em.close();
        }
        return found;
    }
    
    /**
     * Clears all the users in the user registry.
     */
    public void clear() {
        
        //Get all the users.
        List<TaskUser> users = getUsers();
        
        for(TaskUser user : users)
            remove(user.getName());
    }
    
}
