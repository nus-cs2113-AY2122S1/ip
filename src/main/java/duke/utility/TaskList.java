package duke.utility;
import duke.functions.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> items = new ArrayList<>();

    public void remove(int i){
        items.remove(i);
    }

    public Task get(int i){
        return items.get(i);
    }

    public void add(Task t){
        items.add(t);
    }

}
