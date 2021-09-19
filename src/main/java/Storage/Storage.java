package Storage;

import Commands.Command;
import Parsing.ParseInput;
import Parsing.Parser;
import Tasks.Task;
import UI.UI;
import Main.Duke;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import Exception.DukeException;

public class Storage {

    private String commandfilePath;
    private String datafilePath;

    public Storage (String commandfilePath, String datafilePath) {
        this.commandfilePath = commandfilePath;
        this.datafilePath = datafilePath;
    }

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
