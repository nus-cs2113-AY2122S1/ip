package duke.command;

import java.io.IOException;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the command to add todo
 */
public class AddTodoCommand extends Command{

    private static final int TODO_NAME_CONSTANT = 5;
    
    private String input;

    public AddTodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to add a todo to the TaskList
     *
     * @param list The tasklist instance to handle interactions with the ArrayList of task
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle interactions with the text file
     */
    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            String taskName = input.substring(TODO_NAME_CONSTANT).trim();
            list.addTodo(taskName);
            storage.appendToFile("T / 0 / " + taskName);
            list.printAddedTask();
        } catch(StringIndexOutOfBoundsException e) {
            ui.printEmptyTodoMessage();
        } catch(IOException e) {
            ui.printSomethingWentWrongMessage(e);
        }
     }

    /**
     * @return returns true if the command to exit the application is given
     */
    @Override
    public boolean isExit() {
        return false;
    }
}