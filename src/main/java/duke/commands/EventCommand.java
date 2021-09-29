package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.time.LocalDateTime;

/**
 * Command Class that executes the creation of a new Event task.
 */
public class EventCommand extends Command {
    public static final int END_OF_EVENT_INDEX = 5;
    public static final int AT_LENGTH = 3;

    /**
     * Initializes new EventCommand object.
     * @param fullCommand full user input as a string
     */
    public EventCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    /**
     * Executes creation of new Event task with description and time as inputted by user.
     * @param tasks TaskList object of all tasks in the programme
     * @param ui Ui object for calling Ui methods
     * @param storage Storage object for writing to memory
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            handleEventTask(tasks, ui, storage);
            ui.showTaskConfirmation(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMissingAtError();
        }
    }

    /**
     * Parses command input by user and adds new Event Task to {@code tasks}.
     * @param tasks TaskList object containing all tasks as an ArrayList
     * @param ui Ui object for calling of ui methods
     * @param storage Storage object for writing to memory
     */
    private void handleEventTask(TaskList tasks, Ui ui, Storage storage) {
        int endOfDescriptionIndex = fullCommand.indexOf("/at");
        int startOfAtIndex = fullCommand.indexOf("/at") + AT_LENGTH;
        String description = fullCommand.substring(END_OF_EVENT_INDEX, endOfDescriptionIndex).trim();
        String atText = fullCommand.substring(startOfAtIndex).trim();
        String memWritableText;
        try {
            String[] atDateTimeArray = atText.split(" ");
            LocalDateTime atDateTime = LocalDateTime.parse(atDateTimeArray[0] + "T" + atDateTimeArray[1]);
            String byDateAsString = ui.dateTimeToString(atDateTime);
            memWritableText = formatForMemory(description, byDateAsString, tasks.getTasks().size(), atDateTime);
            tasks.getTasks().add(new Event(description, byDateAsString, atDateTime));
        } catch (Exception e) {
            memWritableText = formatForMemory(description, atText, tasks.getTasks().size());
            tasks.getTasks().add(new Event(description, atText));
        }
        storage.appendToMem(memWritableText);
    }

    /**
     * Writes user input into format for storage in the memory file.
     * If size == 0, line separator is not included at the front of the string.
     * @param description description of task from user input
     * @param atText time of task from user input
     * @param size size of ArrayList of tasks
     * @return formatted string for writing to memory
     */
    private String formatForMemory(String description, String atText, int size) {
        if (size == 0) {
            return "E~0~" + description + "~" + atText;
        } else {
            return System.lineSeparator() + "E~0~" + description + "~" + atText;
        }
    }

    private static String formatForMemory(String description, String atText, int size, LocalDateTime atDateTime) {
        if (size == 0) {
            return "E~0~" + description + "~" + atText + "~" + atDateTime;
        } else {
            return System.lineSeparator() + "E~0~" + description + "~" + atText + "~" + atDateTime;
        }
    }
}
