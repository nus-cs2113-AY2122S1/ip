package duke.command;

import java.io.IOException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the command to add event
 */
public class AddEventCommand extends Command{

    private static final int EVENT_NAME_CONSTANT = 6;
    private static final int EVENT_AT_CONSTANT = 5;
    
    private String input;

    public AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add an event to the TaskList
     *
     * @param list The tasklist instance to handle interactions with the ArrayList of task
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle interactions with the text file
     */
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

    /**
     * @return returns true if the command to exit the application is given
     */
    @Override
    public boolean isExit() {
        return false;
    }
}