package kate.command;

import kate.common.Message;
import kate.exception.EmptyFieldException;
import kate.exception.EmptyTaskException;
import kate.exception.InvalidFieldException;
import kate.parser.Parser;
import kate.storage.Storage;
import kate.task.Task;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

public class DoneCommand extends Command {

    private static final String FAILURE_MESSAGE_SET_DONE = Message.TEXT_INDENTATION
            + "Please specify a task with:\n"
            + Message.TEXT_INDENTATION + "\"" + Message.COMMAND_DONE + "\"\n";

    public DoneCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public void execute(KateUI ui, Storage storage, TaskList tasks) {
        try {
            Task curTask = Parser.extractDoneInput(tasks, userInput);
            curTask.setDone();

            String doneMessage = Message.TEXT_INDENTATION + "Nice! I've marked this task as done:\n"
                    + Message.TEXT_INDENTATION + "  " + curTask.getTaskInfo() + "\n";
            ui.printMessage(doneMessage);
            storage.updateTasksToFile(ui, tasks);
        } catch (EmptyFieldException | InvalidFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_SET_DONE);
        } catch (EmptyTaskException e) {
            ui.printMessage(Message.FAILURE_MESSAGE_EMPTY_TASK);
        }
    }
}
