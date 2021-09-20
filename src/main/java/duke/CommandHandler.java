package duke;

import duke.exceptionHandler.DukeException;

import java.io.IOException;

public class CommandHandler {

    public boolean commandHandle(Parser commandHandle, String userInputString, Storage dukeTaskText) throws DukeException, IOException {
        if (commandHandle.isBye()) {
            Ui.showGoodBye();
            return true;
        } else if (commandHandle.isList()) {
            TaskList.printTaskList();
            return false;
        } else if (commandHandle.isDone()) {
            TaskList.finishTask(userInputString, dukeTaskText);
            return false;
        } else if (commandHandle.isDelete()) {
            TaskList.deleteTask(userInputString, dukeTaskText);
            return false;
        } else if (commandHandle.isToDo()) {
            TaskList.addToDo(userInputString, dukeTaskText);
            return false;
        } else if (commandHandle.isDeadline()) {
            TaskList.addDeadline(userInputString, dukeTaskText);
            return false;
        } else if (commandHandle.isEvent()) {
            TaskList.addEvent(userInputString, dukeTaskText);
            return false;
        } else if (commandHandle.isFind()) {
            TaskList.findTask(userInputString);
            return false;
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

}
