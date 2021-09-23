package karen.parser;

import karen.exception.IncorrectDescriptionFormatException;
import karen.exception.NoDescriptionException;
import karen.command.Command;
import karen.program.ProgramManager;
import karen.tasklist.TaskList;
import karen.ui.Ui;

import java.io.IOException;


public class Parser {
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String BYE_COMMAND = "bye";

    private static TaskList taskList;
    private static ProgramManager programManager;

    public Parser(TaskList taskList, ProgramManager programManager) {
        this.taskList = taskList;
        this.programManager = programManager;
    }


    public String parseTaskCommand(String rawUserInput) {
        String[] inputWords = rawUserInput.toLowerCase().split(" ");
        String taskCommand = inputWords[0];
        return taskCommand;
    }

    public String parseFullTaskDescription(String rawUserInput) {
        int startIndex = rawUserInput.indexOf(" ") + 1;
        String FullTaskDescription = rawUserInput.substring(startIndex);
        return FullTaskDescription;
    }

    public void parseInput(String rawUserInput) {
        String taskCommand = parseTaskCommand(rawUserInput);
        Command command = new Command(taskCommand, this.programManager, taskList);
        try {
            switch (taskCommand) {
            case LIST_COMMAND:
                command.executeListCommand();
                break;
            case DONE_COMMAND:
                command.executeDoneCommand(rawUserInput);
                break;
            case TODO_COMMAND:
                command.executeToDoCommand(rawUserInput, parseFullTaskDescription(rawUserInput));
                break;
            case DEADLINE_COMMAND:
                command.executeDeadlineCommand(rawUserInput, parseFullTaskDescription(rawUserInput));
                break;
            case EVENT_COMMAND:
                command.executeEventCommand(rawUserInput, parseFullTaskDescription(rawUserInput));
                break;
            case DELETE_COMMAND:
                command.executeDeleteCommand(rawUserInput);
                break;
            case BYE_COMMAND:
                command.executeByeCommand(rawUserInput);
                break;
            default:
                Ui.printInvalidCommandMessage();
                break;
            }
        } catch (NoDescriptionException e) {
            Ui.printNoDescriptionMessage();
        } catch (IncorrectDescriptionFormatException e) {
            Ui.printIncorrectDescriptionFormatMessage();
        } catch (NumberFormatException e) {
            Ui.printNumberFormatMessage();
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsMessage();
        } catch (IOException e) {
            Ui.printIOExceptionMessage();
        }
    }
}
