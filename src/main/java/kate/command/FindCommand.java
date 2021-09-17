package kate.command;

import kate.common.Message;
import kate.exception.EmptyFieldException;
import kate.parser.Parser;
import kate.storage.Storage;
import kate.task.Task;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

import java.util.ArrayList;

public class FindCommand extends Command {

    private static final String FAILURE_MESSAGE_FIND = Message.TEXT_INDENTATION
            + "Please enter a keyword!\n"
            + Message.TEXT_INDENTATION + "\"" + Message.COMMAND_FIND + "\"\n";

    public FindCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(KateUI ui, Storage storage, TaskList tasks) {
        try {
            String keyword = Parser.extractKeyword(userInput);
            ArrayList<Task> filteredTask = tasks.findTasksByKeyword(keyword);
            StringBuilder compiledTasks = new StringBuilder();
            for (Task task : filteredTask) {
                compiledTasks.append(Message.TEXT_INDENTATION).append(task.getTaskInfo()).append("\n");
            }
            ui.printMessage(String.valueOf(compiledTasks));
        } catch (EmptyFieldException e) {
            ui.printMessage(Message.FAILURE_MESSAGE_EMPTY_TASK);
        }
    }
}
