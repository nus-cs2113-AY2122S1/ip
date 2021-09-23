package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Ui class manages the printing of messages onto the Command Line Interface
 */
public class Ui {
    private static final String LINE = "─────────────────────────────────────────────────────────────\n";

    /**
     * Prints the exit message onto the CLI
     */
    public void printByeMessage() {
        String byeGreeting = "Bye. Hope to see you again soon!\n";
        System.out.println(LINE + byeGreeting + LINE);
    }

    /**
     * Prints the welcome message onto the CLI
     */
    public void printHelloMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String helloGreeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE + helloGreeting + LINE);
    }

    /**
     * Prints the error message whenever an exception is thrown
     * @param e Exception thrown
     */
    public void printErrorMessage(DukeException e) {
        System.out.println(LINE);
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

    /**
     * Prints a message to the CLI whenever a task is added to the task list
     * @param task The task that was added to the task list
     * @param tasks ArrayList to store tasks into the task list at run time
     */
    public void printAddTaskMessage(Task task, ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints a message to the CLI whenever a task is deleted from the task list
     * @param taskIndex Index of task that was deleted
     * @param tasks ArrayList to store tasks into the task list at run time
     */
    public void printDeleteTaskMessage(int taskIndex, ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(taskIndex));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints a message to the CLI when a task is done from the task list
     * @param taskIndex Index of task that was done
     * @param tasks ArrayList to store tasks into the task list at run time
     */
    public void printTaskDoneMessage(int taskIndex, ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
        System.out.println(LINE);
    }

    /**
     * Prints the entire task list
     * @param tasks ArrayList to store tasks into the task list at run time
     */
    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        if (tasks.size() == 0) {
            System.out.println("Task list is empty. Add some tasks in!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i+1) + "." + tasks.get(i));
            }
        }
        System.out.println(LINE);
    }

    /**
     * Prints a message to the Command Line Interface when the given task is found
     * @param matchingTasks List of tasks that match the given keyword
     */
    public void printFindTask(List<Task> matchingTasks) {
        System.out.println(LINE);
        if (matchingTasks.size() == 0) {
            System.out.println("No matching tasks found!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i <matchingTasks.size(); i++) {
                System.out.println((i+1) + "." + matchingTasks.get(i));
            }
        }
        System.out.println(LINE);
    }
}
