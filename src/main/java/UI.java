import exceptions.DukeException;
import tasks.Task;
import java.util.Scanner;

public class UI {
    private static final Scanner myScan = new Scanner(System.in);

    /*----------- CONSOLE LOGGING ----------- */
    private static final String ADD_TASK = "Got it. I've added this task: ";
    private static final String DONE_TASK = "Nice! I've marked this task as done: ";
    private static final String LIST_TASK = "Here are your scheduled tasks!";
    private static final String DELETE_TASK = "Noted. I've removed this task: ";

    /*--------- PROCESSING CONSTANTS ------------ */
    public static final String FAREWELL_STR = "Bye. Hope to see you again soon!";
    private static final String DIVIDER ="------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "_________________________\n"
            + "Hello! I'm Duke\n"
            + "Standby while I load up your schedule\n"
            + "Loading...";

    private static void showNumTasks(Task t, int taskSize) {
        System.out.println(t);
        System.out.println("You now have (" + taskSize + ") tasks!" );
    }

    /*----------- PUBLIC FUNCTIONS --------------- */
    public static void greet() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(GREETING);
        TaskSafe.loadFromFile(TaskSafe.rootPath + TaskSafe.DATA_PATH,Duke.taskManager);
        System.out.println("------------------------------------");
        System.out.println("What can I do for you?");
    }

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    public static void showError(Exception e) {
        System.out.println(e);
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
