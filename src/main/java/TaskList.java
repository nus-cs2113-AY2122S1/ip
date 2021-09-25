package ip.src.main.java;
import java.util.ArrayList;

/**
 * Represents a TaskList class. A <code>TaskList</code> Class adapts from the Task Class in Arrays.
 * The TaskList class contains methods which modifies code from the Task Class.
 */
public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<String> letter = new ArrayList<>();
    public static ArrayList<String> done = new ArrayList<>();

    private static int i=0;

    /**
     * Adds a Task into the TaskList by assigning it values into the 3 arrays.
     *
     * @param t Task t.
     * @param type The class of the Task, whether it is a Deadline, Event or Task.
     */
    public static void addTask(Task t, int type) {
        tasks.add(t);
        if (type == 1) letter.add("D");
        else if (type == 2) letter.add("E");
        else if (type == 3) letter.add("T");
        done.add(" ");
        i++;
    }

    /**
     * Marks a Task in the TaskList as Done.
     *
     * @param i Corresponding index of the Task.
     */
    public static void markDone(int i) {
        done.set(i-1, "X");
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param i Corresponding index of the Task.
     */
    public static void remove(int i) {
        done.remove(i-1);
        letter.remove(i-1);
        tasks.remove(i-1);
    }

    /**
     * Prints all the Tasks in the TaskList.
     */
    public static void printTask() {
        for (int j=1; j<=tasks.size(); j++)
            System.out.println(j + ". " + "[" + letter.get(j-1) + "] "
                    + "[" + done.get(j-1) + "] " + tasks.get(j-1).description());
    }

    /**
     * Gets the description of a Task from the TaskList.
     *
     * @param i Corresponding index of the Task.
     * @return Description of the task.
     */
    public static String getDescription(int i) {
        return tasks.get(i-1).description();
    }

    /**
     * Gets the description of a Task from the TaskList.
     *
     * @param i Corresponding index of the Task.
     * @return Description of the task including its status and index.
     */
    public static String getTask(int i) {
        return i + " | " + letter.get(i - 1) + " | " + done.get(i - 1) +  " | " + getDescription(i);
    }

    /**
     * Finds all associated Tasks in the TaskList.
     *
     *  @param data Corresponding substring of the Task to be searched.
     */
    public static void find(String data) {
        for (int j=1; j<=tasks.size(); j++) {
            if (tasks.get(j-1).description().contains(data)) {
                System.out.println(j + ". " + "[" + letter.get(j-1) + "] "
                        + "[" + done.get(j-1) + "] " + tasks.get(j-1).description());
            }
        }
    }
}
