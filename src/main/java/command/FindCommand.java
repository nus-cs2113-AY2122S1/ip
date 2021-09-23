package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.UI;
import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyword;

    /**
     * Constructor that initialises the keyword entered by the user as the keyword property for the command.
     *
     * @param keyword the search term entered by the user
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Searches for the keyword in the complete list of tasks stored by taro and prints out the matching items to the
     * output.
     *
     * @param taskList the TaskList type object used to handle internal task related operations
     * @param ui the UI type object used to handle i/o for taro
     * @param storage the object used for handling read/write operations to the taro.txt data file
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.printMatchingTasks(matchingTasks, matchingTasks.size());
    }
}
