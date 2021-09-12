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

        do {
            setUserInput();
            Duke.printDivider();

            //TODO short form "l"
            if (userInput.equals(COMMAND_LIST)) {
                Duke.taskManager.listTasks();

            } else if (userInput.startsWith(COMMAND_TODO)) {
                Duke.taskManager.addTaskPlusException(TaskEnum.TODO, userInput);

            } else if (userInput.startsWith(COMMAND_DEADLINE)) {
                Duke.taskManager.addTaskPlusException(TaskEnum.DEADLINE, userInput);

            } else if (userInput.startsWith(COMMAND_EVENT)) {
                Duke.taskManager.addTaskPlusException(TaskEnum.EVENT, userInput);

            } else if (userInput.startsWith(COMMAND_DONE)) {
                Duke.taskManager.doneTaskPlusException(userInput);

            } else if (userInput.equals(COMMAND_BYE)) {
                Duke.printlnTab("Bye. Hope to see you again soon!");
                Duke.printDivider();
                return;

            } else if (userInput.equals("")) { //empty command
                Duke.printlnTab("Please enter a command keyword.");
                Duke.printDivider();

            } else if (userInput.equals("help") || userInput.equals("h")) { //empty command
                Duke.printlnTab("List of commands:");
                Duke.printlnTab("1. list");
                Duke.printlnTab("2. todo [TASK DESCRIPTION]");
                Duke.printlnTab("3. deadline [TASK DESCRIPTION] /by [DEADLINE]");
                Duke.printlnTab("4. event [TASK DESCRIPTION] /at [DATE/TIME]");
                Duke.printlnTab("5. done [TASK NUMBER]");
                Duke.printlnTab("6. help");
                Duke.printlnTab("7. bye");
                Duke.printDivider();

            } else { //Invalid inputs
                Duke.printlnTab("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                Duke.printDivider();
            }

        } while (true);

    }


}
