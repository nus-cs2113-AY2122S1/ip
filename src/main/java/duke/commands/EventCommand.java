package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final int END_OF_EVENT_INDEX = 5;
    public static final int AT_LENGTH = 3;

    public EventCommand(String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            handleEventTask(tasks, ui, storage);
            ui.showTaskConfirmation(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMissingAtError();
        }
    }

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
