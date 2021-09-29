package duke;

import duke.exception.EmptyCommandArgumentException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidCommandSeparatorException;
import duke.exception.InvalidTaskIndexException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.Parser;

import java.util.Scanner;

public class Duke {

    protected TaskList taskList;
    protected Ui ui;
    protected Storage storage;

    public Duke() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
    }

    /**
     * Processes the input provided by the user.
     * Calls the appropriate function based on the user's input.
     */
    public void handleCommand() {
        String userInput;
        Scanner in = new Scanner(System.in);

        userInput = in.nextLine();
        Parser parser = new Parser();
        String[] inputWords = parser.getInputAsWordsArray(userInput);
        String userCommand = parser.getCommand(inputWords);

        try {
            switch (userCommand) {
            case "bye":
                ui.printBye();
                break;
            case "list":
                taskList.showList();
                handleCommand();
                break;
            case "done":
                taskList.markTaskAsDone(inputWords[1]);
                handleCommand();
                break;
            case "deadline":
                taskList.addDeadlineOrEventTask(inputWords, "deadline");
                handleCommand();
                break;
            case "event":
                taskList.addDeadlineOrEventTask(inputWords, "event");
                handleCommand();
                break;
            case "todo":
                taskList.addTodoTask(inputWords);
                handleCommand();
                break;
            case "delete":
                taskList.deleteTask(inputWords);
                handleCommand();
                break;
            case "find":
                taskList.findTask(inputWords);
                handleCommand();
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            ui.printErrorInvalidCommand();
            handleCommand();
        } catch (EmptyCommandArgumentException e) {
            ui.printErrorEmptyCommandArg();
            handleCommand();
        } catch (InvalidCommandSeparatorException e) {
            ui.printErrorInvalidSeparator();
            handleCommand();
        } catch (InvalidTaskIndexException e) {
            ui.printErrorInvalidTaskIndex();
            handleCommand();
        }
    }

    /**
     * Runs the main functions to start the chatbot.
     */
    public void run() {
        ui.printWelcome();
        storage.createDataFile();
        storage.loadDataFile(taskList);
        handleCommand();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
