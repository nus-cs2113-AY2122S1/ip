package duke.program;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.TaskIndexOutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {

    private static final String SEPARATOR_BY = "/by";
    private static final String SEPARATOR_AT = "/at";
    private static final String ICON_DONE = "X";

    private static ArrayList<Task> tasks;
    private static int taskCount;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    /**
     * Gets the latest task on the list.
     * @return the latest task object.
     */
    public Task getLatestTask() {
        return tasks.get(taskCount-1);
    }

    public int getTaskCount() {
        return taskCount;
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Adds a new todo task to the task list.
     * @param rawDescription String containing the task description.
     * @throws EmptyDescriptionException when description is left empty.
     */
    public void addNewTodo(String rawDescription)
            throws EmptyDescriptionException {
        String description = rawDescription.trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        tasks.add(new ToDo(description));
        taskCount++;
    }

    /**
     * Adds a new deadline task to the task list.
     * @param descriptionAndTime String containing the task description and the due date/time.
     * @throws EmptyDescriptionException when description is left empty.
     * @throws InvalidFormatException when due date/time is not written in the specified format.
     */
    public void addNewDeadline(String descriptionAndTime)
            throws EmptyDescriptionException, InvalidFormatException {
        String[] descriptionAndByTimeArray = descriptionAndTime.split(SEPARATOR_BY);
        String description = descriptionAndByTimeArray[0].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (descriptionAndByTimeArray.length == 1) {
            throw new InvalidFormatException();
        }

        String byDateTime = descriptionAndByTimeArray[1].trim();

        tasks.add(new Deadline(description, byDateTime));
        taskCount++;
    }

    /**
     * Adds a new event task to the task list.
     * @param descriptionAndTime String containing the task description, the start date/time and end date/time.
     * @throws EmptyDescriptionException when description is left empty.
     * @throws InvalidFormatException when due date/time is not written in the specified format.
     */
    public void addNewEvent(String descriptionAndTime)
            throws EmptyDescriptionException, InvalidFormatException {
        String[] descriptionAndAtTimeArray = descriptionAndTime.split(SEPARATOR_AT);
        String description = descriptionAndAtTimeArray[0].trim();

        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (descriptionAndAtTimeArray.length == 1) {
            throw new InvalidFormatException();
        }

        String startAndEndTime = descriptionAndAtTimeArray[1].trim();

        tasks.add(new Event(description, startAndEndTime));
        taskCount++;
    }

    /**
     * Marks a task as done on the task list.
     * @param tasks ArrayList of task objects in current task list.
     * @param lineArgs parsed array of line arguments. Contains done command and done index number.
     * @param ui ui object to print the marked as done notice message.
     * @throws InvalidIndexException when 2nd line argument cannot be parsed as integer.
     * @throws TaskIndexOutOfBoundsException when task index provided is out of range of the task list.
     */
    public void markAsDone(ArrayList<Task> tasks, String[] lineArgs, LizUi ui)
            throws InvalidIndexException, TaskIndexOutOfBoundsException {
        if (lineArgs.length > 2) {
            throw new InvalidIndexException();
        }

        int doneIndex = Integer.parseInt(lineArgs[1]) - 1;

        if (doneIndex >= taskCount || doneIndex < 0) {
            throw new TaskIndexOutOfBoundsException();
        } else {
            if (tasks.get(doneIndex).getStatusIcon().equals(ICON_DONE)) {
                ui.printAlreadyDoneMessage();
            } else {
                tasks.get(doneIndex).setDone();
                ui.printMarkAsDoneMessage(tasks.get(doneIndex), taskCount);
            }
        }
    }

    /**
     * removes a task off the task list.
     * @param tasks ArrayList of task objects in current task list.
     * @param lineArgs parsed array of line arguments. Contains delete command and delete index number.
     * @param ui ui object to print the deleted task notice message.
     * @throws InvalidIndexException when 2nd line argument cannot be parsed as integer.
     * @throws TaskIndexOutOfBoundsException when task index provided is out of range of the task list.
     */
    public void deleteTask(ArrayList<Task> tasks, String[] lineArgs, LizUi ui)
            throws InvalidIndexException, TaskIndexOutOfBoundsException {
        if (lineArgs.length > 2) {
            throw new InvalidIndexException();
        }

        int deleteIndex = Integer.parseInt(lineArgs[1]) - 1;

        if (deleteIndex >= taskCount || deleteIndex < 0) {
            throw new TaskIndexOutOfBoundsException();
        } else {

            //taskCount - 1 is passed as the taskCount parameter for printDeletedTaskMessage since
            //print method is called first before removing
            ui.printDeletedTaskMessage(tasks.get(deleteIndex), taskCount - 1);
            tasks.remove(deleteIndex);
        }

        taskCount--;
    }

    /**
     * Finds all tasks in the task list that contain the given keyword, and outputs an array containing
     * all matching tasks.
     * @param tasks ArrayList of task objects in current task list.
     * @param lineArgs parsed array of line arguments. Contains find command and keyword string.
     * @return ArrayList of tasks that match the keyword.
     * @throws EmptyDescriptionException when no keyword is entered after find command.
     */
    public ArrayList<Task> findMatchingTasks(TaskList tasks, String[] lineArgs)
            throws EmptyDescriptionException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String keyword = lineArgs[1];

        for (Task task : tasks.getTaskList()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
