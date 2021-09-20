package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String query; 
    
    public FindCommand(String query) {
        this.query = query;
    }
    
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ArrayList<Task> results = (ArrayList<Task>) tasks.getTasks().stream()
                .filter(t -> t.getTask().contains(query))
                .collect(Collectors.toList());
        ui.showSearchResults(results);
    }
}
