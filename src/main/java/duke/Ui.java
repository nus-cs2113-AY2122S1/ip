package duke;

public class Ui {

    private static final String LINE = "-------------------------------------------------------------------\n";
    private static final String GREET_USER = "Hello! I'm Duke \nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public static void greetUser() {
        System.out.println(GREET_USER + "\n \n" + LINE);
    }

    public static void byeUser() {
        System.out.println(EXIT_MESSAGE + "\n" + LINE);
    }

    public static void printLine() {
        System.out.println(LINE);
    }

    public void showLoadingError() {
        System.out.println("No file found! Creating a new file");
    }
}
