package commands;

import data.Storage;
import data.TaskList;
import task.Deadline;
import ui.TextUI;

import java.time.format.DateTimeParseException;

import static common.Error.INVALID_DATETIME_FORMAT;
import static common.Message.ADDED_TASK;
import static common.Error.ERROR_FORMAT_DEADLINE;

/**
 * Represents the /deadline command
 * This command creates a new Deadline task
 * COMMAND_WORD represents exact string user should provide to invoke this command
 */
public class DeadlineCommand extends Command{
    public static final String COMMAND_WORD = "/deadline";
    protected String args;
    protected String description;
    protected String by;

    /**
     * Sole constructor
     * @param args Additional arguments supplied by user after COMMAND_WORD
     */
    public DeadlineCommand(String args) {
        this.args = args;
    }

    /**
     * Splits arguments supplied by user after COMMAND_WORD into description
     * and when deadline is due
     * Creates new Deadline object and adds it to tasks object and writes changes
     * into data.txt, displaying success messages when complete. Error messages are
     * also output if command is malformed or invalid date format had been input
     * Overrides method from parent class
     * @param ui Object that handles user IO
     * @param tasks Object that stores current and updated list of tasks
     * @param data Object that handles storage, read and writes to data.txt
     */
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
