package duke.command;

import java.io.IOException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class AddTodoCommand extends Command{

    private static final int TODO_NAME_CONSTANT = 5;
    
    private String input;

    public AddTodoCommand(String input) {
        this.input = input;
    }

    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            String taskName = input.substring(TODO_NAME_CONSTANT);
            list.addTodo(taskName);
            storage.appendToFile("T / 0 / " + taskName);
            list.printAddedTask();
        } catch(StringIndexOutOfBoundsException e) {
            ui.printEmptyTodoMessage();
        } catch(IOException e) {
            ui.printSomethingWentWrongMessage(e);
        }
     }

    @Override
    public boolean isExit() {
        return false;
    }
}