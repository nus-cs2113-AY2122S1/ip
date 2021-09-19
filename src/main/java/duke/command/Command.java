package duke.command;

import duke.DukeException;

public interface Command {
    void run() throws DukeException;

    CommandType getType();
}
