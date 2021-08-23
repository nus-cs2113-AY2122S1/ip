import java.util.Scanner;
public class Duke {

    private static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        greetUser();
        echo();
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printString(String response) {
        System.out.println(line);
        System.out.println(" " + response);
        System.out.println(line);
    }

    // greet the user and prints a line
    public static void greetUser() {
        printString("Hello! I'm Duke\n" +
                " What can I do for you?");
    }

    // exits the programme after printing the greeting and a line
    public static void exitChatbot() {
        printString("Bye. Hope to see you again soon!");
    }

    // echoes user commands until the user types bye
    public static void echo() {
        String command;
        Scanner in = new Scanner(System.in);
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                exitChatbot();
                break;
            }
            printString(command);
        }
    }
}
