
import java.util.ArrayList; // import the ArrayList class
import java.util.regex.PatternSyntaxException;

/*---------LOCAL IMPORT--------*/
import tasks.TaskType;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.Task;
import exceptions.DukeException;

public class TaskManager {
    /*----------- PROCESSING CONSTANTS ---------- */
    public static final String DEADLINE_CLAUSE = "/by";
    public static final String EVENT_CLAUSE = "/at";

    private static final int DEADLINE_DESCRIPTION_IDX = 9;
    private static final int EVENT_DESCRIPTION_IDX = 6;


    /*----------- CONSOLE LOGGING ----------- */
    private static final String ADD_TASK = "Got it. I've added this task: ";
    private static final String DONE_TASK = "Nice! I've marked this task as done: ";
    private static final String LIST_TASK = "Here are your scheduled tasks!";

    /*------------- PRIVATE VARIABLES ------------ */
    private ArrayList<Task> tasks;
    private int taskSize;


    /*------------- CONSTRUCTOR -------------- */
    public TaskManager() {
        tasks = new ArrayList<Task>();
        taskSize = 0;

    }

    /**
     * adds a Task into a variable-size array of Tasks
     * @param command input that has been parsed by CommandHandler
     * @param type the type of task to be added : Todo / Event / Deadline
     */
    public void addTask(CommandHandler command, TaskType type, boolean isDone, boolean isLogged) throws DukeException {
        Task t = new Task("");
        switch (type) {
        case TODO:
            command.splitByClause("todo",0,true);
            t = new Todo(command.descriptorAfterClause,isDone);
            break;
        case DEADLINE:
            command.splitByClause(DEADLINE_CLAUSE,DEADLINE_DESCRIPTION_IDX,false);
            t = new Deadline(command.descriptorBeforeClause, command.descriptorAfterClause, isDone);
            break;
        case EVENT:
            command.splitByClause(EVENT_CLAUSE,EVENT_DESCRIPTION_IDX,false);
            t = new Event(command.descriptorBeforeClause, command.descriptorAfterClause, isDone);
            break;
        }

        tasks.add(t);
        taskSize++;
        if (isLogged) {
            System.out.println(ADD_TASK);
            System.out.println(t);
            System.out.println("You now have (" + taskSize + ") tasks!" );
        }
    }

    public void addTask(CommandHandler command, TaskType type) throws DukeException {
        addTask(command,type,false,true);
    }

    public void addTask(CommandHandler command, TaskType type, boolean isDone) throws DukeException {
        addTask(command,type,isDone,true);
    }

    /**
     * Marks a task as done. Can be called by input
     * @param command the input after its been parsed by command handler
     * @throws DukeException if the input does not have a valid number
     */
    public void markTaskAsDone(CommandHandler command) throws DukeException {
        command.splitByClause("done",0,true);
        try {
            int idx = Integer.parseInt(command.descriptorAfterClause) - 1;
            updateTask(idx, true);
            System.out.println(DONE_TASK);
            printTask(idx);
        } catch (NumberFormatException | IndexOutOfBoundsException e){
            throw new DukeException("Please enter a valid task number to be marked as done!");
        } finally {
            listTasks();
        }
    }

    public void listTasks() {
        System.out.println(LIST_TASK);
        for (int i = 0; i < taskSize; i++) {
            System.out.printf("%d.", i + 1);
            printTask(i);
        }
    }

    public void updateTask(int idx, boolean isDone) {
        tasks.get(idx).setDone(isDone);
    }

    public void printTask(int idx) {
        System.out.println(tasks.get(idx));
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskSize(){
        return taskSize;
    }
}

