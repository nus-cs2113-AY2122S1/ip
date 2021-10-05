package duke.control.command;

import duke.control.InvalidInputFormatException;
import duke.control.Storage;
import duke.control.TaskList;
import duke.control.Ui;

public class TaskCommand extends Command {

    @Override
    public void executeCommand(TaskList list, String input, Storage storage) {
        try {
            list.addEntryToList(input);
            storage.saveData(list);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("todo, deadline or event commands must have task descriptions");
        } catch (InvalidInputFormatException e) {
            Ui.printMissingDateTimeMessage();
        }
    }
}
