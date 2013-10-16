/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.awesomedat076.task_manager_backend;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * An entity container that has all the users.
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
     * Get a user by the user name.
     * 
     * @param name
     * @return 
     */
    public List<User> getByName(String name) {
        List<User> found = new ArrayList<User>();
        for (User c : getRange(0, getCount()))
            if (c.getName().equals(name))
                found.add(c);
        
        return found;
    }
    
    /**
     * Adds a user to the user registry.
     * 
     * @param t
     */
    public void add(User user) {
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
            User user = (User) em.getReference(User.class, name);
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
    public void update(User user) {
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
    public User find(String name) {
        EntityManager em = getEntityManager();
        User user = (User) em.find(User.class, name);
        return user;
    }

    /**
     * Gets a sublist of user from the user registry. The sublist consists of
     * first - (first + nItems).
     * 
     * @param first
     * @param nItems
     * @return 
     */
    public List<User> getRange(int first, int nItems) {
        return get(false, nItems, first);
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
            Root<User> rt = cq.from(User.class);
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
    protected List<User> get(boolean all, int max, int first) {
        EntityManager em = getEntityManager();
        List<User> found = new ArrayList<User>();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
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
        List<User> users= get(true, 1, 1);
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            
            /**
             * Remove all the users.
             */
            for(User user : users){
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
