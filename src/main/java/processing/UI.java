package processing;

import org.jetbrains.annotations.NotNull;
import tasks.Task;
import java.util.Scanner;

public class UI {
    private static final Scanner myScan = new Scanner(System.in);

    /*----------- CONSOLE LOGGING ----------- */
    private static final String ADD_TASK = "Got it. I've added this task: ";
    private static final String DELETE_TASK = "Noted. I've removed this task: ";
    private static final String LIST_COMMANDS = "These are the valid commands: ";

    private static final String DTFORMAT_HEADER = "Here are the valid DateTime formats to use: ";

    public static final String FAREWELL_STR = "Bye. Hope to see you again soon!";
    private static final String DIVIDER = "------------------------------------------";
    private static final String BEGIN_STR = "What can I do for you?";
    private static final String LOGO = " ____        _        \n" +
                                       "|  _ \\ _   _| | _____ \n" +
                                       "| | | | | | | |/ / _ \\\n" +
                                       "| |_| | |_| |   <  __/\n" +
                                       "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "_________________________\n" +
                                           "Hello! I'm Duke.Duke\n" +
                                           "Standby while I load up your schedule\n" +
                                           "Loading...";

    private static void showNumTasks(Task t, int taskSize) {
        System.out.println(t);
        System.out.println("You now have (" + taskSize + ") tasks!" );
    }

    /*----------- PUBLIC FUNCTIONS --------------- */
    public static void greetPartOne() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETING);

    }

    public static void greetPartTwo() {
        printDivider();
        System.out.println(BEGIN_STR);
    }

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void showError(@NotNull Exception e) {
        e.printStackTrace();
    }

    public static void showAddTask (Task t, int taskSize) {
        System.out.println(ADD_TASK);
        showNumTasks(t, taskSize);
    }

    public static void showDeleteTask(Task t, int taskSize) {
        System.out.println(DELETE_TASK);
        showNumTasks(t, taskSize);
    }

    public static @NotNull String getCommand() {
        String input = myScan.nextLine();
        if (input.isBlank()) {
            return getCommand();
        }
        return input;
    }

    public static void close() {
        System.out.println(FAREWELL_STR);
        myScan.close();
    }

    public static void listDTFormats() {
        System.out.println(DTFORMAT_HEADER);
        for (String format : DateParser.DATETIME_FORMATS) {
            System.out.println("-> " + format);
        }
        for (String format : DateParser.TIME_FORMATS) {
            System.out.println("-> today " + format);
        }
    }

    public static void listCommands() {
        System.out.println(LIST_COMMANDS);
        for (String command : CommandHandler.commands) {
            System.out.println(" > " + command);
        }
    }
}
