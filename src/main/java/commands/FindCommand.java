package commands;

import task.Task;
import tasklist.TaskList;

public class FindCommand extends Command{

    private final String input;
    private static final String LIST_CONTAINING_STRING_MESSAGE = "Here are the matching tasks in your list : ";
    private static final String NO_SIMILAR_WORDS = "There are no matching tasks :(";

    public FindCommand(String input) {
        this.input = input;
    }
    public CommandResult execute() {
        int totalTasks = 0;
        TaskList taskContainingString = new TaskList();
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            if(tasks.getTask(i).getDescription().contains(input)){
                taskContainingString.addTaskForSpecificCases(tasks.getTask(i));
                totalTasks++;
            }
        }
        taskContainingString.setNumberOfTask(totalTasks);
        if(totalTasks == 0) {
            return new CommandResult(NO_SIMILAR_WORDS,PrintOptions.DEFAULT);
        }
        return new CommandResult(LIST_CONTAINING_STRING_MESSAGE,
                taskContainingString,PrintOptions.LIST_WITH_SPECIFIC_CONDITIONS);
    }
}
