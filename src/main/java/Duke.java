import java.util.ArrayList;
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
        ArrayList<String> list = new ArrayList<>();
        do {
            System.out.print("$ ");
            String input = scanner.nextLine();
            switch (input) {
            case "list":
                String message;
                if (list.isEmpty()) {
                    message = "List is empty";
                } else {
                    message = "";
                    for (int i = 0; i < list.size(); i += 1) {
                        if (i > 0) {
                            message += "\n";
                        }
                        message += String.format("%d: %s",i + 1, list.get(i));
                    }
                }
                printMessage(message);
                break;
            case "bye":
                run = false;
                break;
            default:
                list.add(input);
                printMessage(String.format("Added: %s",input));
                break;
            }
        } while (run);

        printMessage("Bye. Hope to see you again soon!");
    }
}
