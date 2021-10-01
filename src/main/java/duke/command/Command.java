package duke.command;

import duke.DukeException;

public interface Command {

    /**
     * Interface that provides common functionality among all user input commands
     *
     * @param printMessage Print message to user on executing command
     * @throws DukeException If exception is thrown by any of Command implementers
     */
    void run(boolean printMessage) throws DukeException;

    /**
     * @return Type of Command
     */
    CommandType getType();
}
