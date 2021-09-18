package duke.ui;

import java.util.List;
import duke.task.Task;

public class Ui {

    public Ui() {

    }

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

    public void addSuccess(Task task, int size) {
        printWithIndent("Got it. I've added this task: ", 5);
        printWithIndent(task.toString(), 7);
        String taskCount = String.format("Now you have %d task%s in the list.", size, size == 1 ? "" : "s");
        printWithIndent(taskCount, 5);
    }

    public void deleteSuccess(Task task, int size) {
        printWithIndent("Noted. I've removed this task:", 5);
        printWithIndent(task.toString(), 7);
        String taskCount = String.format("Now you have %d task%s in the list.", size, size == 1 ? "" : "s");
        printWithIndent(taskCount, 5);
    }

    public void list(List<Task> tasks) {
        printWithIndent("Here are the tasks in your list:", 5);
        int index = 1;
        for (Task item : tasks) {
            printWithIndent(String.format("%d.%s", index, item), 5);
            index++;
        }
    }

    public void markDone(Task task) {
        printWithIndent("Nice! I've marked this task as done: ", 5);
        printWithIndent(task.toString(), 7);
    }
}
