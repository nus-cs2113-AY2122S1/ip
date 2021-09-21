package duke.parser;

import duke.command.*;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDos;
import duke.text.Text;

public class Parser extends Text {

    protected TaskList taskList;
    protected Ui ui;
    protected Storage storage;

    /**
     * A constructor to parse user inputs to suitable commands.
     *
     * @param taskList user's task list.
     * @param ui  the user interface.
     * @param storage A text file storage to store task into hard disk.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parse user inputs to run its respective command methods.
     *
     * @param userInput inputs from user.
     * @return Command object.
     * @throws DukeException exception thrown when an unknown command is input by user.
     */
    public Command parseCommand(String userInput) throws DukeException {
        String[] splitInput = userInput.trim().split(" ", 2);
        switch(splitInput[0]) {
        case TODO:
        case DEADLINE:
        case EVENT:
            return parseAddCommand(userInput);
        case DONE:
            return parseDoneCommand(userInput);
        case DELETE:
            return parseDeleteCommand(userInput);
        case LIST:
            return new ListCommand(taskList, ui);
        case HELP:
            return new HelpCommand();
        case BYE:
            return new ExitCommand();
        default:
            throw new DukeException(UNKNOWN_COMMAND);
        }
    }

    /**
     * Parse user input to suitable parameters for adding a new task.
     *
     * @param userInput inputs from user.
     * @return AddCommand object.
     * @throws DukeException exception thrown when task type is unknown or task description is empty.
     */
    private Command parseAddCommand(String userInput) throws DukeException {
        String[] splitInput = userInput.trim().split(" ", 2);
        String[] taskNameAndDueDate;
        if (splitInput.length <= 1) {
            throw new DukeException(EMPTY_TASK_DESCRIPTION);
        }
        switch (splitInput[0]) {
        case TODO:
            return new AddCommand(taskList, ui, new ToDos(splitInput[1]));
        case DEADLINE:
            taskNameAndDueDate = splitInput[1].split(" /by ", 2);
            return new AddCommand(taskList, ui, new Deadlines(taskNameAndDueDate[0], taskNameAndDueDate[1]));
        case EVENT:
            taskNameAndDueDate = splitInput[1].split(" /at ", 2);
            return new AddCommand(taskList, ui, new Events(taskNameAndDueDate[0], taskNameAndDueDate[1]));
        default:
            throw new DukeException(UNKNOWN_COMMAND);
        }
    }

    /**
     * Parse user input to suitable parameters for marking a task as done in taskList.
     *
     * @param userInput inputs from user.
     * @return CompleteCommand object.
     * @throws DukeException exception thrown when no task number is specified.
     */
    private Command parseDoneCommand(String userInput) throws DukeException {
        String[] taskToMark = userInput.split(" ", 2);
        if (taskToMark.length <= 1) {
            throw new DukeException(NO_TASK_NUMBER);
        } else {
            return new CompleteCommand(taskList, ui, Integer.parseInt(taskToMark[1]) - 1);
        }
    }

    /**
     * Parse user input to suitable parameters to delete a task in taskList.
     *
     * @param userInput inputs from user.
     * @return DeleteCommand object.
     * @throws DukeException exception thrown when no task number is specified.
     */
    private Command parseDeleteCommand(String userInput) throws DukeException {
        String[] taskToDelete = userInput.split(" ", 2);
        if (taskToDelete.length <= 1) {
            throw new DukeException(NO_TASK_NUMBER);
        } else {
            return new DeleteCommand(taskList, ui, Integer.parseInt(taskToDelete[1]) - 1);
        }
    }
}
