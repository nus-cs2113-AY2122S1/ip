package duke.command;

import duke.datasaver.DataManager;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import static duke.constants.DukeCommandStrings.*;

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
        if (userInput.trim().equalsIgnoreCase(LIST_COMMAND)) {
            handlePrintList();
        } else if (userInput.trim().toLowerCase().startsWith(DONE_COMMAND)) {
            handleTaskDone(userInput);
        } else if (userInput.trim().toLowerCase().startsWith(TODO_COMMAND)) {
            handleTodo(userInput);
        } else if (userInput.trim().toLowerCase().startsWith(DEADLINE_COMMAND)) {
            handleDeadline(userInput);
        } else if (userInput.trim().toLowerCase().startsWith(EVENT_COMMAND)) {
            handleEvent(userInput);
        } else if (userInput.trim().toLowerCase().startsWith(DELETE_COMMAND)) {
            handleDelete(userInput);
        } else {
            Ui.printUnrecognizedCommandMessage();
        }
    }

    /**
     * Prints a list of the current tasks Duke has
     *
     * @throws EmptyOrFullListException if task list is empty
     */
    public void printTaskList() throws EmptyOrFullListException {
        if (taskList.size() == 0) {
            throw new EmptyOrFullListException();
        }
        Ui.printTaskList(taskList);
    }

    /**
     * Marks a task as done
     *
     * @param doneCommand User command containing the task ID of the task to mark done
     */
    public void markTaskDone(String doneCommand) throws InvalidCommandException {
        String[] doneSentence = doneCommand.split(WHITESPACE_SEQUENCE);

        // Checks if done command entered does not follow the correct format of "done {task ID}".
        if (doneSentence.length != 2) {
            throw new InvalidCommandException();
        }

        try {
            int indexOfTaskToMarkDone = Integer.parseInt(doneSentence[1]) - 1;
            Ui.printTaskMarkedDoneMessage(taskList.get(indexOfTaskToMarkDone));
            taskList.get(indexOfTaskToMarkDone).setDone(true);
        } catch (NumberFormatException e) {
            Ui.printInvalidCommandFormatMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printTaskNotInListMessage();
        }
    }

    /**
     * Add a todo to Duke's task list.
     * Splits addedTodo by the first whitespace (or sequence of whitespaces) encountered to separate 'todo' command from
     * its description
     *
     * @param addedTodo User command containing the todo description
     */
    public void addTodo(String addedTodo) throws InvalidCommandException {
        String[] splitTodo = addedTodo.split(WHITESPACE_SEQUENCE, 2);

        if (splitTodo.length != 2) {
            throw new InvalidCommandException();
        }

        String todoDescription = splitTodo[1].trim();
        Todo newTodo = new Todo(todoDescription);
        taskList.add(newTodo);
        Ui.printTaskAddedMessage(newTodo, taskList.size());
    }

    /**
     * Add a deadline to Duke's task list
     * Splits addedDeadline initially by the first whitespace (or sequence of whitespaces) encountered to separate
     * 'deadline' command from its description and deadline. The description and deadline is then split by DEADLINE_PREFIX
     * to obtain the arguments needed for Deadline constructor
     *
     * @param addedDeadline User command containing the deadline description and deadline
     */
    public void addDeadline(String addedDeadline) throws InvalidCommandException {
        String[] splitDeadline = addedDeadline.split(WHITESPACE_SEQUENCE, 2);

        if (splitDeadline.length != 2) {
            throw new InvalidCommandException();
        }

        String[] deadlineDescriptionAndDeadline = splitDeadline[1].split(DEADLINE_PREFIX, 2);
        if (deadlineDescriptionAndDeadline.length != 2) {
            throw new InvalidCommandException();
        }

        String deadlineDescription = deadlineDescriptionAndDeadline[0].trim();
        String deadlineDeadline = deadlineDescriptionAndDeadline[1].trim();
        boolean isValidDeadline = !deadlineDescription.isEmpty() && !deadlineDeadline.isEmpty();
        // Deadline with valid format is added to task list
        if (isValidDeadline) {
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDeadline);
            taskList.add(newDeadline);
            Ui.printTaskAddedMessage(newDeadline, taskList.size());
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Add an event to Duke's task list
     * Splits addedEvent initially by the first whitespace (or sequence of whitespaces) encountered to separate
     * 'event' command from its description and time. The description and time is then split by EVENT_PREFIX
     * to obtain the arguments needed for Event constructor
     *
     * @param addedEvent User command containing the event description and time
     */
    public void addEvent(String addedEvent) throws InvalidCommandException {
        String[] splitEvent = addedEvent.split(WHITESPACE_SEQUENCE, 2);

        if (splitEvent.length != 2) {
            throw new InvalidCommandException();
        }

        String[] eventDescriptionAndTime = splitEvent[1].split(EVENT_PREFIX, 2);
        if (eventDescriptionAndTime.length != 2) {
            throw new InvalidCommandException();
        }

        String eventDescription = eventDescriptionAndTime[0].trim();
        String eventTime = eventDescriptionAndTime[1].trim();
        boolean isValidEvent = !eventDescription.isEmpty() && !eventTime.isEmpty();
        // Event with valid format is added to task list
        if (isValidEvent) {
            Event newEvent = new Event(eventDescription, eventTime);
            taskList.add(newEvent);
            Ui.printTaskAddedMessage(newEvent, taskList.size());
        } else {
            throw new InvalidCommandException();
        }
    }

    public void deleteTask(String deleteCommand) throws InvalidCommandException {
        String[] deleteSentence = deleteCommand.split("\\s+");

        // Checks if delete command entered does not follow the correct format of "delete {task ID}"
        if (deleteSentence.length != 2) {
            throw new InvalidCommandException();
        }

        try {
            int indexOfTaskToDelete = Integer.parseInt(deleteSentence[1]) - 1;
            Ui.printTaskDeletedMessage(taskList.get(indexOfTaskToDelete), taskList.size());
            taskList.remove(taskList.get(indexOfTaskToDelete));
        } catch (NumberFormatException e) {
            Ui.printInvalidCommandFormatMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printTaskNotInListMessage();
        }
    }

    public void handlePrintList() {
        try {
            printTaskList();
        } catch (EmptyOrFullListException emptyOrFullListException) {
            emptyOrFullListException.printEmptyListMessage();
        }
    }

    /**
     * Handles the marking of a task as done, taking into account any erroneous input
     *
     * @param userInput Command input by the user
     */
    public void handleTaskDone(String userInput) {
        try {
            markTaskDone(userInput);
        } catch (InvalidCommandException e) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    /**
     * Handles the adding of a todo to Duke's task list, including any erroneous input
     *
     * @param userInput Command input by the user
     */
    public void handleTodo(String userInput) {
        try {
            addTodo(userInput);
            DataManager.saveData(taskList);
        } catch (InvalidCommandException e) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    /**
     * Handles the adding of a deadline to Duke's task list, including any erroneous input
     *
     * @param userInput Command input by the user
     */
    public void handleDeadline(String userInput) {
        try {
            addDeadline(userInput);
            DataManager.saveData(taskList);
        } catch (InvalidCommandException e) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    /**
     * Handles the adding of an event to Duke's task list, including any erroneous input
     *
     * @param userInput Command input by the user
     */
    public void handleEvent(String userInput) {
        try {
            addEvent(userInput);
            DataManager.saveData(taskList);
        } catch (InvalidCommandException e) {
            Ui.printInvalidCommandFormatMessage();
        }
    }

    public void handleDelete(String userInput) {
        try {
            deleteTask(userInput);
            DataManager.saveData(taskList);
        } catch (InvalidCommandException e) {
            Ui.printInvalidCommandFormatMessage();
        }
    }
}
