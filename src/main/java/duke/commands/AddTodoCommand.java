package duke.commands;

import duke.storage.DataStorage;
import duke.task.Todo;
import duke.task.TaskList;


public class AddTodoCommand extends Command {

    private final Todo toAdd;

    public AddTodoCommand(Todo toAdd) {
        this.toAdd = toAdd;
    }

    /**
     * Executes the addition of a {@code Todo} to {@code taskList} by passing the user input to it.
     * An error message is printed if the user input lacks the description of the {@code Todo}
     *
     * @param taskList {@code TaskList} where the todo is to be added to
     * @param dataStorage {@code DataStorage} which saves the new {@code Todo} to Duke's storage
     */
    @Override
    public void execute(TaskList taskList, DataStorage dataStorage) {
        taskList.addTask(toAdd);
        dataStorage.saveData(taskList);
    }
}
