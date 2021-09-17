package kate.command;

import kate.common.Message;
import kate.exception.EmptyFieldException;
import kate.parser.Parser;
import kate.storage.Storage;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

public class ToDoCommand extends Command {

    private static final String FAILURE_MESSAGE_ADD_TODO = Message.TEXT_INDENTATION
            + "Please specify a task with:\n"
            + Message.TEXT_INDENTATION + "\"" + Message.COMMAND_TODO + "\"\n";

    public ToDoCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(KateUI ui, Storage storage, TaskList tasks) {
        try {
            String taskDescription = Parser.extractToDoInput(userInput);
            tasks.addToDo(taskDescription);
            ui.printAddedTask(tasks.getMostRecentAddedTask(), tasks.getTaskSize());
            storage.appendTaskToFile(ui, tasks.getMostRecentAddedTask());
        } catch (EmptyFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_ADD_TODO);
        }
    }
}
