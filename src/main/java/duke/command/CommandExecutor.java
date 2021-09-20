package duke.command;

import duke.datasaver.DataManager;
import duke.exception.InvalidDateTimeException;
import duke.exception.QueryNotFoundException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.EmptyListException;
import duke.exception.InvalidCommandFormatException;

import java.util.ArrayList;

/**
 * Processes user input to determine the command entered by the user.
 * The command is then executed by calling the appropriate method.
 */
public class CommandExecutor {

    /**
     * Uses the help of {@code Parser} to determine the {@code CommandWord} from user input.
     * Based on the {@code CommandWord} returned, the appropriate method is called to execute the command.
     *
     * @param userInput command entered by the user
     * @param taskList {@code TaskList} on which the commands are executed
     * @param dataManager {@code DataManager} which handles the changes made to the tasks
     */
    public void execute(String userInput, TaskList taskList, DataManager dataManager) {
        CommandWord commandWord = Parser.parseCommandWord(userInput);
        switch (commandWord) {
        case LIST:
            executeList(taskList);
            break;
        case DONE:
            executeDone(userInput, taskList, dataManager);
            break;
        case TODO:
            executeAddTodo(userInput, taskList, dataManager);
            break;
        case DEADLINE:
            executeAddDeadline(userInput, taskList, dataManager);
            break;
        case EVENT:
            executeAddEvent(userInput, taskList, dataManager);
            break;
        case DELETE:
            executeDelete(userInput, taskList, dataManager);
            break;
        case HELP:
            Ui.printHelp();
            break;
        case FIND:
            executeFind(userInput, taskList);
            break;
        case EXIT:
            Ui.printByeMessage();
            break;
        case INVALID:
            Ui.printUnrecognizedCommandMessage();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + commandWord);
        }
    }

    /**
     * Determines if the user input is a {@code bye} command.
     * If it is a {@code bye} command, the loop in the main class terminates, resulting in termination of the program.
     *
     * @param userInput command entered by the user
     * @return true if the user input is a {@code bye} command, false otherwise
     */
     public boolean isExit(String userInput) {
        CommandWord commandWord = Parser.parseCommandWord(userInput);
        return commandWord.equals(CommandWord.EXIT);
    }

    /**
     * Executes the {@code list} command and prints out the entire each task in a list along with its
     * type, description and done status.
     *
     * @param taskList {@code TaskList} containing the tasks to be printed
     */
    public void executeList(TaskList taskList) {
        try {
            checkListSize(taskList);
            Ui.printTaskList(taskList.getTaskList());
        } catch (EmptyListException ele) {
            Ui.printEmptyListMessage();
        }
    }

    /**
     * Executes the marking of a task as done by passing the user input to the task list to be marked done.
     * An error message is printed if the task ID entered by the user is non-numeric, lacking from the command
     * or not in the task list.
     *
     * @param userInput command entered by the user
     * @param taskList  {@code TaskList} containing the task to be mark done
     * @param dataManager {@code DataManager} which saves the updated done status of the task to Duke's storage
     */
    public void executeDone(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.markTaskDone(userInput, dataManager);
        } catch (InvalidCommandFormatException | NumberFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        } catch (IndexOutOfBoundsException ioobe) {
            Ui.printTaskNotInListMessage();
        }
    }

    /**
     * Executes the addition of a {@code Todo} to {@code taskList} by passing the user input to it.
     * An error message is printed if the user input lacks the description of the {@code Todo}
     *
     * @param userInput command entered by the user
     * @param taskList {@code TaskList} where the todo is to be added to
     * @param dataManager {@code DataManager} which saves the new {@code Todo} to Duke's storage
     */
    public void executeAddTodo(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.addTodo(userInput, dataManager);
        } catch (InvalidCommandFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    /**
     * Executes the addition of a {@code Deadline} to {@code taskList} by passing the user input to it.
     * An error message is printed if {@code userInput} lacks the deadline description or the deadline.
     * An error message is also printed if the date and time entered do not follow the format dd-MM-yyyy HH:mm or
     * if an invalid date and time is entered.
     *
     * @param userInput command entered by the user
     * @param taskList task list where the deadline is to be added to
     * @param dataManager data manager which saves the new deadline to Duke's storage
     */
    public void executeAddDeadline(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.addDeadline(userInput, dataManager);
        } catch (InvalidCommandFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        } catch (InvalidDateTimeException idte) {
            Ui.printInvalidDateTimeMessage();
        }
    }

    /**
     * Executes the addition of an {@code Event} to the {@code taskList} by passing the user input to it.
     * An error message is printed if {@code userInput} lacks the event description or the event time.
     * An error message is also printed if the date and time entered do not follow the format dd-MM-yyyy HH:mm or
     * if an invalid date and time is entered.
     *
     * @param userInput command entered by the user
     * @param taskList {@code TaskList} where the event is to be added to
     * @param dataManager {@code DataManager} which saves the new event to Duke's storage
     */
    public void executeAddEvent(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.addEvent(userInput, dataManager);
        } catch (InvalidCommandFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        } catch (InvalidDateTimeException idte) {
            Ui.printInvalidDateTimeMessage();
        }
    }

    /**
     * Executes the deletion of a task py passing {@code userInput} to the {@code TaskList}.
     * An error message is printed if the task ID entered by the user is non-numeric, lacking from the command
     * or not in the task list.
     *
     * @param userInput command entered by the user
     * @param taskList {@code TaskList} containing the task to be deleted
     * @param dataManager {@code DataManager} which removes the deleted task from Duke's storage
     */
    public void executeDelete(String userInput, TaskList taskList, DataManager dataManager) {
        try {
            taskList.deleteTask(userInput, dataManager);
        } catch (InvalidCommandFormatException | NumberFormatException fe) {
            Ui.printInvalidCommandFormatMessage();
        } catch (IndexOutOfBoundsException ioobe) {
            Ui.printTaskNotInListMessage();
        }
    }

    /**
     * Executes find by creating a list with the tasks containing the keyword and printing the list.
     * {@code userInput} is parsed to extract the keyword (or query) and the query is searched for in {@code taskList}.
     *
     * @param userInput command entered by the user
     * @param taskList task list to be searched
     */
    public void executeFind(String userInput, TaskList taskList) {
        try {
            String query = Parser.parseFindCommand(userInput);
            ArrayList<Task> tasksContainingQuery = search(taskList.getTaskList(), query);
            Ui.printTaskListContainingQuery(tasksContainingQuery, query);
        } catch (InvalidCommandFormatException icfe) {
            Ui.printInvalidCommandFormatMessage();
        } catch (QueryNotFoundException qnfe) {
            Ui.printQueryNotFoundMessage();
        }
    }

    /**
     * Helper function which searches for a query in {@code taskList} A query is said to be found in a {@code Task} if
     * it is a continuous substring/contained in the task description.
     *
     * @param taskList task list to be searched
     * @param query keyword to be searched for in the task list
     * @return a list of tasks containing the query
     * @throws QueryNotFoundException if the query could not be found in any of the tasks in the task list
     */
    private static ArrayList<Task> search(ArrayList<Task> taskList, String query) throws QueryNotFoundException {
        ArrayList<Task> taskListContainingQuery = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getDescription().toLowerCase().contains(query.toLowerCase())) {
                taskListContainingQuery.add(t);
            }
        }
        if (taskListContainingQuery.isEmpty()) {
            throw new QueryNotFoundException();
        }
        return taskListContainingQuery;
    }

    /**
     * Helper function which checks if {@code taskList} is empty. This method throws an exception to allow for the
     * printing of an error message in {@code executeList}.
     *
     * @param taskList task list to be checked
     * @throws EmptyListException if the task list is empty
     */
    private static void checkListSize(TaskList taskList) throws EmptyListException {
        if (taskList.size() == 0) {
            throw new EmptyListException();
        }
    }
}
