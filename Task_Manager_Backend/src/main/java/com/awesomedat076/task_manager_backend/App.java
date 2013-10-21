package com.awesomedat076.task_manager_backend;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Core core = Core.getInstance();
        core.addTestData();
        for(Item item : core.getList(1).getTextAsItems())
            System.out.println(item.getName());
    }
}
