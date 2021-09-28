package command;

import parser.Parser;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.UI;

import java.io.IOException;

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
     * Utilises the TaskList object to add a task to the full list of tasks on taro. Specifics of the task type
     * based on todo, deadline and event are handled by the TaskList object. Uses the Storage object to write the new
     * task information to the taro.txt data file.
     *
     * @param taskList the TaskList type object used to handle internal task related operations
     * @param ui the UI type object used to handle i/o for taro
     * @param storage the object used for handling read/write operations to the taro.txt data file
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        try {
            Task newTask = taskList.addTask(taskType, taskName, date);
            if (taskType.equals(Parser.COMMAND_TODO)) {
                storage.writeAddTask(taskName);
            } else if (taskType.equals(Parser.COMMAND_DEADLINE) | taskType.equals(Parser.COMMAND_EVENT)) {
                storage.writeAddTask(taskType, taskName, date);
            }
            ui.printTaskAddedMessage(newTask, taskList.getTaskCount());
        } catch (IOException e) {
            ui.printString("there was an issue writing your new task to saved data");
        }
    }
}
