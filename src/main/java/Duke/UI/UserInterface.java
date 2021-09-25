package Duke.UI;

import Duke.Exception.DukeException;
import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;

import java.io.IOException;
import java.util.ArrayList;

import static Duke.UI.DukeConstants.*;

public class UserInterface {
    public static void printDukeGreet() {
        System.out.println("Hello from\n" + DUKE_LOGO);
        System.out.println(LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    public static void printList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            DukeException.emptyTaskException();
            return;
        }

        System.out.println(LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void taskDoneMessage (int taskDoneIndex, ArrayList<Task> taskList) {
        System.out.println(LINE);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskList.get(taskDoneIndex - 1).toString());
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void eventMessage (Event newItem, ArrayList<Task> taskList) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void todoMessage(Todo newItem, ArrayList<Task> taskList) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void deadlineMessage(Deadline newItem, ArrayList<Task> taskList) {
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newItem);
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void deleteMessage(int taskDeleteIndex, ArrayList<Task> taskList) {
        System.out.println(LINE);
        System.out.println(" Noted! I've removed this task:");
        System.out.println("   " + taskList.get(taskDeleteIndex - 1).toString());
        taskList.remove(taskDeleteIndex - 1);
        System.out.println(" Now you have " + taskList.size() +" tasks in the list.");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void generalDukeException() {
        System.out.println(LINE);
        System.out.println("Please input a valid command!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void printDukeExit() {
        System.out.println(LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE);
    }

    public static void emptyTodoMessage() {
        System.out.println(LINE);
        System.out.println("Your todo command does not have an argument!");
        System.out.println("To input a valid todo command, type \"todo (argument)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void invalidDeadlineMessage() {
        System.out.println(LINE);
        System.out.println("Your deadline command is invalid!");
        System.out.println("To input a valid deadline command, type \"deadline (description) /by (deadline)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void invalidEventMessage() {
        System.out.println(LINE);
        System.out.println("Your event command is invalid!");
        System.out.println("To input a event command, type \"event (description) /at (when)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void invalidDoneMessage() {
        System.out.println(LINE);
        System.out.println("Your done command is invalid!");
        System.out.println("To input a done command, type \"done (task index)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void invalidDeleteMessage() {
        System.out.println(LINE);
        System.out.println("Your delete command is invalid!");
        System.out.println("To input a delete command, type \"delete (task index)\"!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void emptyTaskMessage() {
        System.out.println(LINE);
        System.out.println(" No Tasks here yet. Go include some tasks!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void invalidSaveFileMessage() {
        System.out.println(LINE);
        System.out.println("Your save file is invalid!");
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void createIOExceptionMessage(IOException ioException) {
        System.out.println(LINE);
        System.out.println("Something went wrong: " + ioException.getMessage());
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }

    public static void SaveIOExceptionMessage(IOException ioException) {
        System.out.println(LINE);
        System.out.println("Something went wrong: " + ioException.getMessage());
        System.out.println(LINE);
        System.out.print(System.lineSeparator());
    }
}
