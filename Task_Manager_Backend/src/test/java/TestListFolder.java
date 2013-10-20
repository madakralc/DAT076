
import com.awesomedat076.task_manager_backend.Core;
import com.awesomedat076.task_manager_backend.ListFolder;
import com.awesomedat076.task_manager_backend.ShoppingList;
import com.awesomedat076.task_manager_backend.TaskUser;
import com.awesomedat076.task_manager_backend.UserRegistry;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for the List Folder.
 * 
 * @author boscar
 */
public class TestListFolder {
    
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
     * Adds 1 user to the database, then adds a shopping list to that user. Finally 
     * checks if the added user has exactly one list.
     */
    @Test
    public void testAddList() {
        /**
         * Add the user.
         */
        String testUserName = "karl";
        UserRegistry userRegistry = core.getUserRegistry();
        userRegistry.add(new TaskUser(testUserName, "asdf", "karl@test.se"));
        
        /**
         * Add the list.
         */
        ListFolder listFolder = core.getListFolder();
        listFolder.add(new ShoppingList("test_list_001", "hej; hej; hejhej;", testUserName));
        
        /**
         * Checks if the test user has exactly one list.
         */
        assertTrue(listFolder.getByUsername(testUserName).size() == 1);
        
        //Clears the user registry adn the list folder after the test.
        userRegistry.clear();
        listFolder.clear();
        
    }
    
    /**
     * Adds 1 user to the database, then adds 3 shopping lists to that user. After
     * that the two lists that was added first will be removed. Finally check if
     * the user has exactly 1 list, and if that list has the same text as the list
     * that was added last.
     */
    @Test
    public void testRemoveList(){
        /**
         * Add the user.
         */
        String testUserName = "karl";
        UserRegistry userRegistry = core.getUserRegistry();
        userRegistry.add(new TaskUser(testUserName, "asdf", "karl@test.se"));
        
        /**
         * Add the lists.
         */
        String listText = "lalala";
        ListFolder listFolder = core.getListFolder();
        
        for(int i = 0; i < 3; ++i)
            listFolder.add(new ShoppingList("test_list_" + i, listText + i, testUserName));
        
        /**
         * Removes the lists.
         */
        List<ShoppingList> userLists = listFolder.getByUsername(testUserName);
        
        for(int i = 0; i < 2; ++i)
            listFolder.remove(userLists.get(i).getId());
        
        /**
         * Checks if the test user has exactly one list, and the text is correct.
         */
        userLists = listFolder.getByUsername(testUserName);
        
        assertTrue(userLists.size() == 1);
        assertTrue(userLists.get(0).getText().equals(listText + 2));
        
        //Clears the user registry and the list folder after the test.
        userRegistry.clear();
        listFolder.clear();
    }
    
    /**
     * Creates 2 users. Adds 2 list to both of these users. Removes all the lists
     * of the first user. Finally checks if all the lists in the list folder belongs
     * to the second user.
     */
    @Test
    public void testRemoveUserLists(){
        /**
         * Add the users.
         */
        String testUserNameOne = "karl";
        String testUserNameTwo = "birger";
        UserRegistry userRegistry = core.getUserRegistry();
        
        userRegistry.add(new TaskUser(testUserNameOne, "asdf", testUserNameOne + "@test.se"));
        userRegistry.add(new TaskUser(testUserNameTwo, "asdf", testUserNameTwo + "@test.se"));
        
        /**
         * Add the lists.
         */
        ListFolder listFolder = core.getListFolder();
        
        for(TaskUser user: userRegistry.getUsers())
            for(int i = 0; i < 2; ++i)
                listFolder.add(new ShoppingList(user.getName() + "_list_" + i, user.getName() + "_text_" + i, user.getName()));
        
        /**
         * Removes the lists of the first user.
         */
        listFolder.removeByUsername(testUserNameOne);
        
        /**
         * Checks if all the lists in the list folder belongs to user two.
         */
        for(ShoppingList list : listFolder.getLists())
            assertTrue(list.getUsername().equals(testUserNameTwo));
        
        //Clears the user registry and the list folder after the test.
        userRegistry.clear();
        listFolder.clear();
    }
    
    /**
     * Attempts to find a list that does not exist.
     */
    @Test
    public void testFindIncorrectList(){
        ListFolder listFolder = core.getListFolder();
        assertFalse(listFolder.find(7) instanceof ShoppingList);
    }
    
}