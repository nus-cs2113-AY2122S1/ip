package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.program.LizUi;
import duke.program.TaskList;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private static final int TODO_DESCRIPTION_START_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_START_INDEX = 9;
    private static final int EVENT_DESCRIPTION_START_INDEX = 6;

    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private String line;
    private String taskType;

    public AddCommand(String line, String taskType) {
        this.line = line;
        this.taskType = taskType;
    }

    /**+
     * Executes command to add a task to the TaskList.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    @Override
    public void executeCommand(TaskList tasks, LizUi ui) {
        switch (taskType) {
        case "T":
            addNewTodoWithExceptionHandling(line, tasks, ui);
            break;
        case "D":
            addNewDeadlineWithExceptionHandling(line, tasks, ui);
            break;
        case "E":
            addNewEventWithExceptionHandling(line, tasks, ui);
            break;
        }
    }

    /**
     * Adds a new event task with corresponding exception handling. Checked exceptions when caught, print
     * a corresponding error message.
     * @param line string containing user input.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    private void addNewEventWithExceptionHandling(String line, TaskList tasks, LizUi ui) {
        try {
            tasks.addNewEvent(line.substring(EVENT_DESCRIPTION_START_INDEX));
            ui.printAddedTaskMessage(tasks.getLatestTask(), tasks.getTaskCount());
        } catch (EmptyDescriptionException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            ui.printEmptyDescriptionMessage(COMMAND_EVENT);
        } catch (InvalidFormatException e) {
            ui.printInvalidFormatMessage(COMMAND_EVENT);
        } catch (DateTimeParseException e) {
            ui.printInvalidDateTimeFormatMessage();
        }
    }

    /**
     * Adds a new deadline task with corresponding exception handling. Checked exceptions when caught, print
     * a corresponding error message.
     * @param line string containing user input.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    private void addNewDeadlineWithExceptionHandling(String line, TaskList tasks, LizUi ui) {
        try {
            tasks.addNewDeadline(line.substring(DEADLINE_DESCRIPTION_START_INDEX));
            ui.printAddedTaskMessage(tasks.getLatestTask(), tasks.getTaskCount());
        } catch (EmptyDescriptionException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            ui.printEmptyDescriptionMessage(COMMAND_DEADLINE);
        } catch (InvalidFormatException e) {
            ui.printInvalidFormatMessage(COMMAND_DEADLINE);
        } catch (DateTimeParseException e) {
            ui.printInvalidDateTimeFormatMessage();
        }
    }

    /**
     * Adds a new todo task with corresponding exception handling. Checked exceptions when caught, print
     * a corresponding error message.
     * @param line string containing user input.
     * @param tasks TaskList of all tasks.
     * @param ui ui object of Duke.
     */
    private void addNewTodoWithExceptionHandling(String line, TaskList tasks, LizUi ui) {
        try {
            tasks.addNewTodo(line.substring(TODO_DESCRIPTION_START_INDEX));
            ui.printAddedTaskMessage(tasks.getLatestTask(), tasks.getTaskCount());
        } catch (EmptyDescriptionException | StringIndexOutOfBoundsException e) {
            ui.printEmptyDescriptionMessage(COMMAND_TODO);
        }
    }
}
