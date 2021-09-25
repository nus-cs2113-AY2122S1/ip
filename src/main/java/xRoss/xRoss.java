package xRoss;

import xRoss.exception.EmptyStringException;
import xRoss.task.Deadline;
import xRoss.task.Event;
import xRoss.task.Todo;
import xRoss.ui.Ui;

import java.util.Scanner;

/**
 * Represents implementation of xRoss chat bot.
 */
public class xRoss {

    private static Ui ui;

    /**
     * Main function to execute xRoss chat bot.
     *
     * @param args
     */
    public static void main(String[] args) {
        ui = new Ui();

        ui.printWelcomeMessage();

        // TaskManager instance to keep track of all tasks
        TaskManager taskManager = new TaskManager();
        taskManager.readFromFile();


        // setting up variable and scanner for user input
        String inputLine;
        Scanner in = new Scanner(System.in);

        // boolean value on whether
        boolean continueLoop = true;

        while (continueLoop) {
            inputLine = in.nextLine();

            if (inputLine.equals("bye")) {
                continueLoop = false;
            } else if (inputLine.equals("list")) {
                ui.printTaskListResponse(taskManager);
            } else if (inputLine.startsWith("done")) {
                ui.printDoneResponse(taskManager, inputLine);
            } else if (inputLine.startsWith("delete")){
                ui.printDeleteResponse(taskManager, inputLine);
            }else if (inputLine.startsWith("todo")) {
                ui.printTodoResponse(taskManager, inputLine);
            } else if (inputLine.startsWith("deadline")) {
                ui.printDeadlineResponse(taskManager, inputLine);
            } else if (inputLine.startsWith("event")) {
                ui.printEventResponse(taskManager, inputLine);
            } else {
                ui.printEcho(inputLine);
            }
        }
        ui.printExitMessage();
    }
}
