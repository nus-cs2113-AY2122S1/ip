package duke;

import duke.exceptionHandler.DukeException;

import java.io.IOException;

/**
 * Check for the type of entered command by the user.
 *
 * @author YEOWEIHNGWHYELAB
 */

public class CommandHandler {
    /**
     * if, else if chunk to check user command.
     *
     * @param commandHandle Parser object which can be checked for what type of command is it.
     * @param userInputString Raw user input String.
     * @param dukeTaskText Storage type object responsible for all duke.txt operation.
     * @return Boolean true if the command is not "bye" and false otherwise.
     * @throws DukeException When invalid command is entered.
     * @throws IOException When duke.txt is deleted while program is running.
     */
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
