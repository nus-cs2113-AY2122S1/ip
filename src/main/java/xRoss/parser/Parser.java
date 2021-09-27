package xRoss.parser;

import xRoss.TaskManager;
import xRoss.ui.Ui;


/**
 * Represents making sense of user input.
 */
public class Parser {

    /**
     * ui   Ui class instance used to invoke the relevant actions by chat bot.
     */
    private static Ui ui = new Ui();

    /**
     * Makes sense of user input and invokes the relevant actions by the chat bot.
     *
     * @param taskManager Representation of current task list.
     * @param inputLine   Scanned user input.
     * @return Boolean value that keeps tracks of whether to exit the chat bot.
     */
    public boolean parseCommand(TaskManager taskManager, String inputLine) {
        boolean commandLoop = true;

        String[] userCommand = inputLine.split(" ", 2);

        switch (userCommand[0]) {
        case "bye":
            commandLoop = false;
            break;
        case "list":
            ui.printTaskListResponse(taskManager);
            break;
        case "done":
            ui.printDoneResponse(taskManager, inputLine);
            break;
        case "find":
            ui.printFindResponse(taskManager, inputLine);
            break;
        case "delete":
            ui.printDeleteResponse(taskManager, inputLine);
            break;
        case "todo":
            ui.printTodoResponse(taskManager, inputLine);
            break;
        case "deadline":
            ui.printDeadlineResponse(taskManager, inputLine);
            break;
        case "event":
            ui.printEventResponse(taskManager, inputLine);
            break;
        default:
            ui.printEcho(inputLine);
        }

        return commandLoop;
    }

}
