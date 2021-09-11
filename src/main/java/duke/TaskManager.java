package duke;

import duke.task.Task;

public class TaskManager {
    private static final int MAXIMUM_TASKS = 100;

    private Task[] tasks = new Task[MAXIMUM_TASKS];
    private int tasksCount = 0;

    /**
     * Adds a task to the list of tasks
     *
     * @param task The task to be added
     * @throws DukeException If the maximum number of tasks has been reached
     */
    public void addTask(Task task) throws DukeException {
        if (tasksCount >= MAXIMUM_TASKS) {
            throw new DukeException("Maximum number of tasks reached.");
        }
        tasks[tasksCount] = task;
        tasksCount++;
        printLine();
        System.out.println("  Ok! I've added this task:");
        System.out.println("    " + task.toString());
        System.out.println("  Now you have " + tasksCount + " tasks.");
        printLine();
    }

    /**
     * Prints a list of all the tasks in the taskManager
     */
    public void printTasks() {
        printLine();
        System.out.println("  Here are your tasks:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.print("    " + (i + 1) + ".");
            System.out.println(tasks[i]);
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
        } else if (taskIndex >= tasksCount) {
            throw new DukeException("Task does not exist.");
        }
        tasks[taskIndex].setCompleted();
        printLine();
        System.out.print("  Ok! I've marked this task as done:" + System.lineSeparator() + "    ");
        System.out.println(tasks[taskIndex]);
        printLine();
    }


    /**
     * Prints a horizontal line in the terminal.
     */
    private static void printLine() {
        System.out.println("  ──────────────────────────────");
    }
}
