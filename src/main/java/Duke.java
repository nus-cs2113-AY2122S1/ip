import java.util.Scanner;

public class Duke {

    private static final Scanner in = new Scanner(System.in);

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
                Tasks.printTasks();
            } else if (userInput.matches(DONE_REGEX)) {
                Tasks.taskDone(Integer.parseInt(userInput.split(" ")[1]) - 1);
            } else {
                Tasks.newTask(userInput);
            }
        }
        Message.end();
    }
}
