package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Extracts and prints event tasks happening today and deadline tasks due today
 */
public class AgendaCommand extends Command {
    public static final String COMMAND_KEYWORD = "agenda";

    /**
     * Executes AgendaCommand by printing out the tasks that matches
     * with the keyword.
     *
     * @param tasks The task list which stores the task
     * @param ui In charge of informing the user that the command has been executed
     * @param storage In charge of reading and writing to data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        LocalDate today = LocalDate.now();
        ArrayList<Task> todayTasks = (ArrayList<Task>) tasks.getTasks().stream()
                .filter((task) -> task.getDT().toLocalDate().equals(today))
                .sorted(Comparator.comparing(Task::getDT))
                .collect(Collectors.toList());
        ui.printAgenda(todayTasks);
    }
}