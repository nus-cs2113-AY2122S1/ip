package Parser;

import commands.*;
import exceptions.*;
import tasks.*;

import java.awt.geom.AffineTransform;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


/**
 * A class to interpret user commands.
 */
public class Parser {

    /**
     * Interprets user input, and gets what type of command it is.
     *
     * @param command The user input
     * @param userTasks The TaskList object holds all tasks of this user.
     *
     * @return The command type
     * @throws DukeException Throws exception if the command is not exactly following the format.
     */
    public UserCommand parseCommand(String command, TaskList userTasks) throws DukeException{

        /*
        exceptions already handled:
        (1) Done / Delete command:
            1. not provide the task index or task index not a number (NumberFormatException)


       (2) Deadline / Event command
            1. the time does not follow required format (DateTimeParseException)
            2. task name is missing
            3. time is missing (ArrayIndexOutOfBoundsException)

        (3) command not exists (InvalidCommandException);
         */

        int firstSpace = command.indexOf(" ");

        if (firstSpace == -1) {
            if (command.equals("bye")) {
                return new QuitCommand();
            }

            if (command.equals("list")) {
                return new ListCommand(userTasks);
            }

            if (command.equals("sort")) {
                return new SortCommand(userTasks);
            }

            if (command.equals("help")) {
                return new HelpCommand();
            }
            throw new InvalidCommandException();
        }

        String commandType = command.substring(0, firstSpace);
        String restCommand = command.substring(firstSpace).strip();
        if (commandType.equals("done")) {
            return parseDoneCommand(restCommand, userTasks);
        }

        if (commandType.equals("delete")) {
            return parseDeleteCommand(restCommand, userTasks);
        }

        if (commandType.equals("todo")) {
            return parseTodoCommand(restCommand, userTasks);
        }

        if (commandType.equals("find")) {
            return parseFindCommand(restCommand, userTasks);
        }

        if (commandType.equals("deadline")) {
            return parseDeadlineCommand(restCommand, userTasks);
        }

        if (commandType.equals("event")) {
            return parseEventCommand(restCommand, userTasks);
        }

        throw new InvalidCommandException();
    }

    private DeleteCommand parseDeleteCommand(String taskIndex, TaskList userTask) throws TaskIndexException {
        try {
            return new DeleteCommand(Integer.parseInt(taskIndex), userTask);
        } catch (NumberFormatException e) {
            throw new TaskIndexException();
        }
    }

    private DoneCommand parseDoneCommand(String taskIndex, TaskList userTask) throws TaskIndexException{
        try {
            return new DoneCommand(Integer.parseInt(taskIndex), userTask);
        } catch (NumberFormatException e) {
            throw new TaskIndexException();
        }
    }

    private AddTaskCommand parseTodoCommand(String taskName, TaskList userTask) {
        return new AddTaskCommand(new Todo(taskName, false), userTask);
    }

    private AddTaskCommand parseDeadlineCommand(String restCommand, TaskList userTask) throws DukeException {
        try {
            String[] split = restCommand.split("/");
            String[] time = split[1].strip().split(" ");

            if (time.length == 1) {
                return new AddTaskCommand(new Deadline(split[0].strip(),
                        LocalDate.parse(split[1].strip()), false), userTask);
            } else {
                return new AddTaskCommand(new Deadline(split[0].strip(),
                        LocalDate.parse(time[0].strip()), LocalTime.parse(time[1].strip()), false), userTask);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TimeMissingException();
        } catch (DateTimeParseException e) {
            throw new TimeFormatException();
        }
    }

    private AddTaskCommand parseEventCommand(String restCommand, TaskList userTask) throws DukeException {
        try {
            String[] split = restCommand.split("/");
            return new AddTaskCommand(new Event(split[0].strip(),
                    LocalDateTime.parse(split[1].strip()), false), userTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TimeMissingException();
        } catch (DateTimeParseException e) {
            throw new TimeFormatException();
        }
    }

    private FindTaskCommand parseFindCommand(String restCommand, TaskList userTask) {
        return new FindTaskCommand(userTask, restCommand.strip());
    }
}
