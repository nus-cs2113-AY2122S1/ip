package wutdequack.terminator;

import java.util.ArrayList;
import wutdequack.terminator.objects.task.Task;
import wutdequack.terminator.parser.Parser;
import wutdequack.terminator.storage.Storage;
import wutdequack.terminator.tasklist.TaskList;
import wutdequack.terminator.ui.TextUi;

import static wutdequack.terminator.common.MagicValues.BYE_STRING;
import static wutdequack.terminator.common.MagicValues.DEADLINE_STRING;
import static wutdequack.terminator.common.MagicValues.DELETE_STRING;
import static wutdequack.terminator.common.MagicValues.DONE_STRING;
import static wutdequack.terminator.common.MagicValues.EVENT_STRING;
import static wutdequack.terminator.common.MagicValues.FROM_USER;
import static wutdequack.terminator.common.MagicValues.LIST_STRING;
import static wutdequack.terminator.common.MagicValues.TODO_STRING;
import static wutdequack.terminator.common.MagicValues.tasksList;
import static wutdequack.terminator.common.MagicValues.ui;

public class Terminator {

    /**
     * Global variable used to show if loop to get user input should continue running.
     */
    public static Boolean toContinue = true;

    private TaskList taskList;
    private Storage storage;

    /**
     * Handle the bye sequence for Terminator.
     */
    public void handleByeSequence() {
        toContinue = false;
        ui.printGoodByeMessage();
    }

    /**
     * Executes the command based on what is given by the user.
     * @param userLine Line that is inputted by the user.
     */
    private void executeCommand(String userLine) {
        String keyword = new Parser().getKeywordFromUserInput(userLine);

        // Checks for the input for keywords BYE and LIST
        switch (keyword.toUpperCase()) {
        case DONE_STRING:
            // Go to helper function to mark task as done
            taskList.handleDoneTask(userLine);
            break;
        case DELETE_STRING:
            // Go to helper function to mark task as done
            taskList.handleDeleteTask(userLine);
            break;
        case LIST_STRING:
            // Print Tasks with in-built tasksList
            ui.printTasks();
            break;
        case BYE_STRING:
            // Stop loop and print Goodbye
            handleByeSequence();
            break;
        case TODO_STRING:
            taskList.createToDoTask(userLine, FROM_USER);
            break;
        case DEADLINE_STRING:
            taskList.createDeadlineTask(userLine, FROM_USER);
            break;
        case EVENT_STRING:
            taskList.createEventTask(userLine, FROM_USER);
            break;
        default:
            ui.printUnknownCommandMessage();
            break;
        }
        // Update the list
        storage.updateTasksToFile();

    }

    /**
     * Main Function that is called upon program execution.
     * @param args System Arguments added to the program.
     */
    public static void main(String[] args) {
        new Terminator().run();
    }

    /**
     * Runs program until termination
     */
    public void run() {
        start();
        loopUntilByeInitiated();
    }

    private void loopUntilByeInitiated() {
        // Continue Running Loop until bye is called
        while (toContinue) {
            // Gets user input
            String userInput = ui.getUserInput();
            executeCommand(userInput);
        }
    }

    /**
     * Set up required objects and prints welcome message
     */
    private void start() {
        ui = new TextUi();
        this.taskList = new TaskList();
        this.storage = new Storage();
        tasksList  = new ArrayList<Task>();
        ui.printHelloMessage();
        storage.loadTasksFromFile();
    }
}
