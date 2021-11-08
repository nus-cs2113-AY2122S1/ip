package duke;

/**
 * This is the main class of the chat-bot app that helps user remember their different
 * types of task and "parrot" the task back to them when they request.
 *
 * @author YEOWEIHNGWHYELAB
 */

import duke.commandHandler.CommandHandler;
import duke.exceptionHandler.DukeException;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    /**
     * Main run() method. Initialize Ui, Scanner, check command and execute them accordingly.
     *
     * @throws IOException When there is issue with reading duke.txt due to lack of permission...
     */
    public static void run() throws IOException {
        Ui ui = new Ui();
        ui.showWelcome();

        String userInputString;
        Scanner userInput = new Scanner(System.in);

        Storage dukeTaskText = new Storage();
        TaskList.numberOfTasks = dukeTaskText.loadTask(TaskList.tasks);

        boolean isExit  = false;
        while (!isExit) {
            userInputString = userInput.nextLine();
            Parser commandHandle = new Parser(userInputString);
            CommandHandler runningUserCommand = new CommandHandler();
            try {
                isExit = runningUserCommand.commandHandle(commandHandle, userInputString, dukeTaskText, ui);
            } catch (DukeException dukeError) {
                dukeError.printErrorMessage();
            }
        }

        userInput.close();
    }

    public static void main(String[] args) throws IOException { run(); }
}
