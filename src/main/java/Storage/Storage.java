package Storage;

import Commands.Command;
import Parsing.ParseInput;
import Parsing.Parser;
import Tasks.Task;
import UI.UI;
import Main.Duke;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Exception.DukeException;

/**
 * Represents actions that involve reading/writing to Hard Drive.
 *
 * Storage object contains two Strings representing File paths
 * 1. commandfilePath - path to .txt file that contains all valid commands made by User
 * 2. datafilePath - path to .txt file that contains all User tasks
 */
public class Storage {

    final private String commandfilePath;
    final private String datafilePath;

    public Storage (String commandfilePath, String datafilePath) {
        this.commandfilePath = commandfilePath;
        this.datafilePath = datafilePath;
    }

    /**
     * Executes saved commands in .txt file, to populate Duke Tasks when Duke is started-up.
     *
     * @param tasks    User's tasks in Duke (will be an empty list)
     * @param storage  actions involving reading/writing to hard drive
     */
    public void loadData (List<Task> tasks, Storage storage) {
        File commandFile = new File(commandfilePath);

        try {
            if (commandFile.createNewFile()) {
                UI.fileCreated(commandfilePath);
                Duke.isLoading = false;
            }

            else {
                Scanner commandScanner = new Scanner(commandFile);
                while (commandScanner.hasNextLine()) {
                    ParseInput parseInput = Parser.parse(commandScanner.nextLine());
                    Command.executeCommand(parseInput, tasks, storage);
                }
            }
            Duke.isLoading = false;
        } catch (IOException ioException) {
            UI.fileExistsError(commandfilePath);
        } catch (DukeException dukeException) {
            UI.dukeError(dukeException.getMessage());
        }
    }

    /**
     * Saves commands made during Duke's usage to a .txt file
     *
     * @param input a singular Command made to Duke
     */
    public void saveCommand (String input) {
        try {
            BufferedWriter commandFileWriter = new BufferedWriter(new FileWriter(commandfilePath, true));
            commandFileWriter.append(input);
            commandFileWriter.newLine();
            commandFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current Tasks in Duke to a .txt file
     *
     * @param tasks User's tasks in Duke
     */
    public void saveData (List<Task> tasks) {
        File dataFile = new File (datafilePath);

        try {
            if (dataFile.createNewFile()) {
                UI.fileCreated(datafilePath);
            }

            BufferedWriter dataFileWriter = new BufferedWriter(new FileWriter(dataFile));
            for (int i = 0; i < tasks.size(); i++) {
                dataFileWriter.append(tasks.get(i).describeString());
                dataFileWriter.newLine();
            }
            dataFileWriter.close();

        } catch (IOException e) {
            UI.fileExistsError(datafilePath);
        }
    }
}
