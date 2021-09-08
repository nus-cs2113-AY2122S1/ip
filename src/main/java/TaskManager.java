import java.util.ArrayList;

public class TaskManager {

    /* Types of tasks */
    public static final char CHAR_TYPE_TODO = 'T';
    public static final char CHAR_TYPE_DEADLINE = 'D';
    public static final char CHAR_TYPE_EVENT = 'E';

    /* Types of string split regex  */
    public static final String EVENT_STRING_SPLIT_REGEX = "/at ";
    public static final String DEADLINE_STRING_SPLIT_REGEX = "/by ";

    /* Error messages */
    private static final String DEADLINE_ERROR_MESSAGE = "Sorry please input a valid DEADLINE TASK with date and time "
            + "by using <description> /by <date and time>";
    private static final String EVENT_ERROR_MESSAGE = "Sorry please input a EVENT TASK with date and time by using "
            + "<description> /at <date time>";
    private static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = "Please do not input an empty description.\n"
            + "Input a TODO/DEADLINE/EVENT TASK with a valid description by using\n"
            + "TODO <description>\n"
            + "DEADLINE <description> /by <date and time>\n"
            + "EVENT <description> /at <date and time>";
    private static final String TASK_DOES_NOT_EXIST_MESSAGE = "Sorry, task selected does not exist! Please double "
            + "check if task number exist with the list command.";
    private static final String TASK_NUMBER_NOT_VALID_MESSAGE = "Error in detecting task number. Please enter a valid"
            + " number after done as such DONE <TASK NUMBER>, i.e: DONE 1";
    private static final String TASK_NUMBER_MISSING_MESSAGE = "Task number was not given. Please give me a task "
            + "number as such DONE <TASK NUMBER>, i.e: DONE 1";

    /* List of tasks */
    private final ArrayList<Task> tasksList;

    /* Constructor for task manager */
    public TaskManager() {
        tasksList = new ArrayList<Task>();
        Duke.printMessage("Gaben's Task Manager is here to assist you!");
    }

    /**
     * Creates a new task based on type given
     *
     * @param userParameter Description of task and its parameter given by user
     * @param type         type of task
     * @return return task
     * @throws InvalidParameterException If deadline/event task do not have the corresponding regax such as /by and
     * /at or when no description is given after the regax
     */
    public Task createTask(String userParameter, char type) throws InvalidParameterException{
        Task task = null;
        String[] userArguments = null;
        boolean regexNotFound = false;
        boolean emptyDescription = false;
        switch (type) {
        case CHAR_TYPE_TODO:
            task = new Todo(userParameter);
            break;
        case CHAR_TYPE_EVENT:
            userArguments = userParameter.split(EVENT_STRING_SPLIT_REGEX, 2);
            regexNotFound = userArguments.length != 2;
            emptyDescription = userArguments[0].isBlank();
            if (emptyDescription || regexNotFound) {
                throw new InvalidParameterException(EVENT_ERROR_MESSAGE);
            }
            task = new Event(userArguments[0], userArguments[1]);
            break;
        case CHAR_TYPE_DEADLINE:
            userArguments = userParameter.split(DEADLINE_STRING_SPLIT_REGEX, 2);
            regexNotFound = userArguments.length != 2;
            emptyDescription = userArguments[0].isBlank();
            if (emptyDescription || regexNotFound) {
                throw new InvalidParameterException(DEADLINE_ERROR_MESSAGE);
            }
            task = new Deadline(userArguments[0], userArguments[1]);
            break;
        }
        return task;
    }


    /**
     * Adds a newly created task with given task description and type to tasks list
     *
     * @param userInputArray Command and parameters given by user
     * @param type         Type of task given by user input
     * @throws InvalidParameterException If missing description or description with only spaces
     */
    public void addTask(String[] userInputArray, TaskType type) throws InvalidParameterException{

        // Check for empty description
        try{
            boolean emptyDescription = userInputArray[1].isBlank();
            if (emptyDescription) {
                throw new InvalidParameterException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            }
        } catch (IndexOutOfBoundsException e){
            throw new InvalidParameterException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
        }

        // Create task
        Task task = null;
        switch (type) {
        case TODO:
            task = createTask(userInputArray[1], CHAR_TYPE_TODO);
            break;
        case EVENT:
            task = createTask(userInputArray[1], CHAR_TYPE_EVENT);
            break;
        case DEADLINE:
            task = createTask(userInputArray[1], CHAR_TYPE_DEADLINE);
            break;
        }

        // As long as task is valid, add to task list and inform user
        if (task != null) {
            tasksList.add(task);
            Duke.printMessage(String.format("Gaben have seen and will add the following task for you:\n" +
                    "%s\n" + "You now have %d task in the list", task, tasksList.size()));
        }
    }

    /**
     * Prints the entire list of tasks currently in tasks list. Will let user know if there is no task in tasks list.
     */
    public void listTask() {
        int tasksListSize = tasksList.size();
        String message = "";
        switch (tasksListSize) {
        case 0:
            message = "Oh! You have no tasks left!";
            break;
        default:
            message = "Total of " + tasksListSize + " task(s)\n";
            int counter = 1;
            for (Task task : tasksList) {
                message += String.format("%d.%s\n", counter, task.toString());
                counter++;
            }
            break;
        }
        Duke.printMessage(message);
    }

    /**
     * Set the task to be completed by marking it done.
     *
     * @param userInputArray Command and parameters given by user
     * @throws InvalidParameterException If invalid number is given or when task index is out of range
     */
    public void completeTask(String[] userInputArray) throws InvalidParameterException{
        // Initialized task number index to match task array list
        try {
            int taskNumber = Integer.parseInt(userInputArray[1]);
            int taskNumberIndex = taskNumber - 1;
            boolean isWithinSizeLimit = taskNumber < 1 || taskNumber > tasksList.size();
            if (isWithinSizeLimit) {
                throw new InvalidParameterException(TASK_DOES_NOT_EXIST_MESSAGE);
            } else {
                Task task = tasksList.get(taskNumberIndex);
                task.markAsDone();
                Duke.printMessage("Good lad, you have finally completed the task you needed to do.\n" + task.toString());
            }
        } catch (NumberFormatException e){
            throw new InvalidParameterException(TASK_NUMBER_NOT_VALID_MESSAGE);
        } catch (IndexOutOfBoundsException e){
            throw new InvalidParameterException(TASK_NUMBER_MISSING_MESSAGE);
        }

    }

}
