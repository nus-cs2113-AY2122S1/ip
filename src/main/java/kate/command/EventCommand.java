package kate.command;

import kate.common.Message;
import kate.exception.EmptyFieldException;
import kate.parser.Parser;
import kate.storage.Storage;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

public class EventCommand extends Command {

    private static final String FAILURE_MESSAGE_ADD_EVENT = Message.TEXT_INDENTATION
            + "Please specify a task with:\n"
            + Message.TEXT_INDENTATION + "\"" + Message.COMMAND_EVENT + "\"\n";

    public EventCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(KateUI ui, Storage storage, TaskList tasks) {
        try {
            String[] infoArr = Parser.extractEventInput(userInput);
            String taskDescription = infoArr[0];
            String timeFrame = infoArr[1];

            tasks.addEvent(taskDescription, timeFrame);
            ui.printAddedTask(tasks.getMostRecentAddedTask(), tasks.getTaskSize());
            storage.appendTaskToFile(ui, tasks.getMostRecentAddedTask());
        } catch (EmptyFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_ADD_EVENT);
        }
    }
}
