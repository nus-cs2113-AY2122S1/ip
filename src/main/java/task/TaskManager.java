package task;

import console.InputParser;
import error.DukeTaskNameEmptyException;
import task.subtask.Deadline;
import task.subtask.Event;
import task.subtask.Todo;
import ui.Display;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The TaskManager class is responsible for storing and managing all the different types of tasks.
 */
public class TaskManager {

    /** Offset required to convert between 0 and 1 indexing. */
    public static final int INDEX_OFFSET = 1;

    private static ArrayList<Task> allTasks = new ArrayList<>();
    private static int taskCount;

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    /** Increases the current number of tasks by 1. */
    public void increaseTaskCount() {
        taskCount++;
    }

    /** Decreases the current number of tasks by 1. */
    public void decreaseTaskCount() {
        taskCount--;
    }

    /**
     * Adds a 'todo' type task.
     * Shows the user an acknowledgement message after a successful addition of new 'todo' type task.
     * Increases the current number of tasks in the TaskManager by 1.
     *
     * @param taskInformation Task details provided by user.
     * @throws DukeTaskNameEmptyException If task name provided is empty.
     */
    public void addTodoTask(String taskInformation) throws DukeTaskNameEmptyException {
        allTasks.add(new Todo(InputParser.getTaskName(taskInformation)));
        increaseTaskCount();
        Display.displayTaskCreation(allTasks.get(taskCount - INDEX_OFFSET), Display.TASK_NAME_TODO, taskCount);
    }

    /**
     * Adds a 'deadline' type task.
     * Shows the user an acknowledgement message after a successful addition of new 'deadline' type task.
     * Increases the current number of tasks in the TaskManager by 1.
     *
     * @param taskInformation Task details provided by user.
     * @throws DukeTaskNameEmptyException If task name provided is empty.
     * @throws DateTimeParseException If datetime provided is in the wrong format.
     * @throws IndexOutOfBoundsException If task detail provided is in the wrong format.
     */
    public void addDeadlineTask(String taskInformation) throws DukeTaskNameEmptyException, DateTimeParseException,
            IndexOutOfBoundsException {
        allTasks.add(new Deadline(InputParser.getTaskNameComponent(taskInformation),
                InputParser.getDateComponent(taskInformation),
                InputParser.getTimeComponent(taskInformation)));
        increaseTaskCount();
        Display.displayTaskCreation(allTasks.get(taskCount - INDEX_OFFSET), Display.TASK_NAME_DEADLINE, taskCount);
    }

    /**
     * Adds a 'event' type task.
     * Shows the user an acknowledgement message after a successful addition of new 'event' type task.
     * Increases the current number of tasks in the TaskManager by 1.
     *
     * @param taskInformation Task details provided by user.
     * @throws DukeTaskNameEmptyException If task name provided is empty.
     * @throws DateTimeParseException If datetime provided is the wrong format.
     * @throws IndexOutOfBoundsException If task detail provided is in the wrong format.
     */
    public void addEventTask(String taskInformation) throws DukeTaskNameEmptyException, DateTimeParseException,
            IndexOutOfBoundsException {
        allTasks.add(new Event(InputParser.getTaskNameComponent(taskInformation),
                InputParser.getDateComponent(taskInformation),
                InputParser.getTimeComponent(taskInformation)));
        increaseTaskCount();
        Display.displayTaskCreation(allTasks.get(taskCount - INDEX_OFFSET), Display.TASK_NAME_EVENT, taskCount);
    }

    /**
     * Removes a task from the TaskManager.
     * Shows the user an acknowledgement message after a successful deletion of task.
     * Decreases the current number of tasks in the TaskManager by 1.
     *
     * @param commandComponents User input in a string array form.
     * @throws IndexOutOfBoundsException If task to delete does not exist.
     * @throws NumberFormatException If task number provided is not a number.
     */
    public void deleteTask(String[] commandComponents) throws IndexOutOfBoundsException, NumberFormatException {
        int taskNumber = InputParser.getTaskNumber(commandComponents);
        Task deletedTask = allTasks.get(taskNumber);
        allTasks.remove(taskNumber);
        decreaseTaskCount();
        Display.displayTaskDeleted(deletedTask, taskCount);
    }

