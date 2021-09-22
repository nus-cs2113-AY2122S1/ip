package duke.tasklist;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public int getListSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTodo(String description) {
        taskList.add(new Todo(description));
    }

    public void addDeadline(String description, String by) {
        taskList.add(new Deadline(description, by));
    }

    public void addEvent(String description, String at) {
        taskList.add(new Event(description, at));
    }

    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }
}
