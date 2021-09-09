import java.util.Scanner;

public class CommandManager {
    private static final Scanner in = new Scanner(System.in);
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static String userInput;

    private static void setUserInput() {
        userInput = in.nextLine().trim();
    }


    public static void executeCommand() {

        TaskManager taskManager = new TaskManager();

        do {
            setUserInput();
            Duke.printDivider();

            if (userInput.equals(COMMAND_LIST)) {
                taskManager.listTasks();

            } else if (userInput.startsWith(COMMAND_TODO)) {
                taskManager.addTaskPlusException(TaskEnum.TODO, userInput);

            } else if (userInput.startsWith(COMMAND_DEADLINE)) {
                taskManager.addTaskPlusException(TaskEnum.DEADLINE, userInput);

            } else if (userInput.startsWith(COMMAND_EVENT)) {
                taskManager.addTaskPlusException(TaskEnum.EVENT, userInput);

            } else if (userInput.startsWith(COMMAND_DONE)) {
                taskManager.doneTaskPlusException(userInput);

            } else if (userInput.equals(COMMAND_BYE)) {
                Duke.printlnTab("Bye. Hope to see you again soon!");
                Duke.printDivider();
                return;

            } else if (userInput.equals("")) { //empty command
                Duke.printlnTab("Please enter a command keyword");
                Duke.printDivider();

            } else { //Invalid inputs
                Duke.printlnTab("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                Duke.printDivider();

            }
            //TODO help function also

        } while (true);

    }


}
