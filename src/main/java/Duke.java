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
            String line = in.nextLine();
            String[] words = line.split(" ");

            switch (Parser.processCommand(words[0].toLowerCase(Locale.ROOT))) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                isExit = true;
                break;
            case LIST:
                taskManager.printTask();
                break;
            case DONE:
                if (words.length < 2) {
                    System.out.println("Please specify index to mark as done!");
                } else if (Integer.parseInt(words[1]) > taskManager.taskCount) {
                    System.out.println("Please specify indices between 1 and " + taskManager.taskCount);
                } else {
                    taskManager.doneTask(Integer.parseInt(words[1]));
                }
                break;
            default:
                taskManager.addTask(line);
            }
            System.out.println(DIVIDER);
        } while (!isExit);
    }

    private static void printWelcomeMessage() {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(DIVIDER);
    }
}
