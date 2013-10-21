package com.awesomedat076.task_manager_backend;



import com.awesomedat076.task_manager_backend.TaskUser;
import javax.xml.bind.annotation.*;

/**
 * The XML/JSON proxy of a TaskUser.
 * 
 * @author boscar
 */
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TaskUserProxy {

    // The wrapped product
    private TaskUser taskUser;

    /**
     * Needed...
     */
    protected TaskUserProxy() { 
    }
   
    public TaskUserProxy(TaskUser taskUser) { 
        this.taskUser = taskUser; 
    }
    
    /**
     * Returns the name of the user.
     * 
     * @return 
     */
    @XmlElement(required = true)
    public String getName() {
        return taskUser.getName();
    }
    
    /**
     * Returns the password of the user.
     * 
     * @return 
     */
    @XmlElement(required = true)
    public String getPassword() {
        return taskUser.getPassword();
    }

    /**
     * Returns the email of the user.
     * 
     * @return 
     */
    @XmlElement(required = true)
    public String getEmail() {
        return taskUser.getEmail();
    }
    
}
