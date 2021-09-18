package processing;

import tasks.Task;
import java.util.Scanner;

public class UI {
    private static final Scanner myScan = new Scanner(System.in);

    /*----------- CONSOLE LOGGING ----------- */
    private static final String ADD_TASK = "Got it. I've added this task: ";
    private static final String DELETE_TASK = "Noted. I've removed this task: ";

    /*--------- PROCESSING CONSTANTS ------------ */
    public static final String FAREWELL_STR = "Bye. Hope to see you again soon!";
    private static final String DIVIDER = "------------------------------------------";
    private static final String BEGIN_STR = "What can I do for you?";
    private static final String LOGO = """
             ____        _       \s
            |  _ \\ _   _| | _____\s
            | | | | | | | |/ / _ \\
            | |_| | |_| |   <  __/
            |____/ \\__,_|_|\\_\\___|
            """;
    private static final String GREETING = """
            _________________________
            Hello! I'm Duke.Duke
            Standby while I load up your schedule
            Loading...""";

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

    public static void showError(Exception e) {
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

    public static String getCommand() {
        return myScan.nextLine();
    }


    public static void close() {
        System.out.println(FAREWELL_STR);
        myScan.close();
    }
}
