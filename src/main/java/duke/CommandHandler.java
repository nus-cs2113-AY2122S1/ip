package duke;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Receives commands that come in from the user, and then
 * carries out the relevant command.
 */
public class CommandHandler {
    //Strings to define command type
    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST_TASKS = "list";
    private static final String COMMAND_MARK_TASK_AS_DONE = "done";
    private static final String COMMAND_ADD_TODO_TASK = "todo";
    private static final String COMMAND_ADD_DEADLINE_TASK = "deadline";
    private static final String COMMAND_ADD_EVENT_TASK = "event";
    private static final String COMMAND_DELETE_TASK = "delete";
    private static final String COMMAND_SAVE_TASK_LIST = "save";
    private static final String COMMAND_FIND_TASK = "find";


    /**
     * Takes in as <code>TaskList</code> object and user commands and executes the relevant methods.
     *
     * @param commandType Type of command.
     * @param commandArgs Arguments to the command.
     * @param tasks Task list which commands are operated on.
     * @throws DukeException If command is invalid.
     */
    public static void processInput(String commandType, String commandArgs, TaskList tasks) throws DukeException {
        switch (commandType) {
        case COMMAND_EXIT:
            exitProgram(tasks);
            break;
        case COMMAND_LIST_TASKS:
            listAllTasks(tasks);
            break;
        case COMMAND_MARK_TASK_AS_DONE:
            markTaskAsDone(commandArgs, tasks);
            break;
        case COMMAND_ADD_TODO_TASK:
            addTodoTaskToList(commandArgs, tasks);
            break;
        case COMMAND_ADD_DEADLINE_TASK:
            addDeadlineTaskToList(commandArgs, tasks);
            break;
        case COMMAND_ADD_EVENT_TASK:
            addEventTaskToList(commandArgs, tasks);
            break;
        case COMMAND_DELETE_TASK:
            deleteTask(commandArgs, tasks);
            break;
        case COMMAND_SAVE_TASK_LIST:
            saveTaskList(tasks);
            break;
        case COMMAND_FIND_TASK:
            findTask(commandArgs, tasks);
            break;
        default:
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_COMMAND);
        }
    }

    private static void exitProgram(TaskList tasks) {
        Storage.writeTaskListToFile(tasks);
        Ui.printExitProgramMessage();
        System.exit(0);
    }

    private static void saveTaskList(TaskList tasks) {
        Storage.writeTaskListToFile(tasks);
        Ui.printSaveTaskMessage();
    }

    private static void listAllTasks(TaskList tasks) {
        tasks.listAllTasks();
    }

    private static void markTaskAsDone(String taskNumber, TaskList tasks) {
        try {
            tasks.markTaskAsDone(taskNumber);
            Storage.writeTaskListToFile(tasks);
        } catch (DukeException e) {
            final String message = e.getMessage();
            switch (message) {
            case ExceptionMessages.EXCEPTION_NO_TASK_NUMBER:
                Ui.showNoTaskNumberError();
                break;
            case ExceptionMessages.EXCEPTION_INVALID_TASK_NUMBER:
                Ui.showInvalidTaskNumberError();
                break;
            }
        } catch (NumberFormatException e) {
            Ui.showInvalidTaskNumberFormatError();
        }
    }

    private static void deleteTask(String input, TaskList tasks) {
        try {
            tasks.deleteTask(input);
            Storage.writeTaskListToFile(tasks);
        } catch (DukeException e) {
            final String message = e.getMessage();
            switch (message) {
            case ExceptionMessages.EXCEPTION_NO_TASK_NUMBER:
                Ui.showNoTaskNumberError();
                break;
            case ExceptionMessages.EXCEPTION_INVALID_TASK_NUMBER:
                Ui.showInvalidTaskNumberError();
                break;
            }
        } catch (NumberFormatException e) {
            Ui.showInvalidTaskNumberFormatError();
        }
    }

    private static void addTodoTaskToList(String input, TaskList tasks) {
        try {
            tasks.addTask(new Todo(input));
            Storage.writeTaskListToFile(tasks);
        } catch (DukeException e) {
            final String message = e.getMessage();
            if (message.equals(ExceptionMessages.EXCEPTION_NO_DESCRIPTION)) {
                Ui.showTodoDescriptionError();
            }
        }

    }

    private static void addDeadlineTaskToList(String input, TaskList tasks) {
        try {
            final String[] taskDescriptionAndBy = Parser.splitDeadlineDescriptionAndTime(input);
            final String description = taskDescriptionAndBy[0];
            final String by = taskDescriptionAndBy[1];
            LocalDateTime dateTime = Parser.parseDateTime(by);
            tasks.addTask(new Deadline(description, dateTime));
            Storage.writeTaskListToFile(tasks);
        } catch (DukeException e) {
            final String message = e.getMessage();
            switch (message) {
            case ExceptionMessages.EXCEPTION_NO_DESCRIPTION:
                Ui.showDeadlineDescriptionError();
                break;
            case ExceptionMessages.EXCEPTION_WRONG_DEADLINE_FORMAT:
                Ui.showDeadlineFormatError();
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            Ui.showDateTimeFormatError();
        }
    }

    private static void addEventTaskToList(String input, TaskList tasks) {
        try {
            final String[] taskDescriptionAndAt = Parser.splitEventDescriptionAndTime(input);
            final String description = taskDescriptionAndAt[0];
            final String at = taskDescriptionAndAt[1];
            LocalDateTime dateTime = Parser.parseDateTime(at);
            tasks.addTask(new Event(description, dateTime));
            Storage.writeTaskListToFile(tasks);
        } catch (DukeException e) {
            final String message = e.getMessage();
            switch (message) {
            case ExceptionMessages.EXCEPTION_NO_DESCRIPTION:
                Ui.showEventDescriptionError();
                break;
            case ExceptionMessages.EXCEPTION_WRONG_EVENT_FORMAT:
                Ui.showEventFormatError();
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            Ui.showDateTimeFormatError();
        }
    }

    private static void findTask(String input, TaskList tasks) {
        try {
            tasks.findTasks(input);
        } catch (DukeException e) {
            if (e.getMessage().equals(ExceptionMessages.EXCEPTION_EMPTY_SEARCH_QUERY)) {
                Ui.showEmptyQueryError();
            }
        }
    }

}
