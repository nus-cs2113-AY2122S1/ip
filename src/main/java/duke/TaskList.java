package duke;
import duke.tasks.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {

    /**
     * Represents the list of Tasks made by the user.
     * Tasks can be added, removed from the TaskList.
     * In addition, the size of the TaskList, as well as specific Tasks, can be accessed.
     */

    private static ArrayList<Task> taskList;

    public TaskList(){
        taskList = new ArrayList<Task>();
    }

    public int size(){
        return taskList.size();
    }

    public void add(Task t){
        taskList.add(t);
    }

    public void remove(int index){
        taskList.remove(index);
    }

    public Task get(int index){
        return taskList.get(index);
    }


}
