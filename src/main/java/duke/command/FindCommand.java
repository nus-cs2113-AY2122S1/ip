package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Represents the command to find tasks based on a keyword
 */
public class FindCommand extends Command{
    
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Executes the command to find tasks based on a keyword
     *
     * @param list The tasklist instance to handle interactions with the ArrayList of task
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle interactions with the text file
     */
    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        boolean isFound = false;
        Task task;
        for (int i = 0; i < list.size(); i += 1) {
            task = list.getList().get(i);
            if (task.getName().toLowerCase().contains(keyword)) {
                if (!isFound) {
                    ui.printFoundTaskMessage();
                    isFound = true;
                }
                ui.printTask(task, i+1);
            }
        }
        if (!isFound) {
            ui.printNoTaskFoundMessage();
        } else {
            ui.printLine();
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