package duke.command;

import duke.task.TaskDoneList;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    public String order;
    public FindCommand(String order) {
        this.order = order;
    }

    /**
     * Find a task in the list
     *
     * @param list The list of all tasks
     * @param doneList The list of all tasks which have been finished
     * @param ui The ui that is used
     */
    @Override
    public void executeCommand(TaskList list, TaskDoneList doneList, Ui ui) {
        String keyWords = this.order.substring(4);
        ui.printSplitLine();
        int j = 1;
        System.out.println("    Here are the tasks found in the list:");
        for (int i = 0; i < list.listSize(); i++) {
            if (list.getTask(i).description.contains(keyWords)) {
                System.out.println(j + "." + list.getTask(i));
                j++;
            }
        }

        if(j == 1) {
            System.out.println("    There is no task in the list matching the keyword(s)");
        }
        ui.printSplitLine();
    }
}
