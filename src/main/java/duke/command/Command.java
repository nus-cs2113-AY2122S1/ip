package duke.command;
import duke.DukeException;

/**
 * Represents an interface for commands
 */
public interface Command {
    /**
     * Executes the command
     *
     * @return the result message if success
     * @throws DukeException if the command cannot be executed normally
     */
    String run() throws DukeException;
}