    /**
     * Updates the status of a task as completed.
     * Shows the user an acknowledgement message after a successful update of task.
     *
     * @param commandComponents User input in a string array form.
     * @throws IndexOutOfBoundsException If task to update status does not exist.
     * @throws NumberFormatException If task number provided is not a number.
     */
    public void markTaskAsCompleted(String[] commandComponents) throws IndexOutOfBoundsException,
            NumberFormatException {
        int taskNumber = InputParser.getTaskNumber(commandComponents);
        allTasks.get(taskNumber).setTaskCompleted();
        Display.displayTaskCompleted(allTasks.get(taskNumber).getTask());
    }

    /** Displays all the tasks found in the TaskManager. */
    public void listTask() {
        Display.printListTaskLine();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + INDEX_OFFSET + ". " + allTasks.get(i));
        }
        Display.printListTaskLine();
    }

    /**
     * Finds a list of tasks that has names which contain the keyword the user is filtering for.
     *
     * @param taskKeyword Keyword that must be present in the task names.
     */
    public void findTask(String taskKeyword) {
        Display.printListTaskLine();
        allTasks.stream()
                .filter((task) -> task.getTask().contains(taskKeyword))
                .forEach(System.out::println);
        Display.printListTaskLine();
    }

    /**
     * Checks and updates a recently added saved task completion status.
     *
     * @param isCompleted Completion status of the saved task.
     */
    public void updateSavedTaskAsCompleted(boolean isCompleted) {
        if (!isCompleted) {
            return;
        }
        allTasks.get(taskCount).setTaskCompleted();
    }

    /**
     * Updates the TaskManager size and ensure saved task loaded has the right completion status.
     *
     * @param isCompleted Completion status of the saved task.
     */
    public void updateTaskManagerAfterLoadingTask(boolean isCompleted) {
        updateSavedTaskAsCompleted(isCompleted);
        increaseTaskCount();
    }

    /**
     * Adds a 'todo' type task when loading task from storage file.
     * If task loaded from storage has a completed status, task is marked as completed.
     *
     * @param isCompleted Task completion status.
     * @param savedTaskInformation Task details that were saved.
     * @throws DukeTaskNameEmptyException If saved task does not have a name.
     */
    public void addSavedTodoTask(Boolean isCompleted, String savedTaskInformation) throws DukeTaskNameEmptyException {
        allTasks.add(new Todo(InputParser.getTaskName(savedTaskInformation)));
        updateTaskManagerAfterLoadingTask(isCompleted);
    }

    /**
     * Adds a 'deadline' type task when loading task from storage file.
     * If task loaded from storage has a completed status, task is marked as completed.
     *
     * @param isCompleted Task completion status.
     * @param savedTaskInformation Task details that were saved.
     * @throws IndexOutOfBoundsException If saved task details is in the wrong format.
     * @throws DukeTaskNameEmptyException If saved task does not have a name.
     * @throws DateTimeParseException If saved task date information is in the wrong format.
     */
    public void addSavedDeadlineTask(Boolean isCompleted, String savedTaskInformation) throws IndexOutOfBoundsException,
            DukeTaskNameEmptyException, DateTimeParseException {
        allTasks.add(new Deadline(InputParser.getTaskNameComponent(savedTaskInformation),
                InputParser.getSavedDateComponent(savedTaskInformation),
                InputParser.getSavedTimeComponent(savedTaskInformation)));
        updateTaskManagerAfterLoadingTask(isCompleted);
    }

    /**
     * Adds an 'event' type task when loading task from storage file.
     * If task loaded from storage has a completed status, task is marked as completed.
     *
     * @param isCompleted Task completion status.
     * @param savedTaskInformation Task details that were saved.
     * @throws IndexOutOfBoundsException If saved task details is in the wrong format.
     * @throws DukeTaskNameEmptyException If saved task does not have a name.
     * @throws DateTimeParseException If saved task date information is in the wrong format.
     */
    public void addSavedEventTask(Boolean isCompleted, String savedTaskInformation) throws IndexOutOfBoundsException,
            DukeTaskNameEmptyException, DateTimeParseException {
        allTasks.add(new Event(InputParser.getTaskNameComponent(savedTaskInformation),
                InputParser.getSavedDateComponent(savedTaskInformation),
                InputParser.getSavedTimeComponent(savedTaskInformation)));
        updateTaskManagerAfterLoadingTask(isCompleted);
    }
}
