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
                Duke.printDivider();

            } else if (userInput.startsWith(COMMAND_TODO)) {
                taskManager.addTask(TaskEnum.TODO, userInput);

            } else if (userInput.startsWith(COMMAND_DEADLINE)) {
                taskManager.addTask(TaskEnum.DEADLINE, userInput);

            } else if (userInput.startsWith(COMMAND_EVENT)) {
                taskManager.addTask(TaskEnum.EVENT, userInput);

            } else if (userInput.startsWith(COMMAND_DONE)) {
                try {
                    taskManager.doneTask(userInput);
                } catch (NumberFormatException e) {
                    Duke.printlnTab("Task number is not an integer. Please try again!");
                    Duke.printDivider();

                } catch (IndexOutOfBoundsException e) {
                    Duke.printlnTab("Task number is out of bounds. Please try again!");
                    Duke.printDivider();

                } catch (NullPointerException e) {
                    Duke.printlnTab("You only have " + taskManager.getTotalTasks() + " tasks");
                    Duke.printlnTab("Please enter a number smaller or equal to " + taskManager.getTotalTasks());
                    Duke.printDivider();

                }        

            } else if (userInput.equals(COMMAND_BYE)) {
                Duke.printlnTab("Bye. Hope to see you again soon!");
                Duke.printDivider();
                return;

            } else { //Invalid inputs
                Duke.printlnTab("Invalid Input. Please try again!");
                Duke.printDivider();

            }
            //TODO help function also

        } while (true);

    }


}
