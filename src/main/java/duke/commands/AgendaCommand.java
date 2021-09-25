package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Shows the user all the events/deadlines that are at/due today
 */
public class AgendaCommand extends Command {
    public static final String COMMAND_WORD = "agenda";

    /**
     * Executes AgendaCommand by printing all events/deadline that equals LocalDate.now()
     *
     * @param tasks   the TaskList that contains all current tasks
     * @param storage the storage object responsible for writing to the data file
     * @param ui      the ui object responsible for acknowledging to user that the command has been executed
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        LocalDate today = LocalDate.now();
        ArrayList<Task> todayDeadlineEvents = (ArrayList<Task>) tasks.getTasks().stream()
                .filter(t -> t.getDT() != null && t.getDT().toLocalDate().equals(today))
                .sorted(Comparator.comparing(Task::getDT))
                .collect(Collectors.toList());
        ui.showAgenda(todayDeadlineEvents);
    }
}
