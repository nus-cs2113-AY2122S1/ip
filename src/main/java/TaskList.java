import java.util.ArrayList;
/**
 * The TaskList program handles the adding and removing of Task objects to/from the Task array.
 */
public class TaskList {

    /**
     * Adds todos into the existing Task Array at a certain index given by the integer count.
     * @param Task The Task array into which todos will be added
     * @param count The index of Task array at which the new todos will be added
     * @param userInput The description of the todos to be added
     */
    public void addTodo(ArrayList<Task> Task, int count, String userInput) {
        Task.add(count, new Todo(userInput));
    }

    /**
     * Adds deadlines into the existing Task Array at a certain index given by the integer count.
     * @param Task The Task array into which deadlines will be added
     * @param count The index of Task array at which the new deadlines will be added
     * @param userInput The description of the deadlines to be added
     * @param date The date by when the deadline is due by.
     */
    public void addDeadline(ArrayList<Task> Task, int count, String userInput, String date) {
        Task.add(count, new Deadline(userInput, date, false));
    }

    /**
     * Adds events into the existing Task Array at a certain index given by the integer count.
     * @param Task The Task array into which events will be added
     * @param count The index of Task array at which the new events will be added
     * @param userInput The description of the events to be added
     * @param date The date by when the event is due by.
     */
    public void addEvent(ArrayList<Task> Task, int count, String userInput, String date) {
        Task.add(count, new Event(userInput, date));
    }

    /**
     * Removes tasks from the existing Task Array at a certain index given by the integer count.
     * @param Task The Task array from which tasks will be removed
     * @param count The index of Task array at which the task will be removed
     */
    public void removeTask(ArrayList<Task> Task, int count) {
        Task.remove(count);
    }
}
