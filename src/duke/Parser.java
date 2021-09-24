package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingInputException;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import ui.ErrorUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    public static final String SEPARATOR_SPACE = " ";
    public static final String SEPARATOR_SLASH = "/";

    private TaskList taskList;

    /**
     * Creates an instance of a Parser from a given taskList for tasks to be stored in.
     *
     * @param taskList TaskList for the Tasks to be stored in.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the raw input data from the user by checking the first word of the line entered,
     * then executing the corresponding command accordingly.
     *
     * @param userInput String containing the raw user input to be parsed
     */
    public void parseCommand(String userInput) {
        String[] inputs = userInput.split(SEPARATOR_SPACE, 2);
        try {
            switch (inputs[0]) {
            case COMMAND_EXIT:
                return;
            case COMMAND_LIST:
                taskList.listTasks();
                break;
            case COMMAND_DONE:
                taskList.completeTask(inputs[1]);
                break;
            case COMMAND_DEADLINE:
                taskList.addTask(parseDeadline(inputs[1]));
                break;
            case COMMAND_EVENT:
                taskList.addTask(parseEvent(inputs[1]));
                break;
            case COMMAND_TODO:
                taskList.addTask(parseToDo(inputs[1]));
                break;
            case COMMAND_DELETE:
                taskList.deleteTask(inputs[1]);
                break;
            case COMMAND_FIND:
                taskList.findTask(inputs[1]);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (MissingInputException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            ErrorUI.printInvalidDescription();
        } catch (InvalidCommandException e) {
            ErrorUI.printInvalidCommand();
        } catch (InvalidIndexException e) {
            ErrorUI.printInvalidIndex();
        } catch (DateTimeParseException e) {
            ErrorUI.printInvalidDate();
        }
    }

    /**
     * Parses the user input into an Event object and returns it to be added into the TaskList.
     *
     * @param input String containing the user input of the task details without the command
     * @return Task object containing an Event with a LocalDate attribute.
     * @throws MissingInputException  when there is no date and time specified in the input.
     * @throws DateTimeParseException when the given date cannot be parsed into a LocalDate object properly.
     */
    private Task parseEvent(String input) throws MissingInputException, DateTimeParseException {
        String[] inputs = input.split(SEPARATOR_SLASH, 2);
        if (inputs.length == 1 || inputs[1].equals("")) { //no / detected
            throw new MissingInputException();
        }
        return new Event(inputs[0], parseDate(inputs[1]));
    }

    /**
     * Parses the user input into a Deadline object and returns it to be added into the TaskList.
     *
     * @param input String containing the user input of the task details without the command
     * @return Task object containing a Deadline with a LocalDate attribute.
     * @throws MissingInputException  when there is no date and time specified in the input.
     * @throws DateTimeParseException when the given date cannot be parsed into a LocalDate object properly.
     */
    private Task parseDeadline(String input) throws MissingInputException, DateTimeParseException {
        String[] inputs = input.split(SEPARATOR_SLASH, 2);
        if (inputs.length == 1 || inputs[1].equals("")) { //no / detected
            throw new MissingInputException();
        }
        return new Deadline(inputs[0], parseDate(inputs[1]));
    }

    /**
     * Parses the user input into a ToDo object and returns it to be added into the TaskList.
     *
     * @param input String containing the user input of the task details without the command
     * @return Task object containing a Deadline with a LocalDate attribute.
     */
    private Task parseToDo(String input) {
        return new ToDo(input);
    }

    /**
     * Parses the given String containing the date into a LocalDate object.
     *
     * @param date String containing the date in the format ddMMyyyy or dd/MM/yyyy or dd-MM-yyyy
     * @return Returns a LocalDate object containing the date.
     * @throws DateTimeParseException when the given date cannot be parsed into a LocalDate object properly.
     */
    private LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("[ddMMyyyy][dd/MM/yyyy][dd-MM-yyyy]"));
    }
}
