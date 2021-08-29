import java.util.ArrayList;

public class TaskManager {

    private final String DEADLINE_DELIMITER = "/by";
    private final String EVENT_DELIMITER = "/at";

    private ArrayList<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    /**
     * Marks a task as done. Informs user that that task has been completed.
     *
     * @param index the index of task in the taskList
     */
    public void markTaskAsDone(int index) {

        if (index >= 0 && index < taskList.size()) {
            Task currentTask = taskList.get(index);

            // Check if the task is already completed
            if (currentTask.getIsDone()) {
                UI.printMessage("Task already marked as completed!");
            } else {
                // Mark a task as done
                currentTask.markAsDone();
                UI.printMessage("Nice! I've marked this task as done:", currentTask.toString());
            }
        } else {
            UI.printMessage("Invalid task number!");
        }
    }

    /**
     * List all task added by the user Show which task has been completed
     */
    public void listTasks() {

        UI.printLine();
        // Check if there are any tasks
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list");
        } else {
            System.out.println("Here are your tasks in your list:");
            // Printing all tasks with their completion status
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d. %s\n",
                        (i + 1),
                        taskList.get(i));
            }
        }
        UI.printLine();
    }

    /**
     * Creates a new task with a specific type
     *
     * @param description the commandline input and its arguments
     * @param type the type of object task we are creating
     * @throws ArrayIndexOutOfBoundsException in the case of invalid input by Events or Deadlines
     */
    public void addTask(Parser description, TaskType type) throws ArrayIndexOutOfBoundsException {
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
            UI.printMessage("Input should not be blank!");
            return;
        }
        // Adding the task to the list of task
        taskList.add(newTask);

        UI.printMessage("Got it. I've added this task:",
                newTask.toString(),
                "Now you have " + taskList.size() + " tasks in the list.");
    }
}

