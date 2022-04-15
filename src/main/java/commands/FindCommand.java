package commands;

import tasks.Task;
import tasks.TaskList;

import ui.TextUi;

public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_DESC = "Finds all tasks that contain the keyword in the description";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds task containing the given keyword
     */
    public void execute() {
        if (keyword.equals("")) {
            TextUi.showMissingKeyWordMessage();
        } else {
            TaskList tasksFound = getTasksWithDescriptionContainingKeyword(keyword);
            TextUi.showTasksFound(tasksFound, keyword);
        }
    }

    private TaskList getTasksWithDescriptionContainingKeyword(String keyword) {
        TaskList matchedTasks = new TaskList();
        for (Task task: taskList.getTaskList()) {
            String taskDescription = task.getDescription().toLowerCase();
            if (taskDescription.contains(keyword)) {
                matchedTasks.addTask(task);
            }
        }
        return matchedTasks;
    }
}
