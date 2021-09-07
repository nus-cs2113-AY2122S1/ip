package command;

import task.Task;
import task.TaskManager;
import ui.UI;

public class AddCommand extends Command {
    private String taskType;
    private String taskName;
    private String date;

    /**
     * Constructor to create an add command with a type, task name and either a "by" or "at" date
     *
     * @param taskType either "todo", "deadline" or "event"
     * @param taskName the description of the task without the date
     * @param date the "by" or "at" date
     */
    public AddCommand(String taskType, String taskName, String date) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.date = date;
    }

    /**
     * Constructor to create an add command only for todo type tasks, which initialises an empty "by" or "at" date
     *
     * @param taskType the type of task created, "todo"
     * @param taskName the description of the task
     */
    public AddCommand(String taskType, String taskName) {
        this(taskType, taskName, "");
    }

    /**
     * Utilises the TaskManager object to add a task to the full list of tasks on taro. Specifics of the task type
     * based on todo, deadline and event are handled by the TaskManager object.
     *
     * @param taskManager the TaskManager type object used to handle internal task related operations
     * @param ui the UI type object used to handle i/o for taro
     */
    @Override
    public void execute(TaskManager taskManager, UI ui) {
        Task newTask = taskManager.addTask(taskType, taskName, date);
        ui.printTaskAddedMessage(newTask, taskManager.getTaskCount());
    }
}
