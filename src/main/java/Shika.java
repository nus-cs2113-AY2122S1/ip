import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

import shikabot.command.Command;
import shikabot.command.ExitCommand;
import shikabot.parser.Parser;
import shikabot.task.Task;

import shikabot.ui.TextUi;

import shikabot.saves.SaveFile;

public class Shika {

    public static ArrayList<Task> tasks;
    public static String path = "data/ShikaTasks.txt";

    public TextUi ui;
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
        this.ui = new TextUi();
        this.saveFile = new SaveFile(path);
        ui.printLogo();
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
        Scanner in = new Scanner(System.in);
        String text;
        do {
            text = in.nextLine();
            command = new Parser().parseCommand(text);
            executeCommand(command);
        } while (!isExitCommand(command) && in.hasNextLine());
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


