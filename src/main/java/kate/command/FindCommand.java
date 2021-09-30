package kate.command;

import kate.common.Message;
import kate.exception.EmptyFieldException;
import kate.parser.Parser;
import kate.storage.Storage;
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
            ArrayList<String> filteredTask = tasks.filterTasksByKeyword(keyword);
            boolean isEmptyFilteredTask = filteredTask.isEmpty();
            boolean isEmptyTaskList = tasks.isEmptyTaskList();

            if (isEmptyTaskList) {
                ui.printEmptyTaskMessage();
                return;
            }

            if (isEmptyFilteredTask) {
                ui.printEmptyFilteredTaskMessage(keyword);
                return;
            }

            ui.printTasksByKeyword(filteredTask, keyword);
        } catch (EmptyFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_FIND);
        }
    }
}
