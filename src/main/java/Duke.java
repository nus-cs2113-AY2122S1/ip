import java.util.Scanner;

public class Duke {

    private static String[] listItems = new String[100];
    private static int itemCount = 0;

    public static void main(String[] args) {
        greetUser();
        executeResponse();
    }

    // Template code to check if IDE is working
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

    // Add items to list
    public static void addToList(String item) {
        listItems[itemCount] = item;
        itemCount++;
        printLine();
        System.out.println("     added: " + item);
        printLine();
    }

    // Print out items in list
    public static void printList() {
        printLine();
        for (int i = 0; i < itemCount; i++) {
            System.out.println("     " + i + ". " + listItems[i]);
        }
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
            } else if (line.equals("list")) {
                printList();
            } else {
                addToList(line);
            }
        } while (!line.equals("bye"));
    }

}
