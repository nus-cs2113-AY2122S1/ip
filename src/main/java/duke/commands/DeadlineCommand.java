package duke.commands;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;
import duke.ui.TextUi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline task to the task list. "
            + "Parameters: TASK /by DEADLINE"
            + "\n|| "
            + "Example: " + COMMAND_WORD
            + " Do CS2113T iP /by 2021-10-01\n||";

    protected String description;
    protected String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        try {
            createDeadlineWithLocalDateTask(tasks, storage);
        } catch (DateTimeParseException e) {
            createDeadlineWithStringTask(tasks,storage);
        }
        ui.showSuccessfulAdd(tasks);
    }

    private void createDeadlineWithLocalDateTask(TaskList tasks, Storage storage) {
        LocalDate d1 = LocalDate.parse(by,DateTimeFormatter.ISO_DATE);
        String date = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        tasks.addTask(new Deadline(description,d1));
        storage.appendDeadlineToFile(description,date);
    }

    private void createDeadlineWithStringTask(TaskList tasks, Storage storage) {
        tasks.addTask(new Deadline(description, by));
        storage.appendDeadlineToFile(description, by);
    }

}