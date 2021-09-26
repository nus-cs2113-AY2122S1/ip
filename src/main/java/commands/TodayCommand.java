package commands;

import data.Storage;
import data.TaskList;
import task.Deadline;
import task.Event;
import task.Task;
import ui.TextUI;

import java.time.LocalDate;

import static common.Message.MESSAGE_SEPARATOR;

/**
 * Represents the /today command
 * This command lists all Event and Deadline tasks that are occurring today
 * COMMAND_WORD represents exact string user should provide to invoke this command
 */
public class TodayCommand extends Command {
    public static final String COMMAND_WORD = "/today";
    protected static LocalDate today = LocalDate.now();

    /**
     * Sole constructor, no args
     */
    public TodayCommand() {}

    /**
     * Upon execution, iterate through all tasks present within tasks container,
     * checking for Events and Deadlines. For each event if starting date is
     * today, print string representation of the event which includes type, status,
     * description and starting and ending time of Event. For each deadline if due
     * date is today, print string representation of the event which includes type,
     * status, description and due date of the task.
     * Overrides method from parent class.
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        ui.showMessage(MESSAGE_SEPARATOR);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currentTask = tasks.getTask(i);
            if (currentTask instanceof Deadline && ((Deadline) currentTask).compareDate(today)) {
                ui.showMessage(currentTask.toString());
            }
            if (currentTask instanceof Event && ((Event) currentTask).compareDate(today)) {
                ui.showMessage(currentTask.toString());
            }
        }
        ui.showMessage(MESSAGE_SEPARATOR);
    }
}
