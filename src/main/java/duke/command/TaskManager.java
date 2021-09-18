package duke.command;

import duke.datasaver.DataManager;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;
import duke.exception.EmptyListException;
import duke.exception.InvalidCommandFormatException;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> taskList;

    // Constructor
    public TaskManager() {
        taskList = new ArrayList<>();
    }

    // Getter
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Handles commands input by the user, will print error message if command is of
     * the wrong format.
     *
     * @param userInput Command input by the user
     */
    public void handleUserInput(String userInput) {
        CommandWord commandWord = Parser.parseCommandWord(userInput);
        switch (commandWord) {
        case LIST:
            printTaskList();
            break;
        case DONE:
            markTaskDone(userInput);
            break;
        case TODO:
            addTodo(userInput);
            break;
        case DEADLINE:
            addDeadline(userInput);
            break;
        case EVENT:
            addEvent(userInput);
            break;
        case DELETE:
            deleteTask(userInput);
            break;
        case INVALID:
            Ui.printUnrecognizedCommandMessage();
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + commandWord);
        }
    }

    /**
     * Prints a list of the current tasks Duke has
     */
    public void printTaskList() {
        try {
            checkListCapacity(taskList);
            Ui.printTaskList(taskList);
        } catch (EmptyListException e) {
            Ui.printEmptyListMessage();
        }
    }

    /**
     * Marks a task as done
     *
     * @param userInput User command containing the task ID of the task to mark done
     */
    public void markTaskDone(String userInput) {
        try {
            String[] doneSentence = Parser.parseDoneCommand(userInput);
            int indexOfTaskToMarkDone = Integer.parseInt(doneSentence[1]) - 1;
            taskList.get(indexOfTaskToMarkDone).setDone(true);
            Ui.printTaskMarkedDoneMessage(taskList.get(indexOfTaskToMarkDone));
            DataManager.saveData(taskList);
        } catch (InvalidCommandFormatException | NumberFormatException e) {
            Ui.printInvalidCommandFormatMessage();
        } catch (IndexOutOfBoundsException e) {
            Ui.printTaskNotInListMessage();
        }
    }

    /**
     * Add a todo to Duke's task list.
     * Splits userInput by the first whitespace (or sequence of whitespaces) encountered to separate 'todo' command from
     * its description
     *
     * @param userInput User command containing the todo description
     */
    public void addTodo(String userInput) {
        try {
            Todo newTodo = Parser.parseAddTodoCommand(userInput);
            taskList.add(newTodo);
            Ui.printTaskAddedMessage(newTodo, taskList.size());
            DataManager.saveData(taskList);
        } catch (InvalidCommandFormatException e) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    /**
     * Add a deadline to Duke's task list
     * Splits userInput initially by the first whitespace (or sequence of whitespaces) encountered to separate
     * 'deadline' command from its description and deadline. The description and deadline is then split by DEADLINE_PREFIX
     * to obtain the arguments needed for Deadline constructor
     *
     * @param userInput User command containing the deadline description and deadline
     */
    public void addDeadline(String userInput) {
        try {
            Deadline newDeadline = Parser.parseAddDeadlineCommand(userInput);
            taskList.add(newDeadline);
            Ui.printTaskAddedMessage(newDeadline, taskList.size());
            DataManager.saveData(taskList);
        } catch (InvalidCommandFormatException e) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    /**
     * Add an event to Duke's task list
     * Splits userInput initially by the first whitespace (or sequence of whitespaces) encountered to separate
     * 'event' command from its description and time. The description and time is then split by EVENT_PREFIX
     * to obtain the arguments needed for Event constructor
     *
     * @param userInput User command containing the event description and time
     */
    public void addEvent(String userInput) {
        try {
            Event newEvent = Parser.parseAddEventCommand(userInput);
            taskList.add(newEvent);
            Ui.printTaskAddedMessage(newEvent, taskList.size());
            DataManager.saveData(taskList);
        } catch (InvalidCommandFormatException e) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    public void deleteTask(String userInput) {
        try {
            String[] deleteSentence = Parser.parseDeleteCommand(userInput);
            int indexOfTaskToDelete = Integer.parseInt(deleteSentence[1]) - 1;
            Ui.printTaskDeletedMessage(taskList.get(indexOfTaskToDelete), taskList.size());
            taskList.remove(taskList.get(indexOfTaskToDelete));
            DataManager.saveData(taskList);
        } catch (InvalidCommandFormatException | NumberFormatException e) {
            Ui.printInvalidCommandFormatMessage();
        } catch (IndexOutOfBoundsException e) {
            Ui.printTaskNotInListMessage();
        }
    }

    private static void checkListCapacity(ArrayList<Task> taskList) throws EmptyListException {
        if (taskList.size() == 0) {
            throw new EmptyListException();
        }
    }
}
