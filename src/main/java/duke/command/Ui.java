package duke.command;

public class Ui {

    static void greet() {
        printDividerLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void instructions() {
        System.out.println("The following can be done: ");
        printDividerLine();
        System.out.println("1.  ToDos: tasks without any date/time attached to it");
        System.out.println("    COMMAND: todo xxx");
        System.out.println("2.  Deadlines: tasks that need to be done before a specific date/time");
        System.out.println("    COMMAND: deadline xxx /by yyy");
        System.out.println("3.  Events: tasks that start at a specific time and ends at a specific time");
        System.out.println("    COMMAND: event xxx /at yyy");
        System.out.println("4.  Display list");
        System.out.println("    COMMAND: list");
        System.out.println("5.  Mark task complete");
        System.out.println("    COMMAND: done x");
        System.out.println("6.  Exit");
        System.out.println("    COMMAND: bye");
        printDividerLine();
    }

    static void bye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividerLine();
    }

    static void printDividerLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

}