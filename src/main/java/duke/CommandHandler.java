package duke;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

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
        final String[] taskDescriptionAndBy = Parser.splitDeadlineDescriptionAndDate(input);
        final String description = taskDescriptionAndBy[0];
        final String by = taskDescriptionAndBy[1];

        try {
            tasks.addTask(new Deadline(description, by));
            Storage.writeTaskListToFile(tasks);
        } catch (DukeException e) {
            final String message = e.getMessage();
            if (message.equals(ExceptionMessages.EXCEPTION_NO_DESCRIPTION)) {
                Ui.showDeadlineDescriptionError();
            }
        }

    }

    private static void addEventTaskToList(String input, TaskList tasks) {
        final String[] taskDescriptionAndAt = Parser.splitEventDescriptionAndDate(input);
        final String description = taskDescriptionAndAt[0];
        final String at = taskDescriptionAndAt[1];
        try {
            tasks.addTask(new Event(description, at));
            Storage.writeTaskListToFile(tasks);
        } catch (DukeException e) {
            final String message = e.getMessage();
            if (message.equals(ExceptionMessages.EXCEPTION_NO_DESCRIPTION)) {
                Ui.showEventDescriptionError();
            }
        }
    }

}
