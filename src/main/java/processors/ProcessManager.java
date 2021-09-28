package processors;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.SavedCommand;
import commands.TodoCommand;
import commands.UncheckCommand;
import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DoneException;
import exceptions.DeadlineException;
import exceptions.DeleteException;
import exceptions.DukeException;
import exceptions.UncheckException;

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
    public FindCommand findCommand = new FindCommand();
    public ByeCommand byeCommand = new ByeCommand();
    public HelpCommand helpCommand = new HelpCommand();
    public UncheckCommand uncheckCommand = new UncheckCommand();

    public UI ui = new UI();

    /**
     * Function takes in a specific command and executes that Command
     * @param command the input Command that will be executed
     * @param taskList the current list of tasks in the programme
     * @param line the input string used to pass into the command that will be executed
     * @return true if the command is not a ByeCommand, otherwise it returns true
     */
    public Boolean executeCommand(Command command, TaskList taskList, String line) {
        if (command instanceof DeadlineCommand) {
            try {
                deadlineCommand.execute(taskList, line);
            } catch (DeadlineException e) {
                ui.printDeadlineException(e);
            }
        } else if (command instanceof EventCommand) {
            try {
                eventCommand.execute(taskList, line);
            } catch (EventException e) {
                ui.printEventException(e);
            }
        } else if (command instanceof TodoCommand) {
            try {
                todoCommand.execute(taskList, line);
            } catch (TodoException e) {
                ui.printTodoException(e);
            }
        } else if (command instanceof DeleteCommand) {
            try {
                deleteCommand.execute(taskList, line);
            } catch (DeleteException e) {
                ui.printDeleteException(e);
            }
        } else if (command instanceof DoneCommand) {
            try {
                doneCommand.execute(taskList, line);
            } catch (DoneException e) {
                ui.printDoneException(e);
            }
        } else if (command instanceof ListCommand) {
            listCommand.execute(taskList);
        } else if (command instanceof InvalidCommand) {
            try {
                invalidCommand.execute();
            } catch (DukeException e) {
                ui.printDukeException(e);
            }
        } else if (command instanceof SavedCommand) {
            try {
                savedCommand.execute(taskList);
            } catch (IOException e) {
                ui.printIOException(e);
            }
        } else if (command instanceof FindCommand) {
            findCommand.execute(taskList, line);
        } else if (command instanceof ByeCommand) {
            try {
                byeCommand.execute(taskList);
            } catch (IOException e) {
                ui.printIOException(e);
            }
            return false;
        } else if (command instanceof HelpCommand) {
            helpCommand.execute();
        } else if (command instanceof UncheckCommand) {
            try {
                uncheckCommand.execute(taskList, line);
            } catch (UncheckException e) {
                ui.printUncheckException(e);
            }
        }
        return true;
    }
}
