package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Command Class that executes the creation of a new Deadline task.
 */
public class DeadlineCommand extends Command {
    public static final int END_OF_DEADLINE_INDEX = 8;
    public static final int BY_LENGTH = 3;

    /**
     * Initializes new DeadlineCommand object.
     * @param fullCommand full user input as a string
     */
    public DeadlineCommand (String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    /**
     * Executes creation of new Event task with description and completion deadline as inputted by user.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            handleDeadlineTask(tasks, ui, storage);
            ui.showTaskConfirmation(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMissingByError();
        }
    }

    /**
     * Parses command input by user and adds new Deadline Task to {@code tasks}.
     * @param tasks TaskList object containing all tasks as an ArrayList
     * @param ui Ui object for calling of ui methods
     * @param storage Storage object for writing to memory
     */
    private void handleDeadlineTask(TaskList tasks, Ui ui, Storage storage) {
        int endOfDescriptionIndex = fullCommand.indexOf("/by");
        int startOfByIndex = fullCommand.indexOf("/by") + BY_LENGTH;
        String description = fullCommand.substring(END_OF_DEADLINE_INDEX, endOfDescriptionIndex).trim();
        String byText = fullCommand.substring(startOfByIndex).trim();

        String memWritableText;
        try {
            String[] byDateTimeArray = byText.split(" ");
            LocalDateTime byDateTime = LocalDateTime.parse(byDateTimeArray[0] + "T" + byDateTimeArray[1]);
            String byDateAsString = ui.dateTimeToString(byDateTime);
            memWritableText = formatForMemory(description, byDateAsString, tasks.getTasks().size(), byDateTime);
            tasks.getTasks().add(new Deadline(description, byDateAsString, byDateTime));
        } catch (Exception e) {
            memWritableText = formatForMemory(description, byText, tasks.getTasks().size());
            tasks.getTasks().add(new Deadline(description, byText));
        }
        storage.appendToMem(memWritableText);
    }

    /**
     * Writes user input into format for storage in the memory file.
     * If size == 0, line separator is not included at the front of the string.
     * @param description description of task from user input
     * @param byText completion deadline of task from user input
     * @param size size of ArrayList of tasks
     * @return formatted string for writing to memory
     */
    private static String formatForMemory(String description, String byText, int size) {
        if (size == 0) {
            return "D~0~" + description + "~" + byText;
        } else {
            return System.lineSeparator() + "D~0~" + description + "~" + byText;
        }
    }

    private static String formatForMemory(String description, String byText, int size, LocalDateTime byDateTime) {
        if (size == 0) {
            return "D~0~" + description + "~" + byText + "~" + byDateTime;
        } else {
            return System.lineSeparator() + "D~0~" + description + "~" + byText + "~" + byDateTime;
        }
    }
}
