package duke.tasklist;
import duke.tasklist.task.Task;
import duke.tasklist.task.Deadlines;
import duke.tasklist.task.Todo;
import duke.tasklist.task.Events;
import duke.ui.Ui;
import java.util.ArrayList;

public class TaskList {

    protected int taskCount;

    public TaskList(ArrayList<Task> items) {
        loadTaskCount(items);
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void loadTaskCount(ArrayList<Task> items) {
        this.taskCount = items.size();
    }

    /**
     * Create a new Event object and add it to the tasks list
     *
     * @param items list of items stored in an ArrayList
     * @param description task description input by user
     * @param at the deadline for the event input by the user
     */
    public void addEvent(ArrayList<Task> items, String description, String at) {
        Events newEvent = new Events(description, at);
        items.add(taskCount, newEvent);
    }

    /**
     * Create a new Deadline object and add it to the tasks list
     *
     * @param items list of items stored in an ArrayList
     * @param description task description input by user
     * @param by the date for the deadline input by the user
     */
    public void addDeadline(ArrayList<Task> items, String description, String by) {
        Deadlines newDeadline = new Deadlines(description, by);
        items.add(taskCount, newDeadline);
    }

    /**
     * Create a new Todo object and add it to the tasks list
     *
     * @param items list of items stored in an ArrayList
     * @param description task description input by user
     *
     */
    public void addTodo(ArrayList<Task> items, String description) {
        Todo newTodo = new Todo(description);
        items.add(taskCount, newTodo);
    }

    public void removeItem(Ui ui, ArrayList<Task> items, int taskNum) {
        taskCount--;
        ui.removeTaskMessage(items.get(taskNum), taskCount);
        items.remove(taskNum);
    }

}


