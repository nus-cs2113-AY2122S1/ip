package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddToDoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.tasklist.TaskList;

import java.util.Locale;

public class Parser {
    public static void processUserInput(CommandType commandType, String userInput,
                                        TaskList taskList) throws DukeException {
        switch (commandType) {
        case LIST:
            new ListCommand(taskList);
            break;
        case DONE:
            new DoneCommand(taskList, userInput);
            break;
        case DEADLINE:
            new AddDeadlineCommand(taskList, userInput);
            break;
        case EVENT:
            new AddEventCommand(taskList, userInput);
            break;
        case TODO:
            new AddToDoCommand(taskList, userInput);
            break;
        case DELETE:
            new DeleteCommand(taskList, userInput);
            break;
        case BYE:
            break;
        default:
            throw new DukeException("Invalid input");
        }
    }

    public enum CommandType {
        LIST, BYE, DONE, TODO, DEADLINE, EVENT, DELETE, NULL
    }

    public static CommandType getCommandType(String userInput) {
        String command = userInput.split(" ")[0].toLowerCase(Locale.ROOT);
        switch (command) {
        case "list":
            return CommandType.LIST;
        case "bye":
            return CommandType.BYE;
        case "done":
            return CommandType.DONE;
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        case "delete":
            return CommandType.DELETE;
        default:
            return CommandType.NULL;
        }
    }

}
