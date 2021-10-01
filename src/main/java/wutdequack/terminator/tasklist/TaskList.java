package wutdequack.terminator.tasklist;

import static wutdequack.terminator.common.MagicValues.COMPLETION_INDEX;
import static wutdequack.terminator.common.MagicValues.DATE_TIME_INDEX;
import static wutdequack.terminator.common.MagicValues.DEADLINE_TYPE;
import static wutdequack.terminator.common.MagicValues.EVENT_TYPE;
import static wutdequack.terminator.common.MagicValues.FROM_FILE;
import static wutdequack.terminator.common.MagicValues.FROM_USER;
import static wutdequack.terminator.common.MagicValues.TASK_NAME_INDEX;
import static wutdequack.terminator.common.MagicValues.TODO_TYPE;
import static wutdequack.terminator.common.MagicValues.tasksList;
import static wutdequack.terminator.common.MagicValues.ui;

import java.util.Objects;
import java.util.stream.Stream;
import wutdequack.terminator.ui.TextUi;
import wutdequack.terminator.parser.Parser;
import wutdequack.terminator.objects.task.Task;
import wutdequack.terminator.objects.task.ToDo;
import wutdequack.terminator.objects.task.Event;
import wutdequack.terminator.objects.task.Deadline;
import wutdequack.terminator.objects.exception.MissingVariablesException;

import java.util.ArrayList;

public class TaskList {

    private Parser parser;

    /**
     * Global Array List of all tasks created by the user.
     */

