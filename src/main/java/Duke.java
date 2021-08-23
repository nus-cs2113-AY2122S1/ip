import java.util.Scanner;

public class Duke {
    /**
     * Prints message within horizontal lines
     *
     * @param message The message to print.
     */
    public static void printMessage(String message) {
        System.out.println("------------------------------------------------------------");
        System.out.println(message);
        System.out.println("------------------------------------------------------------");
    }

    public static void main(String[] args) {
        printMessage("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        do {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                run = false;
                printMessage("Bye. Hope to see you again soon!");
            } else {
                printMessage(input);
            }
        } while (run);
    }
}
