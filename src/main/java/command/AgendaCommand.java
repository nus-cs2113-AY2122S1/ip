package command;

import exception.AustinException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AgendaCommand extends Command {
    public static final String COMMAND_KEYWORD = "agenda";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AustinException, IOException {
        LocalDate today = LocalDate.now();
        ArrayList<Task> todayTasks = (ArrayList<Task>) tasks.getTasks().stream()
                .filter((task) -> task.getDT().toLocalDate().equals(today))
                .sorted(Comparator.comparing(Task::getDT))
                .collect(Collectors.toList());
        ui.printAgenda(todayTasks);
    }
}