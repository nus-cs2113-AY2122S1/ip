import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static final String DIVIDER = "-----------------------------------------";

    public static void main(String[] args) {
        printWelcomeMessage();
        TaskManager taskManager = new TaskManager();

        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        do {
            String userInput = in.nextLine();
            String[] words = userInput.split(" ");

            switch (Parser.processCommand(words[0].toLowerCase(Locale.ROOT))) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                isExit = true;
                break;
            case LIST:
                taskManager.printTask();
                break;
            case DONE:
                taskManager.doneTask(userInput);
                break;
            case DEADLINE:
                taskManager.addDeadlineTask(userInput);
                break;
            case EVENT:
                taskManager.addEventTask(userInput);
                break;
            case TODO:
                taskManager.addTodoTask(userInput);
                break;
            default:
                System.out.println("Sorry I don't understand the command: " + userInput);
            }
            System.out.println(DIVIDER);
        } while (!isExit);
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(DIVIDER);
    }
}
