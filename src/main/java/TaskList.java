import java.util.ArrayList;

/**
 * This class deals with operations on the list that stores all the tasks
 */
public class TaskList {

    protected static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a new Todo task into tasks list
     *
     * @param description Todo task's description
     */
    public static void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    /**
     * Adds a new Deadline task into tasks list
     *
     * @param description Deadline task's description
     * @param date Deadline task's due date
     */
    public static void addDeadline(String description, String date) {
        tasks.add(new Deadline(description, date));
    }

    /**
     * Adds a new Event task into tasks list
     *
     * @param description Event task's description
     * @param date Event task's due date
     */
    public static void addEvent(String description, String date) {
        tasks.add(new Event(description, date));
    }

    /**
     * Removes a task from the tasks list
     *
     * @param index index of the task to be removed in tasks list
     */
    public static void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns a Task object of the specified index
     *
     * @param index index of the task to be returned
     * @return Task of specified index in tasks list
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static void printTaskList() {
        System.out.println(Ui.INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Ui.INDENT + (i + 1) + "." + getTask(i));
        }
    }

    public static void printAddedTask() {
        System.out.println(Ui.INDENT + "Got it. I've added this task:" + Ui.LINE_SEPARATOR_AND_INDENT +
                getTask(tasks.size() - 1) + Ui.LINE_SEPARATOR_AND_INDENT + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void printDeletedTask(int index) {
        System.out.println(Ui.INDENT + "Got it. I've removed this task:" + Ui.LINE_SEPARATOR_AND_INDENT +
                getTask(index) + Ui.LINE_SEPARATOR_AND_INDENT + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
    }
}
