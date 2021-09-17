import exceptions.DukeException;

import java.util.Scanner;

public class UI {
    private static final Scanner myScan = new Scanner(System.in);

    /*--------- PROCESSING CONSTANTS ------------ */
    public static final String FAREWELL_STR = "Bye. Hope to see you again soon!";

    /*----------- PUBLIC FUNCTIONS --------------- */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("Standby while I load up your schedule");
        System.out.println("Loading...");
        TaskSafe.loadFromFile(TaskSafe.rootPath + TaskSafe.DATA_PATH,Duke.taskManager);
        System.out.println("------------------------------------");
        System.out.println("What can I do for you?");
    }

    public static void printDivider() {
        System.out.println("------------------------------------------");
    }

    public static void showError(Exception e) {
        System.out.println(e);
    }

    public static String getCommand() {
        return myScan.nextLine();
    }


    public static void close() {
        System.out.println(FAREWELL_STR);
        myScan.close();
    }
}
