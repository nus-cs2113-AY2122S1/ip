package duke.command;
import duke.DukeException;

/**
 * An interface for commands
 */
public interface Command {
    /**
     * Execute the command
     *
     * @return the result message if success
     * @throws DukeException if the command cannot be executed normally
     */
    String run() throws DukeException;
}
