package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_MARK_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_NEW_TODO = "todo";
    private static final String COMMAND_NEW_DEADLINE = "deadline";
    private static final String COMMAND_NEW_EVENT = "event";
    private static final String COMMAND_FIND = "find";

    /**
     * Takes in the userInput String and returns the corresponding command to be executed
     *
     * @param userInput The String that contains the user's input
     * @return The corresponding Command based on the user's input
     * @throws DukeException If the userInput String is not in the correct format or
     *                       does not correspond to a valid command
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput.equals(COMMAND_EXIT)) {
            return new ExitCommand();
        }
        if (userInput.equals(COMMAND_LIST)) {
            return new ListCommand();
        }
        if (userInput.equals(COMMAND_HELP)) {
            return new HelpCommand();
        }
        if (userInput.startsWith(COMMAND_MARK_DONE)) {
            return parseDoneCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_DELETE)) {
            return parseDeleteCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_FIND)) {
            return parseFindCommand(userInput);
        }
        if (userInput.startsWith(COMMAND_NEW_TODO)) {
            Task task = parseTodoTask(userInput);
            return new AddCommand(task);
        }
        if (userInput.startsWith(COMMAND_NEW_DEADLINE)) {
            Task task = parseDeadlineTask(userInput);
            return new AddCommand(task);
        }
        if (userInput.startsWith(COMMAND_NEW_EVENT)) {
            Task task = parseEventTask(userInput);
            return new AddCommand(task);
        }
        throw new DukeException("Sorry, I do not understand your command.");
    }

    /**
     * Takes in a user input String corresponding to a DoneCommand and returns a DoneCommand
     * containing the specified information
     *
     * @param userInput String corresponding to a DoneCommand
     * @return a DoneCommand containing the information specified in userInput
     * @throws DukeException If the userInput String does not contain a valid task index
     */
    private static DoneCommand parseDoneCommand(String userInput) throws DukeException {
        int taskIndex = -1;
        try {
            taskIndex = Integer.parseInt(userInput.substring(5).strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify the index of the task.");
        }
        return new DoneCommand(taskIndex);
    }

    /**
     * Takes in a user input String corresponding to a DeleteCommand and returns a DeleteCommand
     * containing the specified information
     *
     * @param userInput String corresponding to a DeleteCommand
     * @return a DeleteCommand containing the information specified in userInput
     * @throws DukeException If the userInput String does not contain a valid task index
     */
    private static DeleteCommand parseDeleteCommand(String userInput) throws DukeException {
        int taskIndex = -1;
        try {
            taskIndex = Integer.parseInt(userInput.substring(7).strip()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify the index of the task.");
        }
        return new DeleteCommand(taskIndex);
    }

    /**
     * Takes in a user input String corresponding to a FindCommand and returns a FindCommand
     * containing the specified information
     *
     * @param userInput String corresponding to a FindCommand
     * @return a FindCommand containing the information specified in userInput
     * @throws DukeException If the userInput String does not contain a keyword to search for
     */
    private static FindCommand parseFindCommand(String userInput) throws DukeException {
        String keyword = "";
        try {
            //if no space character after "find"
            if (userInput.charAt(4) != ' ') {
                throw new DukeException("Please enter your search in the format \"find [keyword]\".");
            }
            keyword = userInput.substring(5);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify a keyword to search for.");
        }
        return new FindCommand(keyword);
    }

    /**
     * Takes in the userInput String containing information for a ToDo and returns
     * a ToDo containing the specified information
     *
     * @param userInput the user input String that corresponds to a command to add
     *                  a ToDo
     * @return an Event that contains the details specified in userInput
     * @throws DukeException if the userInput String is not in the correct format
     *                       or has missing information
     */
    private static ToDo parseTodoTask(String userInput) throws DukeException {
        try {
            //if no space character after "todo"
            if (userInput.charAt(4) != ' ') {
                throw new DukeException("Please provide a todo in the format \"todo [task name]\".");
            }
            //if task name is not found
            String todoName = userInput.substring(4).strip();
            if (todoName.equals("")) {
                throw new DukeException("Please specify a task name.");
            }
            return new ToDo(todoName);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a todo in the format \"todo [task name]\".");
        }
    }

    /**
     * Takes in the userInput String containing information for a Deadline and returns
     * a Deadline containing the specified information
     *
     * @param userInput the user input String that corresponds to a command to add
     *                  a Deadline
     * @return an Event that contains the details specified in userInput
     * @throws DukeException if the userInput String is not in the correct format
     *                       or has missing information
     */
    private static Deadline parseDeadlineTask(String userInput) throws DukeException {
        try {
            //if no space character after "deadline"
            if (userInput.charAt(8) != ' ') {
                throw new DukeException("Please provide a deadline in the format \"deadline [task name] /by [date]\".");
            }
            int byIndex = userInput.indexOf(" /by ");
            //if " /by " separator is not found
            if (byIndex == -1) {
                throw new DukeException("Please use the \" /by \" separator to separate the deadline name and date.");
            }
            String deadlineName = userInput.substring(8, byIndex).strip();
            //if task name is found
            if (deadlineName.equals("")) {
                throw new DukeException("Please specify a deadline name.");
            }
            String deadlineDateString = userInput.substring(byIndex + 5).strip();
            //if do by date is not found
            if (deadlineDateString.equals("")) {
                throw new DukeException("Please specify a do by date.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
            LocalDate deadlineDate = LocalDate.parse(deadlineDateString, formatter);
            return new Deadline(deadlineName, deadlineDate);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide a deadline in the format \"deadline [task name] /by [date]\".");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide the date in the format \"dd mm yyyy\", eg. \"12 02 2021\".");
        }
    }

    /**
     * Takes in the userInput String containing information for an Event and returns
     * an Event containing the specified information
     *
     * @param userInput the user input String that corresponds to a command to add
     *                  an Event
     * @return an Event that contains the details specified in userInput
     * @throws DukeException if the userInput String is not in the correct format
     *                       or has missing information
     */
    private static Event parseEventTask(String userInput) throws DukeException {
        try {
            //if no space character after "event"
            if (userInput.charAt(5) != ' ') {
                throw new DukeException("Please provide an in the format \"event [task name] /at [date]\".");
            }
            int atIndex = userInput.indexOf(" /at ");
            //if " /at " separator is not found
            if (atIndex == -1) {
                throw new DukeException("Please use the \" /at \" separator to separate the event name and date.");
            }
            String eventName = userInput.substring(5, atIndex).strip();
            //if task name is not found
            if (eventName.equals("")) {
                throw new DukeException("Please specify an event name.");
            }
            String eventDate = userInput.substring(atIndex + 5).strip();
            //if event date is not found
            if (eventDate.equals("")) {
                throw new DukeException("Please specify an event date.");
            }
            return new Event(eventName, eventDate);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please provide an in the format \"event [task name] /at [date]\".");
        }
    }
}
