package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

public class DeadlineCommand extends Command {
    public static final int END_OF_DEADLINE_INDEX = 8;
    public static final int BY_LENGTH = 3;

    public DeadlineCommand (String fullCommand) {
        this.isExit = false;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            handleDeadlineTask(tasks, ui, storage);
            ui.showTaskConfirmation(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            ui.showMissingByError();
        }
    }

    private void handleDeadlineTask(TaskList tasks, Ui ui, Storage storage) {
        int endOfDescriptionIndex = fullCommand.indexOf("/by");
        int startOfByIndex = fullCommand.indexOf("/by") + BY_LENGTH;
        String description = fullCommand.substring(END_OF_DEADLINE_INDEX, endOfDescriptionIndex).trim();
        String byText = fullCommand.substring(startOfByIndex).trim();
        String memWritableText = formatForMemory(description, byText, tasks.getTasks().size());
        tasks.getTasks().add(new Deadline(description, byText));
        storage.appendToMem(memWritableText);
    }

    private static String formatForMemory(String description, String byText, int size) {
        if (size == 0) {
            return "D~0~" + description + "~" + byText;
        } else {
            return System.lineSeparator() + "D~0~" + description + "~" + byText;
        }
    }
}
