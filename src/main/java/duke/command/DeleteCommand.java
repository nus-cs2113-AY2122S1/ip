package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the command to delete tasks from the TaskList
 */
public class DeleteCommand extends Command{

    private static final int DELETE_SUBSTRING_INDEX = 7;
    
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to delete a task from the TaskList
     *
     * @param list The tasklist instance to handle interactions with the ArrayList of task
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle interactions with the text file
     */
    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            int taskIndex = Integer.parseInt(input.substring(DELETE_SUBSTRING_INDEX).trim()) - 1;
            if (taskIndex < list.size() && taskIndex >= 0) {
                ui.printRemoveTaskMessge(list.getList().get(taskIndex), list.size()-1);     
                list.deleteTask(taskIndex);
                storage.updateFile();
            } else {
                ui.printTaskDoesNotExistMessage();
            }
        } catch (StringIndexOutOfBoundsException e) {
            ui.printWrongCommandFormatMessage();
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