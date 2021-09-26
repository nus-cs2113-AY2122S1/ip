package commands;

import data.Storage;
import data.TaskList;
import task.Event;
import ui.TextUI;

import java.time.format.DateTimeParseException;

import static common.Error.INVALID_DATETIME_FORMAT;
import static common.Message.ADDED_TASK;
import static common.Error.ERROR_FORMAT_EVENT;

/**
 * Represents the /event command
 * This command creates a new Event task
 * COMMAND_WORD represents exact string user should provide to invoke this command
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "/event";
    protected String args;
    protected String description;
    protected String start;
    protected String end;

    /**
     * Sole constructor
     * @param args Additional arguments supplied by user after COMMAND_WORD
     */
    public EventCommand(String args) {
        this.args = args;
    }

    /**
     * Splits arguments supplied by user after COMMAND_WORD into description,
     * starting and ending times of the event.
     * Creates new Event object and adds it to the tasks container, write changes
     * into data.txt, displaying success messages when complete. Error messages are
     * also output if command is malformed or invalid date format had been supplied
     * by user.
     * Overrides method from parent class
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
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
        } catch (DateTimeParseException e) {
            ui.showMessage(INVALID_DATETIME_FORMAT);
        }
    }
}
