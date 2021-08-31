import java.util.Scanner;

public class CommandManager {
    private static final Scanner in = new Scanner(System.in);
    private static String userInput;

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";


    private static void setUserInput() {
        userInput = in.nextLine().trim();
    }


    public static void executeCommand() {

        TaskManager taskManager = new TaskManager();
        setUserInput();


        while (!userInput.equals(COMMAND_BYE)) {
            Duke.printDivider();

            if (userInput.equals(COMMAND_LIST)) {
                taskManager.listTasks();
                Duke.printDivider();

            } else if (userInput.startsWith(COMMAND_TODO)) {
                taskManager.addTask(TaskEnum.TODO, userInput);

            } else if (userInput.startsWith(COMMAND_DEADLINE)) {
                taskManager.addTask(TaskEnum.DEADLINE, userInput);

            } else if (userInput.startsWith(COMMAND_EVENT)) {
                taskManager.addTask(TaskEnum.EVENT, userInput);

            } else if (userInput.startsWith(COMMAND_DONE)) {
                taskManager.doneTask(userInput);

            } else {
                Duke.printlnTab("Invalid Input. Please try again!");
                Duke.printDivider();

            }
            //help function also

            setUserInput();
        }

        Duke.printlnTab("Bye. Hope to see you again soon!");
        Duke.printDivider();
    }


}
