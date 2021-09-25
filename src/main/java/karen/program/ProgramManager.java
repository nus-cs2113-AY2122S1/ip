package karen.program;

import karen.parser.Parser;
import karen.storage.Storage;
import karen.tasklist.TaskList;
import karen.ui.Ui;

import java.io.File;

/**
 * Represents an interface to control the running of the program
 */
public class ProgramManager {
    private static TaskList taskList;
    private static Parser parser;
    private static boolean isRunning;
    private static boolean isFirstRun;

    /**
     * This class constructor is used to start the set up of the the program by instantiating
     * a new TaskList and a new Parser. If there is existing data stored in the data
     * file from the previous runs, this constructor will load the existing data. This
     * constructor also prints out a starting message that depends on whether it is the
     * user's first run.
     */
    public ProgramManager() {
        isRunning = true;
        isFirstRun = getIsFirstRun();

        taskList = new TaskList();
        parser = new Parser(taskList, this);
        Storage.bootUpData(taskList, this.parser);
        Ui.printStartMessage(isFirstRun);
    }

    /**
     * This method starts the program running, and keeps the program running while
     * prompting for user inputs. After getting user inputs, this method will parse
     * the input and execute the user's commands accordingly. The program will continue
     * to prompt for user inputs and process them, until the user inputs a bye command,
     * which will then end the program.
     */
    public void runProgram() {
        do {
            String rawUserInput = Ui.getUserInput();
            parser.parseInput(rawUserInput);
        } while (getIsRunning());
    }

    /**
     * Returns a boolean to check if program has ran before.
     *
     * This method checks for existing storage file in the file path, to detect
     * if it is the program's first run.
     * @return boolean to check if it is the first program run
     */
    public boolean getIsFirstRun() {
        File dataFile = new File(Storage.getFilePath());
        if (dataFile.exists()) {
            this.isFirstRun = false;
        } else {
            this.isFirstRun = true;
        }
        return this.isFirstRun;
    }

    /**
     * This method provides a flag to end the program.
     */
    public void setIsRunningOff() {
        this.isRunning = false;
    }

    /**
     * Return a boolean to check if the program is still running.
     *
     * @return boolean to check the runnng status of the program
     */
    public boolean getIsRunning() {
        return isRunning;
    }


}
