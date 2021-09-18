package duke.ui;

import java.util.List;
import duke.task.Task;

/**
 * Represents a user interface. A Ui object allows printing of duke user interface
 */
public class Ui {

    public Ui() {

    }

    /**
     * Prints a string with specified indentation
     *
     * @param text String to print
     * @param count number of indentions by spaces
     */
    private static void printWithIndent(String text, int count) {
        System.out.println(" ".repeat(count) + text);
    }

    public void greet() {
        printDivider();
        printWithIndent("Hello! I'm Duke", 5);
        printWithIndent("What can I do for you?", 5);
        printDivider();
        System.out.println();
    }

    public void bye() {
        printWithIndent("Bye. Hope to see you again soon!", 5);
    }

    public void showLoadingError() {
        printWithIndent("There is no save file :-(", 5);
    }

    public void printUnknownCommand() {
        printWithIndent("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", 5);
    }

    public void printErrorMessage(String msg) {
        printWithIndent(msg, 5);
    }

    public void printDivider() {
        printWithIndent("_".repeat(60), 4);
    }

    /**
     * Prints a success message for addition of task
     *
     * @param task Task that was successfully added
     * @param size Size of the task list after addition of task
     */
    public void addSuccess(Task task, int size) {
        printWithIndent("Got it. I've added this task: ", 5);
        printWithIndent(task.toString(), 7);
        String taskCount = String.format("Now you have %d task%s in the list.", size, size == 1 ? "" : "s");
        printWithIndent(taskCount, 5);
    }

    /**
     * Prints a success message for deletion of task
     *
     * @param task Task that was successfully deleted
     * @param size Size of the task list after deletion of task
     */
    public void deleteSuccess(Task task, int size) {
        printWithIndent("Noted. I've removed this task:", 5);
        printWithIndent(task.toString(), 7);
        String taskCount = String.format("Now you have %d task%s in the list.", size, size == 1 ? "" : "s");
        printWithIndent(taskCount, 5);
    }

    /**
     * Prints a success message for completion of task
     *
     * @param tasks Task that was completed succssfully
     */
    public void markDone(Task task) {
        printWithIndent("Nice! I've marked this task as done: ", 5);
        printWithIndent(task.toString(), 7);
    }

    /**
     * Lists all tasks in tasks list
     *
     * @param tasks list of tasks
     */
    public void listAll(List<Task> tasks) {
        printWithIndent("Here are the tasks in your list:", 5);
        listTasks(tasks);
    }

    /**
     * Lists tasks that contains a specified search term
     *
     * @param tasks list of tasks that contains a specified search term
     */
    public void listSearch(List<Task> tasks) {
        printWithIndent("Here are the matching tasks in your list:", 5);
        listTasks(tasks);
    }

    /**
     * Lists tasks in numbered order
     *
     * @param tasks list of tasks
     */
    public void listTasks(List<Task> tasks) {
        int index = 1;
        for (Task item : tasks) {
            printWithIndent(String.format("%d.%s", index, item), 5);
            index++;
        }
    }

}
