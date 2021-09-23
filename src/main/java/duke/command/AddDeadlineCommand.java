package duke.command;

import java.io.IOException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class AddDeadlineCommand extends Command{

    private static final int DEADLINE_NAME_CONSTANT = 9;
    private static final int DEADLINE_BY_CONSTANT = 5;
    
    private String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            if (!input.contains("/by")) {
                ui.printWrongDeadlineFormatMessage();
                return;
            }
            int taskEndIndex = input.indexOf("/by") - 1;
            String taskName = input.substring(DEADLINE_NAME_CONSTANT, taskEndIndex);
            String deadline = input.substring(taskEndIndex + DEADLINE_BY_CONSTANT);
            list.addDeadline(taskName, deadline);
            storage.appendToFile("D / 0 / " + taskName + " / " + deadline);
            list.printAddedTask();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printEmptyDeadlineMessage();
        } catch(IOException e) {
            ui.printSomethingWentWrongMessage(e);
        }
     }

    @Override
    public boolean isExit() {
        return false;
    }
}