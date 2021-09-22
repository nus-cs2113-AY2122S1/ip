import java.util.Scanner;

public class Parser {
    public static final TaskList taskList = new TaskList();
    public static final String COMMAND_DONE = "done";
    private static final Scanner in = new Scanner(System.in);
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_LIST_SHORT = "l";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_HELP_SHORT = "h";
    private static String userInput;

    private static void setUserInput() {
        userInput = in.nextLine().trim();
    }


    public static void executeCommand() {
        boolean isExit = false;
        do {
            setUserInput();
            Ui.printDivider();

            if (userInput.equals(COMMAND_LIST_SHORT) || userInput.equals(COMMAND_LIST)) {
                taskList.listTasks();

            } else if (userInput.startsWith(COMMAND_TODO)) {
                taskList.addTaskPlusException(TaskEnum.TODO, userInput);

            } else if (userInput.startsWith(COMMAND_DEADLINE)) {
                taskList.addTaskPlusException(TaskEnum.DEADLINE, userInput);

            } else if (userInput.startsWith(COMMAND_EVENT)) {
                taskList.addTaskPlusException(TaskEnum.EVENT, userInput);

            } else if (userInput.startsWith(COMMAND_DONE)) {
                taskList.doneOrDeleteTaskPlusException(userInput, COMMAND_DONE);

            } else if (userInput.startsWith(COMMAND_DELETE)) {
                taskList.doneOrDeleteTaskPlusException(userInput, COMMAND_DELETE);

            } else if (userInput.equals(COMMAND_BYE)) {
                Ui.printlnTab("Bye. Hope to see you again soon!");
                Ui.printDivider();
                isExit = true;

            } else if (userInput.equals("")) { //empty command
                Ui.printlnTab("Please enter a command.");
                Ui.printDivider();

            } else if (userInput.equals(COMMAND_HELP_SHORT) || userInput.equals(COMMAND_HELP)) { //empty command
                Ui.printlnTab("List of commands:");
                Ui.printlnTab("1. l OR list ");
                Ui.printlnTab("2. todo [TASK DESCRIPTION]");
                Ui.printlnTab("3. deadline [TASK DESCRIPTION] /by [DEADLINE]");
                Ui.printlnTab("4. event [TASK DESCRIPTION] /at [DATE/TIME]");
                Ui.printlnTab("5. delete [TASK NUMBER]");
                Ui.printlnTab("6. done [TASK NUMBER]");
                Ui.printlnTab("7. h OR help");
                Ui.printlnTab("8. bye");
                Ui.printDivider();

            } else { //Invalid inputs
                Ui.printlnTab("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                Ui.printDivider();
            }

        } while (!isExit);

    }


}
