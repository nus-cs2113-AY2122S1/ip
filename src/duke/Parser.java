package duke;

import duke.exception.InvalidCommandException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingInputException;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;


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

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean parseCommand(String userInput) {
        String[] inputs = userInput.split(SEPARATOR_SPACE, 2);
        try {
            switch (inputs[0]) {
            case COMMAND_EXIT:
                return true;
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
            UI.printInvalidDescription();
        } catch (InvalidCommandException e) {
            UI.printInvalidCommand();
        } catch (InvalidIndexException e) {
            UI.printInvalidIndex();
        } catch (DateTimeParseException e) {
            UI.printInvalidDate();
        }
        return false;
    }

    private Task parseEvent(String input) throws MissingInputException, DateTimeParseException {
        String[] inputs = input.split(SEPARATOR_SLASH, 2);
        if (inputs.length == 1 || inputs[1].equals("")) { //no / detected
            throw new MissingInputException();
        }
        return new Event(inputs[0], parseDate(inputs[1]));
    }

    private Task parseDeadline(String input) throws MissingInputException, DateTimeParseException {
        String[] inputs = input.split(SEPARATOR_SLASH, 2);
        if (inputs.length == 1 || inputs[1].equals("")) { //no / detected
            throw new MissingInputException();
        }
        return new Deadline(inputs[0], parseDate(inputs[1]));
    }

    private Task parseToDo(String input) {
        return new ToDo(input);
    }

    private LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("[ddMMyyyy][dd/MM/yyyy][dd-MM-yyyy]"));
    }

}
