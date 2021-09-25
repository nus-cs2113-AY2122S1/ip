package karlett;

import karlett.parser.Parser;
import karlett.storage.StorageFile;
import karlett.task.Task;
import karlett.ui.TextUi;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final char FILE_DELIMITER = '|';

    public static ArrayList<Task> list = new ArrayList<>();

    public static void main(String... launchArgs) throws IOException {
        TextUi.greet();
        StorageFile.loadDataFromStorageFile();
        runCommandLoopUntilExitCommand();
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private static void runCommandLoopUntilExitCommand() throws IOException {
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();
            Parser.parseCommand(userInput);
        }
    }

}
