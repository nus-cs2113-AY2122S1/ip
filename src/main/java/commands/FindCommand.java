package commands;

import processors.TaskList;
import processors.UI;
import tasks.Task;
import java.util.ArrayList;

public class FindCommand extends Command{
    private static final Integer FIND_DIVIDER = 5;
    public UI ui = new UI();

    /**
     * Function attempts to search through all return all the related task that
     * exists in the list
     * @param taskList the list of tasks
     * @param line the input line from the user
     */
    public void execute(TaskList taskList, String line) {
        String search = line.substring(FIND_DIVIDER).toLowerCase();
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : taskList.taskList) {
            if (t.getDescription().toLowerCase().contains(search)) {
                result.add(t);
            }
        }
        ui.printFindMessage(result);
    }
}
