package duke;

import duke.task.Task;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list of tasks
     *
     * @param task The task to be added
     * @throws DukeException If the maximum number of tasks has been reached
     */
    public void addTask(Task task) {
        tasks.add(task);
        printLine();
        System.out.println("  Ok! I've added this task:");
        System.out.println("    " + task.toString());
        System.out.println("  Now you have " + tasks.size() + " tasks.");
        printLine();
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
     * Prints a horizontal line in the terminal.
     */
    private static void printLine() {
        System.out.println("  ──────────────────────────────");
    }
}
