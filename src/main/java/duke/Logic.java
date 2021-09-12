package duke;

import duke.exception.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Logic class runs the main logic of the programme.
 */
public class Logic {
    protected static int LIST_INDEX = 0;
    protected static int LIST_LENGTH = 100;
    private static UserInterface userInterface;
    //protected static Task[] tasks = new Task[LIST_LENGTH];
    protected static ArrayList<Task> tasks = new ArrayList<>();

    public Logic() {
        userInterface = new UserInterface();
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
                Parser parseInput = new Parser(inputCommand);
                //put parsed input into Command object
                Command commandHandler = new Command(parseInput.getCommand(), parseInput.getDescription(), parseInput.getDate());
                //get appropriate message
                String commandMessage = commandHandler.executeCommand();
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
