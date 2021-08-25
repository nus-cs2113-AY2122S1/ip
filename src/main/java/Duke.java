import java.io.InputStream;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greetUser();
        echoUser(args);
        byeUser();
    }

    // That's one dope logo
    public static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
    public static void greetUser() {
        printLine();
        System.out.println("      Hello! I'm Duke\n      What can I do for you?");
        printLine();
    }
    public static void byeUser() {
        printLine();
        System.out.println("      Bye. Hope to see you again soon!");
        printLine();
    }

    public static void echoUser(String[] args) {
        Scanner text = new Scanner(System.in);
        String userInput;

        do {
            userInput = text.nextLine();
            switch (userInput) {
            case "bye":
                break;
            default:
                printLine();
                System.out.println(userInput);
                printLine();
            }
        } while (!userInput.equals("bye"));
    }
}
