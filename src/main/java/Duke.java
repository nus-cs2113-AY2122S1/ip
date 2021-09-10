import java.util.Scanner;

public class Duke {

    private static final Scanner IN = new Scanner(System.in);

    private static final String TERMINATE_CONSOLE = "bye";
    private static final String LIST = "list";
    private static final String DONE_REGEX = "done \\d+";

    private static String getUserInput() {
        return IN.nextLine();
    }

    public static void main(String[] args) {
        Message.begin();
        while (true) {
            String userInput = getUserInput();
            if (userInput.equals(TERMINATE_CONSOLE)) {
                break;
            } else if (userInput.equals(LIST)) {
                TaskManager.printTasks();
            } else if (userInput.matches(DONE_REGEX)) {
                int id = Integer.parseInt(userInput.split(" ")[1]);
                //id entered with index starting from '1' instead of '0'
                TaskManager.taskDone(id - 1);
            } else {
                TaskManager.newTask(userInput);
            }
        }
        Message.end();
    }
}
