package duke;

import java.util.ArrayList;

/**
 * When an instance of this class is created, other classes
 * and methods can access the ArrayList and the counter. This ensures
 * that the entire code updates and draws information from a single
 * ArrayList and a single integer counter.
 */
public class TaskList {

    public ArrayList<Task> list = new ArrayList<>();
    public int counter = 0;

}
