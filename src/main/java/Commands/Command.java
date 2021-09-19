package Commands;

import Duke.TaskList;

import java.util.ArrayList;

/**
 * Represents a command
 */
public class Command {

    public Command() {
    }

    /**
     * Executes the command and returns the result.
     * @param taskList
     */
    public String execute(TaskList taskList) {
        return "Command Executed";
    };
}
