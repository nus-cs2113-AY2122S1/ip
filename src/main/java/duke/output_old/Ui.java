package duke.output_old;

import duke.fileio_old.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static final String NUMBER_LIST_SEPARATOR = ". ";
    public static final String TASK_PADDING = "   ";
    public static final String NEWLINE = "\n";
    public static final String HELP_SEPARATOR = " - ";

    private Scanner in;

    public Ui(){
        in = new Scanner(System.in);
    }

    public String getInput(){
        return in.nextLine();
    }

    public void printWelcomeMessage() {
        System.out.println(NEWLINE + "Hello! I'm Duke, your personal task assistant." + NEWLINE +
                "Get started by adding a task, or type help to see what I can do." + NEWLINE);
    }

    public void printTaskNumberOutOfBoundsMessage() {
        System.out.println(NEWLINE + "Oops, I can't find that task in the list!" + NEWLINE);
    }

    public void printInvalidTaskNumberMessage() {
        System.out.println(NEWLINE + "Oops, that is not a valid task number!" + NEWLINE);
    }

    public void printTaskDoneMessage() {
        System.out.println(NEWLINE + "This task has been completed!" + NEWLINE);
    }

    public void printNoTaskNameMessage() {
        System.out.println(NEWLINE + "Please tell me the name of the task." + NEWLINE);
    }

    public void printNoDeadlineMessage() {
        System.out.println(NEWLINE + "Please tell me what the task is and when it is due." + NEWLINE);
    }

    public void printNoEventMessage() {
        System.out.println(NEWLINE + "Please tell me what the event is and when it is happening." + NEWLINE);
    }

    public void printGreetingMessage() {
        System.out.println(NEWLINE + "Hello! What can I do for you?" + NEWLINE);
    }

    public void printExitMessage() {
        System.out.println(NEWLINE + "Bye. Hope to see you again soon!" + NEWLINE);
    }

    public void printInvalidCommandMessage() {
        System.out.println(NEWLINE + "I'm not sure what you want to do.");
        System.out.println("Check if you've misspelled something!");
        System.out.println("Type help for a list of commands." + NEWLINE);
    }

    public void printUnknownErrorMessage() {
        System.out.println(NEWLINE + "Something went wrong!" + NEWLINE);
    }

    public void printNoTasksInListMessage() {
        System.out.println(NEWLINE + "There are no tasks in the list!" + NEWLINE);
    }

    public String getTrimmedSubstring(String input, int startingIndex, int endingIndex) {
        return input.substring(startingIndex, endingIndex).trim();
    }

    public void printMarkedAsDone(ArrayList<Task> tasks, int taskNumber, Storage storage) {
        System.out.println(NEWLINE + "Nice! I've marked the task as done:");
        System.out.println(TASK_PADDING + tasks.get(taskNumber) + NEWLINE);
    }

    public void printAddedTask(ArrayList<Task> tasks, Task task, Storage storage) {
        System.out.println(NEWLINE + "Got it. I've added this task:");
        System.out.println(TASK_PADDING + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + NEWLINE);
    }

    public void printDeletedTask(ArrayList<Task> tasks, Task task, int taskNumber, Storage storage) {
        System.out.println(NEWLINE + "Noted. I deleted this task:");
        System.out.println(TASK_PADDING + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list." + NEWLINE);
    }

    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println(NEWLINE + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + NUMBER_LIST_SEPARATOR + tasks.get(i));
        }
        System.out.print(NEWLINE);
    }

    public void printMatchingTasks(ArrayList<Task> tasks, String keywords) {
        System.out.println(NEWLINE + "Here are the matching tasks in your list:");
        int count = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(keywords)) {
                count++;
                System.out.println(count + NUMBER_LIST_SEPARATOR + task);
            }
        }
        System.out.print(NEWLINE);
    }

    public void printHelpMessage() {
        System.out.println(NEWLINE + "Here are a list of commands:" + NEWLINE);
        printCommandHelpMessage("list", "lists all tasks", "none", "none");
        printCommandHelpMessage("todo", "adds a new to-do task", "todo [task description]",
                "todo organise my desk");
        printCommandHelpMessage("deadline", "adds a new task with a due date",
                "deadline [task description] /by [due date]", "deadline English assignment /by Wed 5pm");
        printCommandHelpMessage("event", "adds a new event",
                "event [task description] /at [event date]", "event meeting /at Thurs 9am");
        printCommandHelpMessage("done", "marks a task as done", "done [task number]",
                "done 2");
        printCommandHelpMessage("delete", "deletes a task from the list",
                "delete [task number]", "delete 3");
        printCommandHelpMessage("help", "lists all commands", "none", "none");
        printCommandHelpMessage("exit, bye, quit", "exits the program", "none", "none");
    }

    public void printCommandHelpMessage(String command, String description, String format, String example) {
        printCommandDescription(command, description);
        printCommandUsage(format);
        printCommandExample(example);
        System.out.println();
    }

    public void printCommandDescription(String command, String description) {
        System.out.println(command + HELP_SEPARATOR + description);
    }

    public void printCommandUsage(String format) {
        if (!format.equals("none")) {
            System.out.println("usage: " + format);
        }
    }

    public void printCommandExample(String example) {
        if (!example.equals("none")) {
            System.out.println("example: " + example);
        }
    }

    public void printFileNotFoundMessage() {
        System.out.println("You don't have any saved tasks!" + NEWLINE);
    }

    public void printFileSaveError(IOException e) {
        System.out.println("Something went wrong while saving tasks: " + e.getMessage());
    }
}
