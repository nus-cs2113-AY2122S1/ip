
import java.util.List;

public class CommandResult extends Command{
    public final String feedback;

    /** The list of persons that was produced by the command */
    public final Task relevantTask;

    public final int size;

    public final List<Task> relevantTasks;

    public CommandResult(String feedback) {
        this.feedback = feedback;
        relevantTask = null;
        size = 0;
        relevantTasks = null;
    }
    public CommandResult(String feedback, Task relevantTask) {
        this.feedback = feedback;
        this.relevantTask = relevantTask;
        size = 0;
        relevantTasks = null;
    }
    public CommandResult(String feedback, Task relevantTask, int totalNumber) {
        this.feedback = feedback;
        this.relevantTask = relevantTask;
        this.size = totalNumber;
        this.relevantTasks = null;
    }
    public CommandResult(String feedback, List<Task> tasksList, int totalNumber) {
        this.feedback = feedback;
        this.relevantTask = null;
        this.size = totalNumber;
        this.relevantTasks = tasksList;
    }
    public CommandResult(String feedback, List<Task> tasksList) {
        this.feedback = feedback;
        this.relevantTask = null;
        size = 0;
        this.relevantTasks = tasksList;
    }
}
