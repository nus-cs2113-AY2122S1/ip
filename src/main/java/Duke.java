import java.io.InputStream;
import java.util.Scanner;

public class Duke {

    private static String[] list = new String[100];
    private static int itemCount = 0;

    public static void main(String[] args) {
        greetUser();
        engageUser(args);
        byeUser();
    }

    // That's one dope logo.
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
        System.out.println("      你好! I'm Duke\n      What can I do for you?");
        printLine();
    }
    public static void byeUser() {
        printLine();
        System.out.println("      Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printList() {
        for (int i = 0; i < itemCount; i++) {
            System.out.println("      " + (i + 1) + ". " + list[i]);
        }
    }

    public static void engageUser(String[] args) {
        Scanner text = new Scanner(System.in);
        String userInput;

        do {
            userInput = text.nextLine();
            switch (userInput) {
            case "bye":
                break;
            case "list":
                printLine();
                printList();
                printLine();
                break;
            default:
                list[itemCount] = userInput;
                itemCount++;

                printLine();
                System.out.println("      added: " + userInput);
                printLine();
            }
        } while (!userInput.equals("bye"));
    }
}
