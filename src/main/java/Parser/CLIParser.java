package Parser;

import commands.AddTaskCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.FindTaskCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.QuitCommand;
import commands.SortCommand;
import commands.UserCommand;
import exceptions.DukeException;
import exceptions.InvalidCommandException;
import exceptions.TaskIndexException;
import exceptions.TimeException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


/**
 * A class to interpret user commands.
 */
public class CLIParser {

    /**
     * Interprets user input, and gets what type of command it is.
     *
     * @param command The user input
     * @param userTasks The TaskList object holds all tasks of this user.
     *
     * @return The command type
     * @throws DukeException Throws exception if the command is not exactly following the format.
     */
    public UserCommand parseCommand(String command, TaskList userTasks) throws DukeException {

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
            String taskName = split[0].strip();

            if (time.length == 1) {
                LocalDate date = LocalDate.parse(split[1].strip());

                if (! date.isAfter(LocalDate.now())) {
                    throw new TimeException();
                }
                return new AddTaskCommand(new Deadline(taskName, date, false), userTask);

            } else {
                LocalDate date = LocalDate.parse(time[0].strip());
                LocalTime minute = LocalTime.parse(time[1].strip());

                if (!LocalDateTime.of(date, minute).isAfter(LocalDateTime.now())) {
                    throw new TimeException();
                }

                return new AddTaskCommand(new Deadline(taskName, date, minute, false), userTask);
            }

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new TimeException();
        }
    }

    private AddTaskCommand parseEventCommand(String restCommand, TaskList userTask) throws DukeException {
        try {
            String[] split = restCommand.split("/");
            String[] time = split[1].strip().split(" ");

            return new AddTaskCommand(new Event(split[0].strip(),
                    LocalDate.parse(time[0].strip()), LocalTime.parse(time[1].strip()), false), userTask);

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new TimeException();
        }
    }

    private FindTaskCommand parseFindCommand(String restCommand, TaskList userTask) {
        return new FindTaskCommand(userTask, restCommand.strip());
    }
}
