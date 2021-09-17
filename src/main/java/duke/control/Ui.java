package duke.control;

import duke.control.command.*;

import java.util.Scanner;

public class Ui {
    static final String RESPONSE_SEPARATOR = "=============================" +
            "===========================================";

    static void printExitMessage() {
        System.out.println("Bye! See you soon!");
    }

    static void printInvalidInputMessage() {
        System.out.println("I don't know what you mean, please look at the instructions and try again");
    }

    public static void printMissingDateTimeMessage() {
        System.out.println("Command not entered properly, remember to use \"/by\" or " +
                "\"/at\" modifiers for deadline and event tasks respectively. Type \"help\"" +
                " for more information");
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("I am currently able to help you keep track of tasks");
        System.out.println("scheduled to receive a personality soon, exciting!");
        System.out.println("Type \"help\" to find out what I can do for you.");
        printResponseSeparator();
    }

    public static void printHelpMessage() {
        System.out.println("I currently support 3 types of tasks: todo, deadline and event");
        System.out.println("To add a todo task: todo (task name)");
        System.out.println("To add a deadline task: deadline (task name) /by (date or time)");
        System.out.println("To add an event task: event (task name) /at (date or time)");
        System.out.println("To delete a task in the list: delete (x) where x is the entry number");
        System.out.println("note that only the words in parentheses() can be replaced");
        System.out.println("To see your current list, Use the command \"list\"");
        System.out.println("To cross out an entry, use the command \"done x\" where x is the entry number");
        System.out.println("To save your current data, use the command \"save\"");
        System.out.println("To clear all your saved data, use the command \"clearsave\"");
        System.out.println("When you're done, type \"bye\" to end the program (automatically saves your current list)");
    }

    public static void printResponseSeparator() {
        System.out.println(RESPONSE_SEPARATOR);
    }

    public static String getUserResponse(Scanner in) {
        String line;
        line = in.nextLine();
        return line;
    }

    static Command processInput(String input) throws InvalidInputFormatException {
        Command command;
        if (input.equals("list")) {
            command = new ListCommand();
        } else if (input.equals("help")) {
            command = new HelpCommand();
        } else if (input.equals("bye")) {
            command = new ByeCommand();
        } else if (input.startsWith("done")) {
            command = new DoneCommand();
        } else if (input.startsWith("delete")) {
            command = new DeleteCommand();
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            command = new TaskCommand();
        } else {
            throw new InvalidInputFormatException();
        }
        return command;
    }
}
