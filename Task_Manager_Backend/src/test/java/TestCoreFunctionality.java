
import com.awesomedat076.task_manager_backend.Core;
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
    
    
    
}
