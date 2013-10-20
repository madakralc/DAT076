
import com.awesomedat076.task_manager_backend.Core;
import com.awesomedat076.task_manager_backend.ListFolder;
import com.awesomedat076.task_manager_backend.ShoppingList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test class for the functionality in the Core class.
 * 
 * @author boscar
 */
public class TestCoreFunctionality {
    
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
     * Creates a new user and checks if the added user can log in.
     */
    @Test
    public void testAddNewUserAndLogin(){
        String username = "test_user_001";
        String password = "password";
        
        core.createNewUser(username, password, "test_user_001@mail.com");
        
        assertTrue(core.validateLogin(username, password));
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
    }
    
    /**
     * Attempts to create a new user with incorrect values.
     */
    @Test
    public void testAddIncorrectUser(){
        String username = "test_user_001";
        String password = "";
        
        assertFalse(core.createNewUser(username, password, "test_user_001@mail.com"));
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
    }
    
    /**
     * Adds a new user, and attempt to login with the incorrect username.
     */
    @Test
    public void testIncorrectUsernameLogin(){
        String password = "password";
        
        core.createNewUser("correct_username", password, "test_user_001@mail.com");
        
        assertFalse(core.validateLogin("incorrect_username", password));
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
    }
    
    /**
     * Adds a new user, and attempt to login with the incorrect password.
     */
    @Test
    public void testIncorrectPasswordLogin(){
        String username = "test_user_001";
        
        core.createNewUser(username, "correct", "test_user_001@mail.com");
        
        assertFalse(core.validateLogin(username, "incorrect"));
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
    }
    
    /**
     * Creates a new user, and then attempt to create another user with the same
     * username.
     */
    @Test
    public void testAddDoubleUser(){
        String username = "test_user_001";
        
        core.createNewUser(username, "password", "test_user_001@mail.com");
        
        assertFalse(core.createNewUser(username, "password2", "test_user_001@mail.com"));
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
    }
    
    /**
     * Adds a user, then adds a list with that user as an owner.
     */
    @Test
    public void testAddList(){
        String username = "test_user_001";
        core.createNewUser(username, "password", "test_user_001@mail.com");
        
        assertTrue(core.addList("test_list_001", "test;list;", username));
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
        core.getListFolder().clear();
    }
    
    /**
     * Adds a user, then adds 2 lists with that user as an owner. After that, remove
     * 1 list of that user. Finally check if the list count is 1.
     */
    @Test
    public void testRemoveList(){
        //Add user
        String username = "test_user_001";
        core.createNewUser(username, "password", "test_user_001@mail.com");
        
        //Add lists
        for(int i = 0; i < 2; ++i)
            core.addList("test_list_" + i, "test" + i + ";list" + i + ";", username);
        
        //Remove list.
        ListFolder listFolder = core.getListFolder();
        List<ShoppingList> lists = listFolder.getByUsername(username);
        assertTrue(core.removeList(lists.get(0).getId()));
        
        //Checks if there is exactly 1 list.
        assertTrue(listFolder.getCount() == 1); 
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
        core.getListFolder().clear();
    }
    
    /**
     * Attempts to add two lists with the same name and username, which should not
     * be possible.
     */
    @Test
    public void testAddDoubleList(){
        //Add user
        String username = "test_user_001";
        core.createNewUser(username, "password", "test_user_001@mail.com");
        
        //Add lists 1
        String listName = "test_list";
        core.addList(listName, "the first list", username);
        
        //Add lists 2
        assertFalse(core.addList(listName, "the second list", username));
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
        core.getListFolder().clear();
    }
    
    /**
     * Attempts to add a list with an empty name, which should not
     * be possible.
     */
    @Test
    public void testAddListIncorrectName(){
        //Add user
        String username = "test_user_001";
        core.createNewUser(username, "password", "test_user_001@mail.com");
        
        //Add lists
        assertFalse(core.addList("", "the list", username));
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
        core.getListFolder().clear();
    }
    
    /**
     * Attempts to add a list with an incorrect username, which should not
     * be possible.
     */
    @Test
    public void testAddListIncorrectUsername(){
        //Add lists
        assertFalse(core.addList("test_list", "the list", "incorrect_username"));
        
        //Clears the user registry after the test.
        core.getUserRegistry().clear();
        core.getListFolder().clear();
    }
    
}