    public TaskList() {
        this.parser = new Parser();
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Handles the creation of done tasks.
     * @param userLine Line of input from user.
     */
    public void handleDoneTask(String userLine) {
        try {
            // Parse out task number from user input
            int taskNumber = parser.getTaskNumberFromInput(userLine);
            // Update the list and print respective message
            updateTaskCompletionStatus(taskNumber);
            String taskString = getTaskStringFromIndex(taskNumber);
            ui.printUpdateMessage(taskString);
        } catch (IndexOutOfBoundsException e) {
            ui.printOutOfBoundsMessage();
        }
    }

    /**
     * Handler function to delete task from list.
     * @param userLine Line of input from user.
     */
    public void handleDeleteTask(String userLine) {
        try {
            // Parse out task number from user input
            int taskNumber = parser.getTaskNumberFromInput(userLine);
            // Print respective message and delete the task
            deleteTaskFromList(taskNumber);
            String taskString = getTaskStringFromIndex(taskNumber);
            ui.printDeleteMessage(taskString);
        } catch (IndexOutOfBoundsException e) {
            ui.printOutOfBoundsMessage();
        }
    }

    /**
     * Retrieves Task String based on task number index in list.
     * @param taskNumber Index of task.
     * @return A String containing the toString version of the Task.
     */
    private String getTaskStringFromIndex(int taskNumber) {
        return tasksList.get(taskNumber).toString();
    }

    /**
     * Updates the completion status of the Task to true.
     * @param taskNumber The index of the task to be updated.
     */
    private void updateTaskCompletionStatus(int taskNumber) throws IndexOutOfBoundsException {
        tasksList.get(taskNumber).setCompleted(true);
    }

    /**
     * Deletes the Task from the list.
     * @param taskNumber The index of the task to be deleted.
     */
    private void deleteTaskFromList(int taskNumber) throws IndexOutOfBoundsException {
        tasksList.remove(taskNumber);
    }

    /**
     * Workers class to create ToDo Tasks.
     * @param userLine Line that is inputted by the user.
     * @param option FROM_USER or FROM_FILE.
     */
    public void createToDoTask(String userLine, int option) {
        // Extract values and create ToDo Task
        try {
            String[] extractedValues = new String[3];
            String taskName, completionStatus = " ";
            if (option == FROM_USER) {
                extractedValues = parser.extractNameDateTime(userLine, TODO_TYPE);
            } else if (option == FROM_FILE) {
                extractedValues = parser.parseFileFormattedString(userLine, TODO_TYPE);
            }
            taskName = extractedValues[TASK_NAME_INDEX];
            completionStatus = extractedValues[COMPLETION_INDEX];
            Task createdTask = createTask(taskName, TODO_TYPE);
            if (Objects.equals(completionStatus, "X")) {
                createdTask.setCompleted(true);
            }
            if (taskName.isEmpty()) {
                throw new MissingVariablesException();
            }
            addTask(createdTask);
            ui.printAddTaskMessage(createdTask);
        } catch (MissingVariablesException e) {
            ui.printInvalidToDoMessage();
        }
    }

    /**
     * Worker class to create Deadline Tasks.
     * @param userLine Line that is inputted by the user.
     * @param option FROM_USER or FROM_FILE.
     */
    public void createDeadlineTask(String userLine, int option) {
        // Extract values and create Deadline Task
        try {
            String[] extractedValues = new String[3];
            String taskName, dateTime, completionStatus = " ";
            if (option == FROM_USER) {
                if (!parser.isCorrectFormat(userLine, DEADLINE_TYPE)) {
                    return;
                }
                extractedValues = parser.extractNameDateTime(userLine, DEADLINE_TYPE);
            } else if (option == FROM_FILE) {
                extractedValues = parser.parseFileFormattedString(userLine, DEADLINE_TYPE);
            }
            taskName = extractedValues[TASK_NAME_INDEX];
            dateTime = extractedValues[DATE_TIME_INDEX];
            completionStatus = extractedValues[COMPLETION_INDEX];
            if (taskName.isEmpty() || dateTime.isEmpty()) {
                throw new MissingVariablesException();
            }
            Task createdTask = createTask(taskName, dateTime, DEADLINE_TYPE);
            if (Objects.equals(completionStatus, "X")) {
                createdTask.setCompleted(true);
            }
            addTask(createdTask);
            ui.printAddTaskMessage(createdTask);
        } catch (MissingVariablesException e) {
            ui.printDateTimeMessage();
        }
    }

    /**
     * Worker class to create Event Tasks.
     * @param userLine Line that is inputted by the user.
     * @param option FROM_USER or FROM_FILE.
     */
    public void createEventTask(String userLine, int option) {
        // Extract values and create Event Task
        try {
            String[] extractedValues = new String[3];
            String taskName, dateTime, completionStatus = " ";
            if (option == FROM_USER) {
                if (!parser.isCorrectFormat(userLine, EVENT_TYPE)) {
                    return;
                }
                extractedValues = parser.extractNameDateTime(userLine, EVENT_TYPE);
            } else if (option == FROM_FILE) {
                extractedValues = parser.parseFileFormattedString(userLine, EVENT_TYPE);
            }
            taskName = extractedValues[TASK_NAME_INDEX];
            dateTime = extractedValues[DATE_TIME_INDEX];
            completionStatus = extractedValues[COMPLETION_INDEX];
            if (taskName.isEmpty() || dateTime.isEmpty()) {
                throw new MissingVariablesException();
            }
            Task createdTask = createTask(taskName, dateTime, EVENT_TYPE);
            if (Objects.equals(completionStatus, "X")) {
                createdTask.setCompleted(true);
            }
            addTask(createdTask);
            ui.printAddTaskMessage(createdTask);
        } catch (MissingVariablesException e) {
            ui.printDateTimeMessage();
        }
    }

    /**
     * Creates a new default/ToDo Task with name provided and adds it to ArrayList.
     * @param taskName The name of the task to be added.
     * @return The task object created by the user.
     */
    private static Task createTask(String taskName, String taskType) {
        Task newTask;
        if (Objects.equals(taskType, TODO_TYPE)) {
            newTask = new ToDo(taskName);
        } else {
            newTask = new Task(taskName);
        }
        return newTask;
    }

    /**
     * Creates a Deadline/Event Task with name and appropriate date time.
     * If task_type = DEADLINE_TYPE, create a Deadline object.
     * Else If task_type = EVENT_TYPE, create a Event object.
     * @param taskName The name assigned to the given task.
     * @param dateTime The date-time associated with the task.
     * @param taskType The type of task to determine the subclass to create.
     * @return The task object created by the user.
     */
    private Task createTask(String taskName, String dateTime, String taskType) {
        Task newTask = null;
        if (Objects.equals(taskType, DEADLINE_TYPE)) {
            newTask = new Deadline(taskName, dateTime);
        } else if (Objects.equals(taskType, EVENT_TYPE)) {
            newTask = new Event(taskName, dateTime);
        }
        return newTask;
    }

    /**
     * Adds a new task into the list of tasks.
     * @param newTask A task created by the user.
     */
    private void addTask(Task newTask) {
        tasksList.add(newTask);
    }

}
