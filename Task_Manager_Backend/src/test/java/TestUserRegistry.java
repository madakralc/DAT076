
import com.awesomedat076.task_manager_backend.Core;
import com.awesomedat076.task_manager_backend.TaskUser;
import com.awesomedat076.task_manager_backend.UserRegistry;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for the User Registry.
 * 
 * @author boscar
 */
public class TestUserRegistry {
    
    /**
     * The name of the persistence unit that should be used.
     */
    public static final String PERSISTENCE_UNIT_NAME = "task_manager_test_pu";
    
    /**
     * The core object.
     */
    static Core core;

    @Before
    public void before() {
        core = new Core(PERSISTENCE_UNIT_NAME);
    }
    
    /**
     * Adds 1 user to the database, and then checks if the database's only user 
     * has the correct name and email.
     */
    @Test
    public void testAddUser() {
        String testUserName = "karl";
        String testEmail = "karl@test.se";
        UserRegistry userRegistry = core.getUserRegistry();
        
        userRegistry.add(new TaskUser(testUserName, "asdf", testEmail));
        
        assertTrue(userRegistry.getRange(0, 1).get(0).getName().equals(testUserName) && 
                userRegistry.getRange(0, 1).get(0).getEmail().equals(testEmail));
        
        //Clears the user registry after the test.
        userRegistry.clear();
    }
    
    /**
     * Adds users to the user registry and makes sure that the user registry count matches
     * the amount of added users.
     */
    @Test
    public void testGetUserCount() {
        UserRegistry userRegistry = core.getUserRegistry();
        
        for(int i = 0; i < 10; ++i)
            userRegistry.add(new TaskUser("name" + i, "asdf", "user" + i + "@test.com"));
        
        assertTrue(userRegistry.getCount() == 10);
        
        //Clears the user registry after the test.
        userRegistry.clear();
    }
    
    /**
     * Adds 3 users to the database, then attempts to remove 2 of these users.
     * Finally the test checks if the count of the users is 1.
     */
    @Test
    public void testRemoveUser() {
        UserRegistry userRegistry = core.getUserRegistry();
        
        for(int i = 0; i < 3; ++i)
            userRegistry.add(new TaskUser("name" + i, "asdf", "user" + i + "@test.com"));
        
        /**
         * Remove user 0 and 1.
         */
        for(int i = 0; i < 2; ++i)
            userRegistry.remove("name" + i);
        
        assertTrue(userRegistry.getCount() == 1);
        
        //Clears the user registry after the test.
        userRegistry.clear();
    }
    
    /**
     * Test the find function in the user registry, which is the same as get by
     * name, as the users has their name is their primary key. Adds 3 users to
     * the database, and attempts to find one of them.
     */
    @Test
    public void testFindUser() {
        UserRegistry userRegistry = core.getUserRegistry();
        
        userRegistry.add(new TaskUser("karl", "asdf", "karl@test.com"));
        userRegistry.add(new TaskUser("erik", "asdf", "erik@test.com"));
        userRegistry.add(new TaskUser("gustav", "asdf", "gustav@test.com"));
        
        assertTrue(userRegistry.find("erik").getEmail().equals("erik@test.com"));
        
        //Clears the user registry after the test.
        userRegistry.clear();
    }
    
}