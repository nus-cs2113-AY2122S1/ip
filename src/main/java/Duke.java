import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static final Scanner in = new Scanner(System.in);

    private static String[] tasks = new String[100];
    private static int taskCount = 0;

    private static final String TERMINATE_CONSOLE = "bye";
    private static final String LIST = "list";

    private static String getUserInput() {
        return in.nextLine();
    }

    private static void console() {
        while (true) {
            String userInput = getUserInput();
            if (userInput.equals(TERMINATE_CONSOLE)) {
                return;
            } else if (userInput.equals(LIST)) {
                Message.printTasks(Arrays.copyOf(tasks, taskCount));
            } else {
                tasks[taskCount++] = userInput;
                Message.printInputReceived(userInput);
            }
        }
    }

    public static void main(String[] args) {
        Message.begin();
        console();
        Message.end();
    }
}
