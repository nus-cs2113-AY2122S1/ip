package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.TerminateCommand;
import duke.exception.InvalidCommandException;
import duke.exception.TodoFormatException;
import duke.exception.DeadlineFormatException;
import duke.exception.EventFormatException;
import duke.exception.EmptyTasklistException;
import duke.exception.DoneFormatException;
import duke.exception.InvalidTaskIdException;
import duke.exception.TaskAlreadyDoneException;
import duke.exception.DeleteFormatException;

import duke.ui.DukeInterface;
import duke.local.DataManager;
import duke.task.TaskManager;
import duke.parser.Parser;

import java.util.Scanner;

public class Duke {

    private final Scanner in;
    private final DukeInterface dukeUi;
    private final DataManager dataManager;
    private TaskManager taskManager;
    private final Parser parser;


    private final String FILE_PATH = "data/duke.txt";

    public Duke() {
        in = new Scanner(System.in);
        dukeUi = new DukeInterface();
        dataManager = new DataManager(FILE_PATH);
        taskManager = dataManager.loadDataFromFile();
        parser = new Parser();
    }

    public String readInput() {
        dukeUi.printUserName();
        dukeUi.printCursor();
        String input = in.nextLine();
        return input;
    }

    public void startDuke() {

        dukeUi.printWelcomeMsg();

        Command userCommand = null;
        CommandResult commandResult = null;

        do {

            String userInput = readInput();
            userCommand = parser.parseCommand(taskManager, userInput);

            try {
                commandResult = userCommand.executeCommand();
            } catch (Exception e) {
                System.out.println(e);
            }

            if(commandResult.getIsModified() == true) {
                taskManager = commandResult.getTaskManager();
                dataManager.writeToFile(taskManager);
            }

            dukeUi.printMsgWithCursor(commandResult.getDukeMessage());

        } while(commandResult.getIsExited() != true);

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startDuke();
    }

}
