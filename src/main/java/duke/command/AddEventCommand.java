package duke.command;

import java.io.IOException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class AddEventCommand extends Command{

    private static final int EVENT_NAME_CONSTANT = 6;
    private static final int EVENT_AT_CONSTANT = 5;
    
    private String input;

    public AddEventCommand(String input) {
        this.input = input;
    }

    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            if (!input.contains("/at")) {
                ui.printWrongEventFormatMessage();
                return;
            }
            int taskEndIndex = input.indexOf("/at") - 1;
            String taskName = input.substring(EVENT_NAME_CONSTANT, taskEndIndex);
            String at = input.substring(taskEndIndex + EVENT_AT_CONSTANT);
            list.addEvent(taskName, at);
            storage.appendToFile("E / 0 / " + taskName + " / " + at);
            list.printAddedTask();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmptyEventMessage();
        } catch(IOException e) {
            ui.printSomethingWentWrongMessage(e);
        }
     }

    @Override
    public boolean isExit() {
        return false;
    }
}