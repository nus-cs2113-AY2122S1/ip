package duke.ui;

import duke.task.Task;
import duke.task.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final int LINE_WIDTH = 60;

    public void printHorizontalLine() {
        System.out.println("_".repeat(LINE_WIDTH));
    }

    public void printFileError() {
        System.out.println("File write error");
    }

    // Print greeting message
    public void printGreeting() {
        printHorizontalLine();
        String greeting = " Hello! I'm Duke" + System.lineSeparator() +
                " What can I do for you?";
        System.out.println(greeting);
        printHorizontalLine();
        System.out.print(System.lineSeparator());
    }

    // Print task added message
    public void printAddTask(Task task, TaskManager taskManager) {
        if (task == null) {
            return;
        }
        String message = " Got it. I've added this task:" + System.lineSeparator() +
                "  " + task.toString() + System.lineSeparator() +
                " Now you have " + taskManager.getTasksCount() +
                " tasks in the list.";
        System.out.println(message);
    }

    public void printInvalidTaskNumberFormat() {
        String message = "  Task ID must be an integer!";
        System.out.println(message);
    }

    public void printInvalidTaskNumber() {
        String message = "  Task ID does not exist!";
        System.out.println(message);
    }

    public void printMarkAsDone(Task task) {
        String message = (" Nice! I've marked this task as done:" +
                System.lineSeparator() + "   " + task.toString());
        System.out.println(message);
    }

    public void printDeleteTask(Task task, TaskManager taskManager) {
        String message = " Got it! I've removed this task:" +
                System.lineSeparator() + "   " + task.toString() +
                System.lineSeparator() + " Now you have " +
                taskManager.getTasksCount() + " tasks in the list.";
        System.out.println(message);
    }

    public void listTasks(TaskManager taskManager) {
        System.out.println(" Here are the tasks in your list:");
        taskManager.listTasks();
    }

    // Print farewell message
    public void printFarewell() {
        String farewell = " Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    public void printDeadlineCommandFormat() {
        String message = ("  Invalid command format!!" + System.lineSeparator() +
                "  Correct format is: " + "deadline <task name> /by <date>");
        System.out.println(message);
    }

    public void printEmptyDescription(String command) {
        String emptyDescription = " ☹ OOPS!!! The description of a " +
                command + " cannot be empty.";
        System.out.println(emptyDescription);
    }

    public void printEventCommandFormat() {
        String message = ("  Invalid command format!!" + System.lineSeparator() +
                "  Correct format is: " + "event <event name> /at <time>");
        System.out.println(message);
    }

    public void printCorrectCommandFormat(String command) {
        switch (command) {
        case COMMAND_DEADLINE:
            printDeadlineCommandFormat();
            break;
        case COMMAND_EVENT:
            printEventCommandFormat();
            break;
        default:
            return;
        }
    }

    public String readCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    public void printInvalidCommand() {
        String invalid = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(invalid);
    }

    public void printSuccessfullySavedTasks(String filePath) {
        printHorizontalLine();
        System.out.println("Current tasks successfully saved at: " +
                System.lineSeparator() + filePath.toString());
        printHorizontalLine();
    }

    private void printNoMatchingResults() {
        String message = "  Sorry, no matching results found!";
        System.out.println(message);
    }

    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.size() == 0) {
            printNoMatchingResults();
            return;
        }
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 1; i <= matchingTasks.size(); i++) {
            System.out.println(" " + i + "." +
                    matchingTasks.get(i - 1).toString());
        }
    }
}
