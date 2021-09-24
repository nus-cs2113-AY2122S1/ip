package commands;

import ui.Ui;
import storage.Storage;
import tasklist.TaskList;
import tasks.Task;

public class RemoveTask extends Command {
    protected Ui ui = new Ui();
    private final String input;

    public RemoveTask(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks) {
        try {
            int index = Integer.parseInt(input.substring(7));
            //given task does not exist in list
            if (index > tasks.getListSize()) {
                ui.showNoSuchTaskMessage();
            } else {
                Task thisTask = tasks.getTaskFromList(index - 1);
                ui.showDeleteTaskMessage(thisTask, (tasks.getListSize() - 1));
                tasks.removeTaskToList(thisTask);
                Storage.updateDatabase();
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showNothingToRemoveMessage();
        } catch (NumberFormatException e) {
            ui.showRemoveCommandErrorMessage();
        }
    }
}
