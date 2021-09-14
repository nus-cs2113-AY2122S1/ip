package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list of tasks
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        addTask(task, true);
    }

    /**
     * Adds a task to the list of tasks, prints completion message if specified
     *
     * @param task      the task to be added
     * @param doesPrint Boolean determining whether completion message will be printed
     */
    public void addTask(Task task, boolean doesPrint) {
        tasks.add(task);
        if (doesPrint) {
            printLine();
            System.out.println("  Ok! I've added this task:");
            System.out.println("    " + task);
            System.out.println("  Now you have " + tasks.size() + " tasks.");
            printLine();
        }
    }

    /**
     * Deletes the task at the specified index
     *
     * @param taskIndex the index of the task to be deleted
     */
    public void deleteTask(int taskIndex) throws DukeException {
        if (taskIndex <= -1) {
            throw new DukeException("Task index must be greater than 0.");
        } else if (taskIndex >= tasks.size()) {
            throw new DukeException("Task does not exist.");
        }
        Task removedTask = tasks.remove(taskIndex);
        printLine();
        System.out.print("  Ok! I've deleted this task:" + System.lineSeparator() + "    ");
        System.out.println(removedTask);
        System.out.println("  Now you have " + tasks.size() + " tasks.");
        printLine();
    }

    /**
     * Deletes all tasks
     */
    public void deleteAllTasks() {
        tasks.clear();
    }

    /**
     * Prints a list of all the tasks in the taskManager
     */
    public void printTasks() {
        printLine();
        System.out.println("  Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print("    " + (i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        printLine();
    }

    /**
     * Marks the task at the specified index as completed
     *
     * @param taskIndex the index of the task to be marked as completed
     */
    public void completeTask(int taskIndex) throws DukeException {
        if (taskIndex <= -1) {
            throw new DukeException("Task index must be greater than 0.");
        } else if (taskIndex >= tasks.size()) {
            throw new DukeException("Task does not exist.");
        }
        tasks.get(taskIndex).setCompleted();
        printLine();
        System.out.print("  Ok! I've marked this task as done:" + System.lineSeparator() + "    ");
        System.out.println(tasks.get(taskIndex));
        printLine();
    }

    /**
     * Creates a string containing the task information for all Tasks in TaskList
     *
     * @return string containing the task information for all Tasks in TaskList, separated by lineSeparators
     */
    public String getStorageString() {
        String storageString = "";
        for (Task task : tasks) {
            storageString = storageString + task.getStorageString() + System.lineSeparator();
        }
        return storageString;
    }

    /**
     * Prints a horizontal line in the terminal.
     */
    private static void printLine() {
        System.out.println("  ──────────────────────────────");
    }
}
