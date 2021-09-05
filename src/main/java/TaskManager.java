import java.util.ArrayList;

public class TaskManager {

    public static final String ERROR_TASK_NUMBER = "Invalid task number!";
    public static final String BLANK_COMMAND = "Command Should not be blank!";
    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    public static final String TASK_ALREADY_COMPLETED = "Task already marked as completed!";
    public static final String DEADLINE_DELIMITER = "/by";
    public static final String EVENT_DELIMITER = "/at";
    public static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";

    private ArrayList<Task> taskList;

    public TaskManager() {
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
            ui.printMessage(ERROR_TASK_NUMBER);
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
     * @param type        the type of object task we are creating
     * @throws ArrayIndexOutOfBoundsException in the case of invalid input by Events or Deadlines
     */
    public void addTask(Ui ui, Parser description, TaskType type) throws ArgumentNotFoundException {
        // Creates new task to add to the list
        Task newTask;
        switch (type) {
        case TODO:
            newTask = new Todo(description.getArguments());
            break;
        case DEADLINE:
            newTask = new Deadline(description.getArgsBeforeString(DEADLINE_DELIMITER),
                    description.getArgsAfterString(DEADLINE_DELIMITER));
            break;
        case EVENT:
            newTask = new Event(description.getArgsBeforeString(EVENT_DELIMITER),
                    description.getArgsAfterString(EVENT_DELIMITER));
            break;
        default:
            // Error Message should not be reachable
            ui.printMessage(BLANK_COMMAND);
            return;
        }
        // Adding the task to the list of task
        taskList.add(newTask);
        ui.printMessage(ADD_TASK_MESSAGE,
                newTask.toString(),
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
        } else {
            ui.printAllTasks(this);
        }
    }
}

