package main.java.duke.ui;

import main.java.duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/** Includes the messages which is communicated to user after the user's inputs. */
public class Ui {

    public static final String HORIZONTAL_LINE_TOP = "\n______________________________" +
            "______________________________";
    public static final String HORIZONTAL_LINE_BOTTOM = "______________________________" +
            "______________________________\n";

    public String readCommand(Scanner in) {
        return in.nextLine();
    }

    public static void printLineTop() {
        System.out.println(HORIZONTAL_LINE_TOP);
    }

    public static void printLineBottom() {
        System.out.println(HORIZONTAL_LINE_BOTTOM);
    }

    /** Prints the welcome message when user enters Duke. */
    public static void sendWelcomeMessage() {
        String logo = " DDDDD           kk\n"
                + " DD  DD  uu   uu kk  kk   eee\n"
                + " DD   DD uu   uu kkkkk  ee   e\n"
                + " DD   DD uu   uu kk kk  eeeee\n"
                + " DDDDDD   uuuu u kk  kk  eeeee\n";
        System.out.print(" Hello from\n\n" + logo);
        System.out.println(HORIZONTAL_LINE_TOP
                + "\n Hello! I'm Duke, your friendly neighbourhood task manager!\n"
                + " How can I help you?\n" + HORIZONTAL_LINE_BOTTOM);
    }

    /** Prints the exit message when the user inputs {@code bye} and exits duke. */
    public void sendExitMessage() {
        System.out.println(HORIZONTAL_LINE_TOP + "\n Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE_BOTTOM);
    }

    /** Prints an error message when Duke catches any IO exceptions. */
    public static void sendIOExceptionMessage() {
        System.out.println(HORIZONTAL_LINE_TOP
                + "\n ☹ OOPS!!! Something went wrong!." + "\n"
                + HORIZONTAL_LINE_BOTTOM);
    }

    /**
     * Prints error message when the description of the task in the user input is missing
     *
     * @param command The user's command word in the string.
     */
    public void sendIndexOutOfBoundsMessage(String command) {
        System.out.println(HORIZONTAL_LINE_TOP
                + "\n ☹ OOPS!!! The description of a " + command + " cannot be empty.\n"
                + HORIZONTAL_LINE_BOTTOM);
    }

    /** Prints error message when the text file saving the tasks is not found in specified file path. */
    public void sendFileNotFoundMessage() {
        System.out.print(" File not found\n" + HORIZONTAL_LINE_BOTTOM);
    }

    /**
     * Prints the number of tasks found in the task list.
     *
     * @param tasks Array list of the tasks updated onto Duke when launched.
     */
    public void printTasksQuantity(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println(" No tasks found in the task file\n" + HORIZONTAL_LINE_BOTTOM);
        } else {
            System.out.println(" You have " + tasks.size()
                    +  " tasks.\n" + HORIZONTAL_LINE_BOTTOM);
        }
    }

    /** Prints the message of acknowledgement when the task is marked as done. */
    public static void printMarkedAsDone() {
        System.out.println(HORIZONTAL_LINE_TOP + "\n I've already marked the task as done!\n"
                + HORIZONTAL_LINE_BOTTOM);
    }

    /** Prints the message to user when there are no tasks in the text file or duke task list. */
    public static void sendNoTasksMessage() {
        System.out.println(" You have no tasks!");
    }

    /** Prints the header part of the acknowledgement message when user inputs {@code list}. */
    public static void printTaskHeader() {
        System.out.println(" Here are the tasks in your list:");
    }

    /** Prints the header part of the acknowledgement message when user inputs {@code find}. */
    public static void printMatchesHeader() {
        System.out.println(" Here are the matching tasks in your list:");
    }

    /**
     * Prints the message when there are no tasks found which matches the
     * query of the user when the user inputs {@code find}.
     */
    public static void sendNoMatchesMessage() {
        System.out.println("\n ☹ There are no matching tasks found!");
    }

}
