import java.util.ArrayList;

public class TaskManager {

    /* List of tasks */
    private final ArrayList<Task> tasksList;
    /* Types of tasks */
    public static final char CHAR_TYPE_TODO = 'T';
    public static final char CHAR_TYPE_DEADLINE = 'D';
    public static final char CHAR_TYPE_EVENT = 'E';
    /* Error messages */
    private static final String DEADLINE_ERROR_MESSAGE = "Sorry please input a valid DEADLINE TASK with date and time "
            + "by using <description> /by <date and time>";
    private static final String EVENT_ERROR_MESSAGE = "Sorry please input a EVENT TASK with date and time by using "
            + "<description> /at <date time>";
    private static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = "Please do not input an empty description.\n"
            + "Input a TODO/DEADLINE/EVENT TASK with a valid description by using\n"
            +"TODO <description>\n"
            +"DEADLINE <description> /by <date and time>\n"
            +"EVENT <description> /at <date and time>";
    /* Types of string split regex  */
    public static final String EVENT_STRING_SPLIT_REGEX = "/at ";
    public static final String DEADLINE_STRING_SPLIT_REGEX = "/by ";

    /* Constructor for task manager */
    public TaskManager() {
        tasksList = new ArrayList<Task>();
        Duke.printMessage("Gaben's Task Manager is here to assist you!");
    }

    /**
     * Creates a new task based on type given
     *
     * @param userArgument Name of task given by user input
     * @param type type of task
     * @return return task
     */
    public Task createTask(String userArgument, char type) {
        Task task = null;
        String[] userArguments = null;
        boolean regexNotFound = false;
        boolean emptyDescription = false;
        switch (type) {
        case CHAR_TYPE_TODO:
            task = new Todo(userArgument);
            break;
        case CHAR_TYPE_EVENT:
            userArguments = userArgument.split(EVENT_STRING_SPLIT_REGEX,2);
            regexNotFound = userArguments.length != 2;
            emptyDescription = userArguments[0].isBlank();
            if (emptyDescription || regexNotFound){
                Duke.printMessage(EVENT_ERROR_MESSAGE);
                return null;
            }
            task = new Event(userArguments[0], userArguments[1]);
            break;
        case CHAR_TYPE_DEADLINE:
            userArguments = userArgument.split(DEADLINE_STRING_SPLIT_REGEX,2);
            regexNotFound = userArguments.length != 2;
            emptyDescription = userArguments[0].isBlank();
            if (emptyDescription || regexNotFound){
                Duke.printMessage(DEADLINE_ERROR_MESSAGE);
                return null;
            }
            task = new Deadline(userArguments[0], userArguments[1]);
            break;
        }
        return task;
    }


    /**
     * Adds a newly created task with given task description and type to tasks list
     *
     * @param userArgument Description of task given by user input
     * @param type Type of task given by user input
     */
    public void addTask(String userArgument, TaskType type) {
        Task task = null;
        // Check for empty description
        boolean emptyDescription = userArgument == null;
        if(emptyDescription) {
            Duke.printMessage(EMPTY_DESCRIPTION_ERROR_MESSAGE);
            return;
        }
        switch (type){
        case TODO:
            task = createTask(userArgument, CHAR_TYPE_TODO);
            break;
        case EVENT:
            task = createTask(userArgument, CHAR_TYPE_EVENT);
            break;
        case DEADLINE:
            task = createTask(userArgument, CHAR_TYPE_DEADLINE);
            break;
        }
        // As long as task is valid, add to task list and inform user
        if (task != null){
            tasksList.add(task);
            Duke.printMessage(String.format("Gaben have seen and will add the following task for you:\n"+
                            "%s\n"+"You now have %d task in the list", task.toString(), tasksList.size()));
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
            for(Task task : tasksList){
                message += String.format("%d.%s\n",counter,task.toString());
                counter++;
            }
            break;
        }
        Duke.printMessage(message);
    }

    /**
     * Set the task to be completed by marking it done.
     *
     * @param taskNumber The task number as shown by list command.
     */
    public void completeTask(int taskNumber) {
        // Initialized task number index to match task array list
        int taskNumberIndex = taskNumber - 1;
        boolean isWithinSizeLimit = taskNumber < 1 || taskNumber > tasksList.size();
        if (isWithinSizeLimit) {
            Duke.printMessage("Sorry, task selected does not exist! Please double check if task number exist with the"
                    + " list command.");
        } else {
            Task task = tasksList.get(taskNumberIndex);
            task.markAsDone();
            String message = "Good lad, you have finally completed the task you needed to do.\n";
            message += task.toString();
            Duke.printMessage(message);
        }
    }

}
