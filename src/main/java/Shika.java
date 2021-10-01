import java.io.IOException;
import java.io.FileNotFoundException;

import shikabot.command.Command;
import shikabot.parser.Parser;
import shikabot.task.TaskList;

import shikabot.ui.TextUi;

import shikabot.storage.Storage;

public class Shika {

    public static String path = "data/ShikaTasks.txt";
    public static Parser parser = new Parser();

    public Storage storage;
    public TaskList taskList;

    public static void main(String[] args) {
        new Shika().run();
    }

    /**
     * Function that calls other functions to run Shika.
     */
    private void run() {
        try {
            setupShika();
        } catch (FileNotFoundException e) {
            TextUi.printFileErrorMessage();
        }
        runShikaLoop();
    }

    /**
     * Function that attempts to load tasks from ShikaTasks.txt. If the file or parent directories do not exist,
     * it creates them.
     * @throws FileNotFoundException when ShikaTasks.txt is not found.
     */
    private void setupShika() throws FileNotFoundException {
        TextUi.printLogo();
        this.storage = new Storage(path);
        TextUi.printWelcomeMessage(checkForSave());
        this.taskList = new TaskList();
        taskList = storage.loadTasks();
    }

    /**
     * Function that checks if a save file exists, and creates one if it does not.
     * @return true if save does not exist, false otherwise
     */
    private boolean checkForSave() {
        boolean hasSave = false;
        try {
            hasSave = storage.setupSave();
        } catch (SecurityException e) {
            TextUi.printSecurityErrorMessage();
        } catch (Storage.FileErrorException e) {
            TextUi.printFileErrorMessage();
        }
        return hasSave;
    }

    /**
     * Function that calls getCommand() in a loop to run Shika. Loop can be exited by inputting "bye".
     */
    private void runShikaLoop() {
        Command command;
        do {
            command = parser.parseCommand(TextUi.getCommand());
            executeCommand(command);
        } while (!command.isExit());
    }

    /**
     * Function that executes the command that is passed in.
     * @param command to be executed.
     */
    private void executeCommand(Command command) {
        try {
            command.setData(taskList);
            command.execute();
            storage.saveTasks(taskList);
        } catch (IOException e) {
            TextUi.printSaveErrorMessage();
        }
    }

}


