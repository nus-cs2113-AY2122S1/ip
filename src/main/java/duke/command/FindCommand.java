package duke.command;

import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

public class FindCommand {
    /**
     * Find list of tasks with matching words
     *
     * @param taskList list of task
     * @param userInput user input
     */
    public FindCommand(TaskList taskList, String userInput) {
        try {
            String keyword = userInput.split(" ")[1];
            ArrayList<Task> foundTasks = (ArrayList<Task>) taskList.getTasks().stream()
                    .filter(t -> t.getDescription().toLowerCase(Locale.ROOT).contains(keyword))
                    .collect(Collectors.toList());

            boolean noMatch = foundTasks.isEmpty();
            if (noMatch) {
                Ui.printNoMatchTask();
                return;
            }

            System.out.println("Here are the matching tasks in your list:");
            Ui.printTasks(foundTasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter keyword of task to search");
        }
    }
}
