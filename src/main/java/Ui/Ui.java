package Ui;

public class Ui {

    public static final String LINE = "_______________________________________\n";

    public static void horizontalLine() {
        System.out.println(LINE);
    }
    public static void printGreeting() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n" + LINE);
        Ui.horizontalLine();
    }

    public static void showLoadingError() {
        System.out.println("Oops! Something went wrong!\n");
        Ui.horizontalLine();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
        Ui.horizontalLine();
    }
}
