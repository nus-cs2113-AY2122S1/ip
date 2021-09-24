package commands;

import data.Storage;
import data.TaskList;
import task.Event;
import ui.TextUI;

import static common.Message.ADDED_TASK;
import static common.Error.ERROR_FORMAT_EVENT;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "/event";
    protected String args;
    protected String description;
    protected String start;
    protected String end;

    public EventCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TextUI ui, TaskList tasks, Storage data) {
        try {
            description = args.substring(7, args.indexOf("-from")).strip();
            start = args.substring(args.indexOf("-from") + 6, args.indexOf("-to")).strip();
            end = args.substring(args.indexOf("-to") + 4).strip();

            Event newEvent = new Event(description, start, end, false);
            tasks.addTask(newEvent);

            String userOutput = String.format(ADDED_TASK, newEvent, tasks.getSize());
            ui.showMessage(userOutput);
            data.write(tasks.getTaskList());
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMessage(ERROR_FORMAT_EVENT);
        }
    }
}
