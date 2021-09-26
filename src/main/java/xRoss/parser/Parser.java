package xRoss.parser;

import xRoss.TaskManager;
import xRoss.ui.Ui;

public class Parser {

    private static Ui ui = new Ui();

    public boolean parseCommand(TaskManager taskManager, String inputLine){
        boolean commandLoop = true;

        if (inputLine.equals("bye")) {
            commandLoop = false;
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

        return commandLoop;
    }

}
