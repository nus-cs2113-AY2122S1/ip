import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;

import shikabot.command.Command;
import shikabot.command.ExitCommand;
import shikabot.parser.Parser;
import shikabot.task.Task;

import shikabot.ui.TextUi;

import shikabot.saves.SaveFile;

public class Shika {

    public static ArrayList<Task> tasks;
    public static String path = "data/ShikaTasks.txt";
    public static TextUi ui = new TextUi();
    public static Parser parser = new Parser();

    public SaveFile saveFile;

    public static void main(String[] args) {
        new Shika().run();
    }

    /**
     * Function that calls other functions to run Shika.
     */
    public void run() {
        try {
            setupShika();
        } catch (FileNotFoundException e) {
            System.out.println("I can't find the save file AHHHHHHH.\n");
        }
        runShikaLoop();
    }

    /**
     * Function that attempts to load tasks from ShikaTasks.txt. If the file or parent directories do not exist,
     * it creates them.
     * @throws FileNotFoundException when ShikaTasks.txt is not found.
     */
    public void setupShika() throws FileNotFoundException {
        ui.printLogo();
        this.saveFile = new SaveFile(path);
        ui.printWelcomeMessage(checkForSave());
        tasks = saveFile.loadTasks();
    }

    public boolean checkForSave() {
        boolean hasSave = false;
        try {
            hasSave = saveFile.setupSave();
        } catch (SecurityException e) {
            ui.printSecurityErrorMessage();
        } catch (SaveFile.FileErrorException e) {
            ui.printFileErrorMessage();
        }
        return hasSave;
    }

    /**
     * Function that calls getCommand() in a loop to run Shika. Loop can be exited by inputting "bye".
     */
    public void runShikaLoop() {
        Command command;
        do {
            command = parser.parseCommand(ui.getCommand());
            executeCommand(command);
        } while (!isExitCommand(command));
    }

    public boolean isExitCommand(Command command) {
        return command instanceof ExitCommand;
    }

    public void executeCommand(Command command) {
        try {
            command.setData(tasks);
            command.execute();
            saveFile.saveTasks(tasks);
        } catch (IOException e) {
            ui.printSaveErrorMessage();
        }
    }

}


