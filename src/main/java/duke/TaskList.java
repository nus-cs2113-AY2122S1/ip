package duke;
import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

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

