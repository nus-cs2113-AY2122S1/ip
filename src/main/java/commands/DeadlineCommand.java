package commands;

import data.Storage;
import data.TaskList;
import task.Deadline;
import ui.TextUI;

import java.time.format.DateTimeParseException;

import static common.Error.INVALID_DATETIME_FORMAT;
import static common.Message.ADDED_TASK;
import static common.Error.ERROR_FORMAT_DEADLINE;

public class DeadlineCommand extends Command{
    public static final String COMMAND_WORD = "/deadline";
    protected String args;
    protected String description;
    protected String by;

    public DeadlineCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        try {
            description = args.substring(10, args.indexOf("-by")).strip();
            by = args.substring(args.indexOf("-by") + 4).strip();

            Deadline newDeadline = new Deadline(description, by, false);
            tasks.addTask(newDeadline);

            String userOutput = String.format(ADDED_TASK, newDeadline, tasks.getSize());
            ui.showMessage(userOutput);
            data.write(tasks.getTaskList());
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMessage(ERROR_FORMAT_DEADLINE);
        } catch (DateTimeParseException e) {
            ui.showMessage(INVALID_DATETIME_FORMAT);
        }
    }
}
