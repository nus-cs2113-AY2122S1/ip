package duke;

import commands.TaskList;
import ui.Ui;

public class Duke {
    /**
     * Task type Array list to store the tasks the user will create
     */

    public static final TaskList TASK_LIST = new TaskList();
    private static boolean isWorking = true;

    /**
     * This is the main function responsible for the execution of this program
     */
    public static void main(String[] args) {
        Storage.loadData(Duke.TASK_LIST);
        Ui.greet();
        runDuke();
        Ui.greetBye();
    }


    /**
     * Takes in input from the user and executes the given instructions.
     * If user input is "list", it calls the list() function to list all the tasks.
     * If user input is done x, where x is a valid task number, it calls the MarkTaskAsDone() function to mark the task as done.
     * If user has scheduled a task by writing a statement beginning with "event", "deadline" or "todo",
     * then it adds the task in the tasks list by calling the addTaskToList() function.
     *
     * @param userInput            userInput stores the input String entered by the user.
     * @param taskCompletionStatus taskCompletionStatus Stores the status of the task, true if completed, false otherwise.
     */
    private static void runDuke() {
        while (isWorking) {
            String command = Ui.getCommand();
            isWorking = Parser.processCommand(command);
            Ui.printLine();
        }
    }
}
