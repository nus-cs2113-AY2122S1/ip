import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static final Scanner in = new Scanner(System.in);

    private static Task[] tasks = new Task[100];

    private static final String TERMINATE_CONSOLE = "bye";
    private static final String LIST = "list";
    private static final String DONE_REGEX = "done \\d+";

    private static String getUserInput() {
        return in.nextLine();
    }

    public static void main(String[] args) {
        Message.begin();

        while (true) {
            String userInput = getUserInput();
            if (userInput.equals(TERMINATE_CONSOLE)) {
                break;
            } else if (userInput.equals(LIST)) {
                Message.printTasks(Arrays.copyOf(tasks, Task.getCount()));
            } else if (userInput.matches(DONE_REGEX)) {
                Task currentTask = tasks[Integer.parseInt(userInput.split(" ")[1]) - 1];
                currentTask.markAsDone();
                //Mark the task as done
                Message.printTaskDone(currentTask);
            } else {
                tasks[Task.incrementCount()] = new Task(userInput);
                Message.printInputReceived(userInput);
            }
        }

        Message.end();
    }
}
