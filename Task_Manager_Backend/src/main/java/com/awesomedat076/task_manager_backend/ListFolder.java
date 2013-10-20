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
 * Handles the database communication with the lists.
 *
 * @author Oscar Blomqvist
 */
public class ListFolder {
    
    /**
     * The entity manager factory of the user registry.
     */
    private EntityManagerFactory emf;
    
    public ListFolder(String puName){
        emf = Persistence.createEntityManagerFactory(puName);
    }

    /**
     * Creates a new instance of the ListFolder.
     * 
     * @param puName
     * @return 
     */
    public static ListFolder newInstance(String puName) {
        return new ListFolder(puName);
    }

    /**
     * Find a single list in the list folder.
     * 
     * @param name
     * @return 
     */
    public ShoppingList find(int id) {
        EntityManager em = getEntityManager();
        ShoppingList list = (ShoppingList) em.find(ShoppingList.class, id);
        return list;
    }
    
    /**
     * Returns the shopping lists of the specified name.
     * 
     * @param name
     * @return 
     */
    public List<ShoppingList> getByName(String name) {
        List<ShoppingList> found = new ArrayList<>();
        for (ShoppingList l : getRange(0, getCount()))
            if (l.getName().equals(name))
                found.add(l);
        
        return found;
    }
    
    /**
     * Adds a shopping list to the database.
     * 
     * @param list 
     */
    public void add(ShoppingList list) {
        
        if (list == null)
            throw new IllegalArgumentException("Nulls not allowed");
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(list);
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
     * Removes a shopping list from the database.
     * 
     * @param id 
     */
    public void remove(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ShoppingList list = (ShoppingList) em.getReference(ShoppingList.class, id);
            em.remove(list);
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
    public void update(ShoppingList user) {
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
     * Gets a list of all the shoppping lists.
     * 
     * @return 
     */
    public List<ShoppingList> getLists() {
        return get(true, 0, 0);
    }

    /**
     * Gets a sublist of shopping lists from the database. The sublist consists of
     * first - (first + nItems).
     * 
     * @param first
     * @param nItems
     * @return 
     */
    public List<ShoppingList> getRange(int first, int nItems) {
        return get(false, first, nItems);
    }

    /**
     * Returns the size of the list folder.
     * 
     * @return 
     */
    public int getCount() {
        EntityManager em = getEntityManager();
        int count = -1;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ShoppingList> rt = cq.from(ShoppingList.class);
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
     * Returns all the lists that has the specified owner.
     * 
     * @param username
     * @return 
     */
    public List<ShoppingList> getByUsername(String username){
        List<ShoppingList> found = new ArrayList<>();
        for (ShoppingList l : getLists())
            if (l.getUsername().equals(username))
                found.add(l);
        
        return found;
    }
    
    /**
     * Removes all the lists that has the specified owner.
     * 
     * @param username
     * @return 
     */
    public void removeByUsername(String username){
        //Get all the lists.
        List<ShoppingList> lists = getLists();
        
        for(ShoppingList list : lists)
            remove(list.getId());
    }
    
    /**
     * Gets a sublist of the shopping lists in the database. 
     */
    protected List<ShoppingList> get(boolean all, int first, int max) {
        EntityManager em = getEntityManager();
        List<ShoppingList> found = new ArrayList<>();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ShoppingList.class));
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
     * Clears all the lists in the list folder, primarily used for testing purposes.
     */
    public void clear() {
        //Get all the lists.
        List<ShoppingList> lists = getLists();
        
        for(ShoppingList list : lists)
            remove(list.getId());
    }
    
}
