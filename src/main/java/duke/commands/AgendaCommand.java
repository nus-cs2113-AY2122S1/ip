package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AgendaCommand extends Command {
    public static final String COMMAND_WORD = "agenda";
    
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        //TODO change this to LocalDate.now() in final release
        LocalDate today = LocalDate.of(2021,9,20);
        ArrayList<Task> todayDeadlineEvents = (ArrayList<Task>) tasks.getTasks().stream()
                .filter(t -> t.getDT() != null && t.getDT().toLocalDate().equals(today))
                .sorted(Comparator.comparing(Task::getDT))
                .collect(Collectors.toList());
        ui.showAgenda(todayDeadlineEvents);
    }
}
