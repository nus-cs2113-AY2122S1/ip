package duke.task;

import duke.exception.ArgumentNotFoundException;
import duke.exception.InvalidCommandException;
import duke.parser.Parser;
import duke.ui.Ui;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {

    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";

    private static final String TASK_NUMBER_ERROR = "Invalid task number!";
    private static final String TASK_ADD_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_ALREADY_COMPLETED = "Task already marked as completed!";
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String TASK_REMOVE_MESSAGE = "Noted. I've removed this task:";
    private static final String NO_TASK_FOUND_ERROR = "No matching task has been found";

    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Check if the index is valid.
     *
     * @param index index in the taskList
     * @return if the index is valid
     */
    private boolean ensureProperIndex(int index) {
        return index >= 0 && index < taskList.size();
    }

    /**
     * Marks a task as done. Informs user that that task has been completed.
     *
     * @param ui    the ui instance to handle output
     * @param index the index of task in the taskList
     */
    public void markTaskAsDone(Ui ui, int index) {

        if (ensureProperIndex(index)) {
            Task currentTask = taskList.get(index);

            // Check if the task is already completed
            if (currentTask.getIsDone()) {
                ui.printMessage(TASK_ALREADY_COMPLETED);
            } else {
                // Mark a task as done
                currentTask.markAsDone();
                ui.printMessage(TASK_DONE_MESSAGE, currentTask.toString());
            }
        } else {
            ui.printMessage(TASK_NUMBER_ERROR);
        }
    }

    /**
     * Gives the specific task in the taskList This function does not check for valid index use the ensureProperIndex
     * function
     *
     * @param index gets the specific object in the taskList
     * @return Task object is returned
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Gives how many task is in the taskList
     *
     * @return the number of tasks in taskList
     */
    public int getNumberOfTasks() {
        return taskList.size();
    }

    /**
     * Creates a new task with a specific type
     *
     * @param description the commandline input and its arguments
     * @throws ArrayIndexOutOfBoundsException in the case of invalid input by Events or Deadlines
     */
    public void addTask(Ui ui, Parser description)
            throws ArgumentNotFoundException, InvalidCommandException {
        // Creates new task to add to the list
        Task newTask;
        switch (description.getCommand()) {
        case COMMAND_TODO:
            String todoDescription = description.getArguments();
            newTask = new Todo(todoDescription);
            break;
        case COMMAND_DEADLINE:
            String deadlineDescription = description.getArgsBeforeString(DEADLINE_DELIMITER);
            String deadlineBy = description.getArgsAfterString(DEADLINE_DELIMITER);
            newTask = new Deadline(deadlineDescription, deadlineBy);
            break;
        case COMMAND_EVENT:
            String eventDescription = description.getArgsBeforeString(EVENT_DELIMITER);
            String eventAt = description.getArgsAfterString(EVENT_DELIMITER);
            newTask = new Event(eventDescription, eventAt);
            break;
        default:
            throw new InvalidCommandException();
        }
        // Adding the task to the list of task
        taskList.add(newTask);
        ui.printMessage(TASK_ADD_MESSAGE,
                newTask.toString(),
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Add a Task object to the taskList
     *
     * @param task the Task object to add
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes an existing task
     *
     * @param ui    the ui instance to handle output
     * @param index the index of the task to delete
     */
    public void deleteTask(Ui ui, int index) {
        if (!ensureProperIndex(index)) {
            ui.printMessage(TASK_NUMBER_ERROR);
            return;
        }
        Task removedTask = taskList.get(index);
        taskList.remove(index);
        ui.printMessage(TASK_REMOVE_MESSAGE,
                removedTask.toString(),
                "Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Prints the user the list of all task If it is empty prints the empty message instead.
     *
     * @param ui the Ui Instance to format the message to the user
     */
    public void listTasks(Ui ui) {
        if (taskList.size() == 0) {
            ui.printEmptyTaskMessage();
            return;
        }
        ui.printAllTasks(this);
    }

    /**
     * finds the all task with the searchTerm and print them
     *
     * @param ui         the Ui instance to format and print messages
     * @param searchTerm the String to find in the tasks description
     */
    public void findTasks(Ui ui, String searchTerm) {
        ArrayList<Task> foundTasks = (ArrayList<Task>) taskList
                .stream()
                .filter((i) -> i.getDescription().contains(searchTerm))
                .collect(Collectors.toList());
        if (foundTasks.size() <= 0) {
            ui.printMessage(NO_TASK_FOUND_ERROR);
            return;
        }
        ui.printFoundTask(foundTasks);

    }
}

