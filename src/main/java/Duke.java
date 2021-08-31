import java.util.Scanner;

public class Duke {
    private static String getUserResponse(Scanner in) {
        String line;
        line = in.nextLine();
        return line;
    }

    private static void processInput(String input, List list) {
        if (input.equals("list")) {
            list.printList();
        } else if (input.equals("help")) {
            printHelpMessage();
        } else if (input.contains("done")) {
            list.doneEntry(list.parseInputForEntryNumber(input));
        } else {
            list.addEntryToList(input);
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        List list = new List();
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = getUserResponse(in);
            if (userInput.equals("bye")) {
                printExitMessage();
                break;
            }
            processInput(userInput, list);
            printResponseSeparator();
        }
    }

    private static void printExitMessage() {
        System.out.println("Bye! See you soon!");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("I am currently able to help you keep track of tasks");
        System.out.println("scheduled to receive a personality soon, exciting!");
        System.out.println("Type \"help\" to find out what I can do for you.");
        printResponseSeparator();
    }

    private static void printHelpMessage() {
        System.out.println("I currently support 3 types of tasks: todo, deadline and event");
        System.out.println("To add a todo task: todo (task name)");
        System.out.println("To add a deadline task: deadline (task name) /by (date or time)");
        System.out.println("To add an event task: event (task name) /at (date or time)");
        System.out.println("note that only the words in parentheses() can be replaced");
        System.out.println("Use the command \"list\" and I will show you your current list");
        System.out.println("To cross out an entry, use the command \"done x\" where x is the entry number");
        System.out.println("When you're done, type \"bye\" to end the program");
    }

    private static void printResponseSeparator() {
        System.out.println("===============================================================================");
    }
}
