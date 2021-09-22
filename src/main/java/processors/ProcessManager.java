package processors;

import commands.DeleteCommand;
import commands.EventCommand;
import commands.TodoCommand;
import commands.DeadlineCommand;
import commands.DoneCommand;
import commands.ListCommand;
import commands.InvalidCommand;
import commands.SavedCommand;
import commands.Command;
import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DoneException;
import exceptions.DeadlineException;
import exceptions.DeleteException;
import exceptions.DukeException;

import java.io.IOException;

public class ProcessManager {
    public DeadlineCommand deadlineCommand = new DeadlineCommand();
    public EventCommand eventCommand = new EventCommand();
    public TodoCommand todoCommand = new TodoCommand();
    public DeleteCommand deleteCommand = new DeleteCommand();
    public DoneCommand doneCommand = new DoneCommand();
    public ListCommand listCommand = new ListCommand();
    public InvalidCommand invalidCommand = new InvalidCommand();
    public SavedCommand savedCommand = new SavedCommand();

    public Ui ui = new Ui();

    public Boolean executeCommand(Command command, TaskList taskList, String line) {
        if (command instanceof DeadlineCommand) {
            try {
                deadlineCommand.execute(taskList, line);
            } catch (DeadlineException e) {
                ui.printDeadlineException(e);
            }
            return true;
        } else if (command instanceof EventCommand) {
            try {
                eventCommand.execute(taskList, line);
            } catch (EventException e) {
                ui.printEventException(e);
            }
            return true;
        } else if (command instanceof TodoCommand) {
            try {
                todoCommand.execute(taskList, line);
            } catch (TodoException e) {
                ui.printTodoException(e);
            }
            return true;
        } else if (command instanceof DeleteCommand) {
            try {
                deleteCommand.execute(taskList, line);
            } catch (DeleteException e) {
                ui.printDeleteException(e);
            }
            return true;
        } else if (command instanceof DoneCommand) {
            try {
                doneCommand.execute(taskList, line);
            } catch (DoneException e) {
                ui.printDoneException(e);
            }
            return true;
        } else if (command instanceof ListCommand) {
            listCommand.execute(taskList);
        } else if (command instanceof InvalidCommand) {
            try {
                invalidCommand.execute();
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
            return true;
        } else if (command instanceof SavedCommand) {
            try {
                savedCommand.execute(taskList);
            } catch (IOException e) {
                ui.printIOException(e);
            }
            return false;
        }
        return true;
    }
}
