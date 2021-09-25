package commands;

import tasklist.TaskList;
import tasks.Task;
import ui.Ui;
import java.util.ArrayList;
import java.util.List;

public class FindTaskCommand extends Command {
    protected Ui ui = new Ui();
    private final String input;

    public FindTaskCommand(String input) {
        this.input = input.toLowerCase();
    }
    @Override
    public void execute(TaskList tasks) {
        if (input.trim().length() <= 4) {
            ui.showNoKeywordSpecifiedMessage();
        } else {
            String keyword = input.substring(5);
            List<Integer> foundTasksIndices = new ArrayList<>();
            int index = 1;
            for (Task task : tasks.getTaskList()) {
                if (task.getDescription().toLowerCase().contains(keyword)) {
                    foundTasksIndices.add(index);
                }
                index++;
            }
            if (foundTasksIndices.size() <= 0) {
                ui.showTaskNotFoundMessage();
            } else {
                ui.showFoundTaskMessage();
                for (int taskIndex : foundTasksIndices) {
                    System.out.println(taskIndex + "." + tasks.getTaskFromList(taskIndex - 1));
                }
                ui.showLines();
            }
        }
    }
}
