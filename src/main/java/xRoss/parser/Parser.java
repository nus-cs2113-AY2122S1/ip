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

        if (inputLine.equals("bye")) {
            commandLoop = false;
        } else if (inputLine.equals("list")) {
            ui.printTaskListResponse(taskManager);
        } else if (inputLine.startsWith("done")) {
            ui.printDoneResponse(taskManager, inputLine);
        } else if (inputLine.startsWith("find")) {
            ui.printFindResponse(taskManager, inputLine);
        } else if (inputLine.startsWith("delete")) {
            ui.printDeleteResponse(taskManager, inputLine);
        } else if (inputLine.startsWith("todo")) {
            ui.printTodoResponse(taskManager, inputLine);
        } else if (inputLine.startsWith("deadline")) {
            ui.printDeadlineResponse(taskManager, inputLine);
        } else if (inputLine.startsWith("event")) {
            ui.printEventResponse(taskManager, inputLine);
        } else {
            ui.printEcho(inputLine);
        }

        return commandLoop;
    }

}
