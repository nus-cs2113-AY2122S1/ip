package duke.logic;

import duke.UI.Message;
import duke.tasks.TaskList;
import duke.UI.UserInterface;
import duke.command.Command;
import duke.database.Database;
import duke.exception.DukeException;
import duke.parser.Parser;

/**
 * Logic class runs the main logic of the programme.
 */
public class Logic {
    public static int listIndex = 0;
    private static UserInterface userInterface;
    private Database database;
    protected static String fileAddress;
    private TaskList taskList;

    /**
     * Constructor
     *
     * @param filePath file path of storage file
     */
    public Logic(String filePath) {
        fileAddress = filePath;
        userInterface = new UserInterface();
        database = new Database(filePath);
        taskList = database.loadTasks();
    }

    /**
     * Runs the main logic of the program.
     * Welcome message is printed to user and programme enters a while loop to repeatedly get input
     * from the user and then correspondingly respond to them. Input is put through the Parser class
     * which then put through the Command class. The output of the Command class is given to the
     * UserInterface Class to print out the message to the user.
     *
     */
    public void run() {
        userInterface.printMessage(Message.printWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                //get input
                String inputCommand = userInterface.getInput();
                //parse input
                Command commandHandler = new Parser().parseCommand(inputCommand);
                //get appropriate message
                String commandMessage = commandHandler.execute(taskList);
                //print out message
                userInterface.printMessage(commandMessage);
                //see whether to exit the program
                isExit = commandHandler.getExitStatus();
            } catch (DukeException e) {
                userInterface.printMessage(e.getErrorMessage());
            }
        }
    }
}
