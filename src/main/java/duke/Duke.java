package duke;

import duke.exception.AtEmptyException;
import duke.exception.ByEmptyException;
import duke.exception.NumberOutOfBoundsException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {
    private static final String SAD_FACE = "\uD83D\uDE41";

    private Ui dukeUi;
    private Storage dukeStorage;
    private TaskList dukeTask;

    public Duke () {
        dukeUi = new Ui();
        dukeStorage = new Storage();
        dukeTask = new TaskList(dukeStorage.taskArrayList);
    }

    private static void printReply(String userInput) {
        try {
            TaskList.processUserInput(userInput);
        } catch (NumberFormatException e) {
            System.out.println(SAD_FACE + " OOPS! The character you entered is not a number: " + TaskList.number);
        } catch (NumberOutOfBoundsException e) {
            System.out.println(SAD_FACE + e.getMessage() + TaskList.number);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(SAD_FACE + " The description of the " + TaskList.inputCommand + " is not " +
                    "complete.");
        } catch (AtEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
        } catch (ByEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
        }
    }

    public void run(){
        dukeUi.printLogo();
        dukeStorage.loadFile();
        dukeUi.printGreeting("Hello! I'm Duke", "What can I do for you?");
        while (true) {
            String userInput = dukeUi.getUserInput();
            printReply(userInput);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
