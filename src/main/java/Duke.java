import java.util.Scanner;

public class Duke {

    private static String getUserResponse() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;
    }

    private static void echoInput() {
        System.out.println(getUserResponse());
    }

    private static void processInput(String input, List list) {
        if (input.equals("bye")) {
            System.out.println("Bye! See you soon!");
        } else if (input.equals("list")) {
            list.printList();
        } else if (input.contains("done")) {
            list.doneEntry(list.parseInputForEntryNumber(input));
        } else {
            list.addEntry(input);
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        List list = new List();
        while (true) {
            String userInput = getUserResponse();
            processInput(userInput, list);
        }
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
        System.out.println("I am scheduled to receive a personality soon, exciting!");
        System.out.println("Here's what I can do you for you: ");
        System.out.println("Simply state a task and it will be added to the list");
        System.out.println("Use the command \"list\" and I will show you your current list");
        System.out.println("To cross out an entry, use the command \"done x\" where x is the entry number");
        System.out.println("When you're done, type \"bye\" to end the program");
    }
}
