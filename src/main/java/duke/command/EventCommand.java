package duke.command;

import duke.data.TaskList;
import duke.ui.Ui;

/**
 * An event command is a simplified version of the add command
 * A <code>Event</code> command can be called with the prefix 'event' in Duke.
 */
public class EventCommand extends Command {
    public EventCommand() {
        super(CommandPrefix.EVENT);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
    }

    @Override
    public void execute(TaskList tasks) {
        readLineAndAddEvent(tasks);
        saveListAndPrintDone(tasks);
    }

    private void readLineAndAddEvent(TaskList tasks) {
        String userInput = Ui.readLine();
        tasks.addTaskCheckDate(userInput);
    }
}
