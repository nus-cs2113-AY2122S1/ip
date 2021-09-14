package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskManager {
    public static final String LINE = "─────────────────────────────────────────────────────────────\n";
    ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds task to task list represented by tasks array.
     * @param task Task to add
     */
    public void addTasks(Task task, boolean toPrint) {
        tasks.add(task);
        if (toPrint) {
            System.out.println(LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
            System.out.println(LINE);
        }
    }

    /**
     * Prints every task in the task list
     */
    public void printTasks() {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Mark a task given by its index as done
     * @param taskIndex Index of task in task list to mark as done
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public void markTaskAsDone(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please select a task in the task list!");
        }
        tasks.get(taskIndex).markAsDone();
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
        System.out.println(LINE);
    }

    public void deleteTask(int taskIndex) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new DukeException("☹ OOPS!!! Please select a task in the task list!");
        }
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(taskIndex));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        System.out.println(LINE);
        tasks.remove(taskIndex);
    }
}
