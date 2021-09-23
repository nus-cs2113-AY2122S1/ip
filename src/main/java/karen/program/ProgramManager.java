package karen.program;

import karen.parser.Parser;
import karen.storage.Storage;
import karen.tasklist.TaskList;
import karen.ui.Ui;

import java.io.File;

public class ProgramManager {
    private static TaskList taskList;
    private static Parser parser;
    private static boolean isRunning;
    private static boolean isFirstRun;

    public ProgramManager() {
        isRunning = true;
        isFirstRun = getIsFirstRun();

        taskList = new TaskList();
        parser = new Parser(taskList, this);
        Storage.bootUpData(this, taskList);
        Ui.printWelcomeMessage(isFirstRun);
    }

    public void startProgram() {
        do {
            String rawUserInput = Ui.getUserInput();
            parser.processInput(rawUserInput);
        } while (getIsRunning());

    }

//    public void setIsFirstRunFalse() {
//        this.isFirstRun = false;
//    }

    public boolean getIsFirstRun() {
        File dataFile = new File(Storage.getFilePath());
        if (dataFile.exists()) {
            this.isFirstRun = false;
        } else {
            this.isFirstRun = true;
        }
        return this.isFirstRun;
    }

    public void setIsRunningOff() {
        this.isRunning = false;
    }

    public boolean getIsRunning() {
        return isRunning;
    }


}
