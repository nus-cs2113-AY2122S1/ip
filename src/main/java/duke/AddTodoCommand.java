package duke;

import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.io.IOException;

public class AddTodoCommand extends Command{
    public static final String COMMAND_WORD = "todo";
    private String description;
    
    public AddTodoCommand(String description) {
        this.description = description; 
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        Todo toAdd = new Todo(description, false);
        tasks.addTask(toAdd);
        ui.acknowledgeAddCommand(toAdd, tasks.getNumberOfTasks());
    }
}
