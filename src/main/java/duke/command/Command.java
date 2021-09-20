package duke.command;

import duke.DukeException;

public interface Command {
    void run(boolean printMessage) throws DukeException;

    CommandType getType();
}
