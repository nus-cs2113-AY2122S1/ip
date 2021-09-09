import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public abstract class MessagePrinter {
    // constants storing printing messages
    private static final String FRIDAY = "  __      _     _             \n"
            + " / _|    (_)   | |            \n"
            + "| |_ _ __ _  __| | __ _ _   _ \n"
            + "|  _| '__| |/ _` |/ _` | | | |\n"
            + "| | | |  | | (_| | (_| | |_| |\n"
            + "|_| |_|  |_|\\__,_|\\__,_|\\__, |\n"
            + "                         __/ |\n"
            + "                        |___/ \n";
    private static final String DASHES = "____________________________________________________________\n";

    private static void friday() {
        System.out.println(FRIDAY);
    }

    private static void dashes() {
        System.out.println(DASHES);
    }

    // methods to print messages
    public static void greetUser() {
        dashes();
        friday();
        System.out.println("Initiating FRIDAY");
        System.out.println("Hello Mr Stark, how may I be of assistance to you today");
        dashes();
    }

    public static void exitMessage() {
        dashes();
        System.out.println("Powering Off now. Good Bye Mr Stark.\n");
        dashes();
    }

    // SUCCESSFUL messages

    // ERROR messages
}
