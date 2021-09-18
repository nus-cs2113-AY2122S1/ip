package duke;

import duke.commands.Command;
import duke.commands.AddCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.FindCommand;
import duke.exceptions.DukeException;
import duke.exceptions.CommandNotFoundException;
import duke.exceptions.InvalidDeadlineFormatException;
import duke.exceptions.InvalidDeleteFormatException;
import duke.exceptions.InvalidDoneFormatException;
import duke.exceptions.InvalidEventFormatException;
import duke.exceptions.InvalidToDoFormatException;
import duke.exceptions.InvalidFindFormatException;


public class Parser{
    private static final int LENGTH_OF_BY = 5;
    private static final int LENGTH_OF_AT = 5;

    /**
     * Returns a <code>Command</code> with the appropriate information.
     * Purpose is to process raw input from the user.
     * @param rawInput The raw input as input by the user.
     * @return The command which is called by the user that can be executed.
     * @throws DukeException If the parser is unable to parse the raw input.
     */
    public static Command parse(String rawInput) throws DukeException {
        Command command;
        String details;
        int timeIdx;

        String[] userInput = rawInput.split(" ", 2);
        String userCommand = userInput[0].toLowerCase();

        switch(userCommand) {
        case "bye":
            command = new ExitCommand();
            break;
        case "done":
            try {
                command = new DoneCommand(Integer.parseInt(userInput[1]) - 1);
            } catch (NumberFormatException e) {
                throw new InvalidDoneFormatException();
            }
            break;
        case "list":
            command = new ListCommand();
            break;
        case "todo":
            try {
                details = userInput[1];
                command = new AddCommand(userCommand, details);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidToDoFormatException();
            }
            break;
        case "event":
            try {
                timeIdx = userInput[1].indexOf(" /at ");
                details = userInput[1].substring(0, timeIdx);
                String at = userInput[1].substring(timeIdx+LENGTH_OF_AT);
                command = new AddCommand(userCommand, details, at);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidEventFormatException();
            }
            break;
        case "deadline":
            try {
                timeIdx = userInput[1].indexOf(" /by ");
                details = userInput[1].substring(0, timeIdx);
                String by = userInput[1].substring(timeIdx+LENGTH_OF_BY);
                command = new AddCommand(userCommand, details, by);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidDeadlineFormatException();
            }
            break;
        case "delete":
            try {
                command = new DeleteCommand(Integer.parseInt(userInput[1]) - 1);
            } catch (NumberFormatException e) {
                throw new InvalidDeleteFormatException();
            }
            break;
        case "find":
            try {
                command = new FindCommand(userInput[1].toLowerCase());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidFindFormatException();
            }
            break;
        default:
            throw new CommandNotFoundException(userCommand);
        }
        return command;
    }
}
