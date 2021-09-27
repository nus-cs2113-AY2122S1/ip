package ip.src.main.java;
import java.util.ArrayList;

/**
 * Represents a TaskList class. A <code>TaskList</code> Class adapts from the Task Class in Arrays.
 * The TaskList class contains methods which modifies code from the Task Class.
 */
public class TaskList {
    public static ArrayList<Task> taskArray = new ArrayList<>();
    public static ArrayList<String> letter = new ArrayList<>();
    public static ArrayList<String> doneArray = new ArrayList<>();

    private static int i = 0;

    /**
     * Adds a Task into the TaskList by assigning it values into the 3 arrays.
     *
     * @param t Task t.
     * @param type The class of the Task, whether it is a Deadline, Event or Task.
     */
    public static void addTask(Task t, char type) {
        taskArray.add(t);

        switch(type) {
            case 'D':
                letter.add("D");
                break;
            case 'E':
                letter.add("E");
                break;
            case 'T':
                letter.add("T");
                break;
        }
        doneArray.add(" ");
        i++;
    }

    /**
     * Marks a Task in the TaskList as Done.
     *
     * @param i Corresponding index of the Task.
     */
    public static void markDone(int i) {
        doneArray.set(i-1, "X");
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param i Corresponding index of the Task.
     */
    public static void remove(int i) {
        doneArray.remove(i-1);
        letter.remove(i-1);
        taskArray.remove(i-1);
    }

    /**
     * Prints all the Tasks in the TaskList.
     */
    public static void printTask() {
        for (int j = 1; j<= taskArray.size(); j++)
            System.out.println(j + ". " + "[" + letter.get(j-1) + "] "
                    + "[" + doneArray.get(j-1) + "] " + taskArray.get(j-1).description());
    }

    /**
     * Gets the description of a Task from the TaskList.
     *
     * @param i Corresponding index of the Task.
     * @return Description of the task.
     */
    public static String getDescription(int i) {
        return taskArray.get(i-1).description();
    }

    /**
     * Gets the description of a Task from the TaskList.
     *
     * @param i Corresponding index of the Task.
     * @return Description of the task including its status and index.
     */
    public static String getTask(int i) {
        return i + " | " + letter.get(i - 1) + " | " + doneArray.get(i - 1) +  " | " + getDescription(i);
    }

    /**
     * Finds all associated Tasks in the TaskList.
     *
     *  @param data Corresponding substring of the Task to be searched.
     */
    public static void find(String data) {
        for (int j = 1; j<= taskArray.size(); j++) {
            if (taskArray.get(j-1).description().contains(data)) {
                System.out.println(j + ". " + "[" + letter.get(j-1) + "] "
                        + "[" + doneArray.get(j-1) + "] " + taskArray.get(j-1).description());
            }
        }
    }
}
