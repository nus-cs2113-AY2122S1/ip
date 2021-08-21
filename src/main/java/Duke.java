import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greetUser();
        executeResponse();
    }

    // Template code to check if editor is working
    public static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    // Prints a separator line
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    // Greeting message
    public static void greetUser() {
        printLine();
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        printLine();
    }

    // Exit message
    public static void exitDuke() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    // Echos user message
    public static void echoMessage(String message) {
        printLine();
        System.out.println("     " + message);
        printLine();
    }

    // Executes an appropriate response based on the input message
    public static void executeResponse() {
        String line;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println();
            line = in.nextLine();
            if (line.equals("bye")) {
                exitDuke();
            } else {
                echoMessage(line);
            }
        } while (!line.equals("bye"));
    }

}
