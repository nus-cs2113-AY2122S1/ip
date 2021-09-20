package duke.task;

import duke.datasaver.DataManager;
import duke.exception.InvalidCommandFormatException;
import duke.exception.InvalidDateTimeException;
import duke.parser.Parser;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the list containing Duke's {@code Task}s.
 * Tasks can be added, deleted, and marked done from the list in this class.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Parses {@code userInput} to obtain the task ID of the task to mark done.
     * Marks the task with the task ID in {@code taskList} as done. An error message is printed if task ID entered by
     * the user is non-numeric, lacking from the command or not in the task list.
     *
     * @param userInput command entered by the user
     * @param dataManager {@code DataManager} which saves the updated done status of the task to Duke's storage file
     * @throws InvalidCommandFormatException if task ID is non-numeric, lacking from command or not in the task list
     */
    public void markTaskDone(String userInput, DataManager dataManager) throws InvalidCommandFormatException {
        try {
            String[] doneSentence = Parser.parseDoneCommand(userInput);

            /* "- 1" to convert from 1-based to 0-based indexing */
            int indexOfTaskToMarkDone = Integer.parseInt(doneSentence[1]) - 1;
            taskList.get(indexOfTaskToMarkDone).setDone(true);
            Ui.printTaskMarkedDoneMessage(taskList.get(indexOfTaskToMarkDone));

            /* Saves taskList to storage file as the done status of the task has been updated */
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException | NumberFormatException fe) {
            throw new InvalidCommandFormatException();
        } catch (IndexOutOfBoundsException ioobe) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Parses {@code userInput} to obtain a {@code Todo} object to add to the task list.
     * The {@code Todo} is added to the task list. An error message is printed if the todo is lacking a description.
     *
     * @param userInput command entered by the user
     * @param dataManager {@code DataManager} which saves the new {@code Todo} to Duke's storage
     * @throws InvalidCommandFormatException if todo is lacking description
     */
    public void addTodo(String userInput, DataManager dataManager) throws InvalidCommandFormatException {
        try {
            Todo newTodo = Parser.parseAddTodoCommand(userInput);
            taskList.add(newTodo);
            Ui.printTaskAddedMessage(newTodo, taskList.size());
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException icfe) {
            throw new InvalidCommandFormatException();
        }
    }

    /**
     * Parses {@code userInput} to obtain a {@code Deadline} object to add to the task list.
     * The {@code Deadline} is added to the task list. An error message is printed if the deadline is lacking either a
     * description or a date and time. An error message is also printed if the date and time do not follow the format
     * dd-MM-yyyy HH:mm or if an invalid date and time is entered.
     *
     * @param userInput command entered by the user
     * @param dataManager {@code DataManager} which saves the new {@code Deadline} to Duke's storage
     * @throws InvalidCommandFormatException if the deadline is lacking either a description or a date and time
     * @throws InvalidDateTimeException if the date and time do not follow the correct format or if an invalid date
     *                                  date and time is entered
     */
    public void addDeadline(String userInput, DataManager dataManager) throws InvalidCommandFormatException, InvalidDateTimeException {
        try {
            Deadline newDeadline = Parser.parseAddDeadlineCommand(userInput);
            taskList.add(newDeadline);
            Ui.printTaskAddedMessage(newDeadline, taskList.size());
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException icfe) {
            throw new InvalidCommandFormatException();
        } catch (DateTimeParseException dtpe) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Parses {@code userInput} to obtain a {@code Event} object to add to the task list.
     * The {@code Event} is added to the task list. An error message is printed if the event is lacking either a
     * description or a date and time. An error message is also printed if the date and time do not follow the format
     * dd-MM-yyyy HH:mm or if an invalid date and time is entered.
     *
     * @param userInput command entered by the user
     * @param dataManager {@code DataManager} which saves the new {@code Event} to Duke's storage
     * @throws InvalidCommandFormatException if the event is lacking either a description or a date and time
     * @throws InvalidDateTimeException if the date and time do not follow the correct format or if an invalid date
     *                                  date and time is entered
     */
    public void addEvent(String userInput, DataManager dataManager) throws InvalidCommandFormatException, InvalidDateTimeException {
        try {
            Event newEvent = Parser.parseAddEventCommand(userInput);
            taskList.add(newEvent);
            Ui.printTaskAddedMessage(newEvent, taskList.size());
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException icfe) {
            throw new InvalidCommandFormatException();
        } catch (DateTimeParseException dtpe) {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Parses {@code userInput} to obtain the task ID of the task to delete.
     * Deletes the task with the task ID in {@code taskList}. An error message is printed if task ID entered by
     * the user is non-numeric, lacking from the command or not in the task list.
     *
     * @param userInput command entered by the user
     * @param dataManager {@code DataManager} which saves the updated task list to Duke's storage file
     * @throws InvalidCommandFormatException if task ID is non-numeric, lacking from command or not in the task list
     */
    public void deleteTask(String userInput, DataManager dataManager) throws InvalidCommandFormatException {
        try {
            String[] deleteSentence = Parser.parseDeleteCommand(userInput);

            /* "- 1" to convert from 1-based to 0-based indexing */
            int indexOfTaskToDelete = Integer.parseInt(deleteSentence[1]) - 1;
            Ui.printTaskDeletedMessage(taskList.get(indexOfTaskToDelete), taskList.size());
            taskList.remove(taskList.get(indexOfTaskToDelete));

            /* Saves taskList to storage file as a task has been deleted */
            dataManager.saveData(taskList);
        } catch (InvalidCommandFormatException | NumberFormatException fe) {
            throw new InvalidCommandFormatException();
        } catch (IndexOutOfBoundsException ioobe) {
            throw new IndexOutOfBoundsException();
        }
    }
}
