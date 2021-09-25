package commands;

import data.Storage;
import data.TaskList;
import task.Deadline;
import task.Event;
import task.Task;
import ui.TextUI;

import java.time.LocalDate;

import static common.Message.MESSAGE_SEPARATOR;

public class TodayCommand extends Command {
    public static final String COMMAND_WORD = "/today";
    protected static LocalDate today = LocalDate.now();

    public TodayCommand() {}

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
