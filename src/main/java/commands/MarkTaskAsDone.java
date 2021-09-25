package commands;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;


public class MarkTaskAsDone extends Command {
    protected Ui ui = new Ui();
    private final String input;

    public MarkTaskAsDone(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks) {
        try {
            //isolate 'x' from 'done x', where x is a number
            int index = Integer.parseInt(input.substring(5));
            int taskCount = tasks.getListSize();
            //task given is not in list
            if (index > taskCount) {
                ui.showNoSuchTaskMessage();
            } else if (tasks.getTaskFromList(index - 1).getStatusIcon().equals("X")) {
                ui.showTaskAlreadyDoneMessage();
            } else {
                tasks.getTaskFromList(index - 1).markAsDone();
                ui.showTaskCompleteMessage(tasks.getTaskFromList(index - 1));
                Storage.updateDatabase();
            }
        } catch (StringIndexOutOfBoundsException e) {
            ui.showNoTaskSpecifiedMessage();
        } catch (NumberFormatException e) {
            ui.showDoneCommandErrorMessage();
        }
    }
}
