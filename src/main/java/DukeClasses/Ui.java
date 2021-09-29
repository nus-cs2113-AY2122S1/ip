package DukeClasses;

import java.util.Scanner;

/**
 * UI of the application.
 */
public class Ui {

    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String fileNotFound = "____________________________________________________________\n"
            + "No preloaded file found! Please input your own data.\n"
            + "____________________________________________________________\n";

    /** Quick summary of instructions to using this application*/
    public static String instructions = "____________________________________________________________\n"
            + "Hello! Welcome to Duke. I am your personal task tracker.\n"
            + "As of now, I can help you track Todos, Deadlines and Events. "
            + "Mark your tasks with either \"todo\", \"deadline\" or \"event\" at the start. \n"
            + "For deadlines and events, after your task, please enter either \"by (your deadline)\" "
            + "or \"at (your event timing)\".\n"
            + "To see all your tasks, enter \"list\". \n"
            + "To mark a task as done, enter \"done (task number)\". \n"
            + "To delete a task, enter \"delete (task number)\". \n"
            + "To exit this program, enter \"bye\". \n"
            + "And that's all! Hope you find me helpful! :) \n"
            + "____________________________________________________________\n";

    public static String helpMessage = "Enter \"help\" if you need help using me! \n";

    public static String greeting = "____________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + logo
            + "What can I do for you?\n"
            + helpMessage
            + "____________________________________________________________\n";

    public static String goodbye = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";

    /** */
    public static String emptyTaskError = "____________________________________________________________\n"
            + "Please do not leave the description of the task empty!\n"
            + helpMessage
            + "____________________________________________________________\n";

    public static String invalidTaskError = "____________________________________________________________\n"
            + "I don't know what that means! It's either an invalid command or you have formatted the task wrongly.\n"
            + helpMessage
            + "____________________________________________________________\n";



    public void greetUser() {
        System.out.println(greeting);
    }
    public void sayGoodbye() {
        System.out.println(goodbye);
    }

    /**
     * Reads in the user input and returns it
     * @return the string that the user inputs
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public void showInstructions() {
        System.out.println(instructions);
    }
    public void printFileNotFoundError() {
        System.out.println(fileNotFound);
    }

}
