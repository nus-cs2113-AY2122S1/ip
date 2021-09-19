package duke.task;

import duke.exception.DukeException;
import duke.exception.ExceptionMessages;
import duke.Ui;

import java.util.ArrayList;

/**
 * Task list containing a list of different types of tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs an instance of the <code>TaskList</code> object with an empty ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @throws DukeException If no description given.
     */
    public void addTask(Task task) throws DukeException {
        if (task.getDescription().equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_DESCRIPTION);
        }
        taskList.add(task);
        final int listLength = taskList.size();
        //To check that task was added successfully
        final Task mostRecentTask = taskList.get(listLength - 1);
        Ui.printAddTaskMessage(listLength, mostRecentTask);
    }

    /**
     * Adds a task to the task list without printing a message.
     *
     * @param task Task to be added.
     * @throws DukeException If no description given.
     */
    public void addTaskWithoutMessage(Task task) throws DukeException {
        if (task.getDescription().equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_DESCRIPTION);
        }
        taskList.add(task);
    }


    /**
     * Deletes a task by taking in the task number.
     *
     * @param rawTaskNumber Number used to identify the task to delete.
     * @throws DukeException If task number is invalid or if no task number is provided.
     * @throws NumberFormatException If task number provided is not parsable into an integer.
     */
    public void deleteTask(String rawTaskNumber) throws DukeException, NumberFormatException {
        if (rawTaskNumber.equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_TASK_NUMBER);
        }
        int taskNumber = Integer.parseInt(rawTaskNumber.trim());
        if (isMissingTask(taskNumber)) {
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_TASK_NUMBER);
        }
        deleteExistingTask(taskNumber);
    }

    private void deleteExistingTask(int taskNumber) {
        final int taskIndex = taskNumber - 1;
        Ui.printDeleteTaskSuccessMessage(taskList.get(taskIndex));
        taskList.remove(taskIndex);
    }


    /**
     * Marks a task as done.
     *
     * @param rawTaskNumber Number to identify the task to be marked.
     * @throws DukeException If task number is invalid or if no task number is provided.
     * @throws NumberFormatException If task number provided is not parsable into an integer.
     */
    public void markTaskAsDone(String rawTaskNumber) throws DukeException, NumberFormatException {
        if (rawTaskNumber.equals("")) {
            throw new DukeException(ExceptionMessages.EXCEPTION_NO_TASK_NUMBER);
        }
        int taskNumber = Integer.parseInt(rawTaskNumber.trim());
        if (isMissingTask(taskNumber)) {
            throw new DukeException(ExceptionMessages.EXCEPTION_INVALID_TASK_NUMBER);
        }
        markExistingTaskAsDone(taskList.get(taskNumber - 1));
    }

    private boolean isMissingTask(int taskNumber) {
        final int listLength = taskList.size();
        return taskNumber <= 0 || taskNumber > listLength;
    }

    private void markExistingTaskAsDone(Task task) {
        task.markTaskAsDone();
        Ui.printMarkTaskSuccessMessage(task);
    }

    /**
     * Lists and prints all the tasks in the task list.
     */
    public void listAllTasks() {
        Ui.printAllTasks(taskList);
    }

    /**
     * Converts the task list into a string to be saved as a text file.
     *
     * @return String representing the task list to be saved as a text file.
     */
    public String toFile() {
        StringBuilder fileContent = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task taskToConvert = taskList.get(i);
            if (taskToConvert instanceof Todo) {
                fileContent.append("T|").append(taskToConvert.isDone ? '1' : '0').append('|').append(taskToConvert.description);
            } else if (taskToConvert instanceof Deadline) {
                fileContent.append("D|").append(taskToConvert.isDone ? '1' : '0').append('|').append(taskToConvert.description)
                        .append('|').append(((Deadline) taskToConvert).getBy());
            } else if (taskToConvert instanceof Event) {
                fileContent.append("E|").append(taskToConvert.isDone ? '1' : '0').append('|').append(taskToConvert.description)
                        .append('|').append(((Event) taskToConvert).getAt());
            }
            //skip iteration for last task
            if (i == taskList.size() - 1) {
                continue;
            }
            fileContent.append(System.lineSeparator());
        }
        return fileContent.toString();
    }
}
