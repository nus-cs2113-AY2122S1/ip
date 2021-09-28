package storage;

import parser.Parser;
import tasks.Task;
import ui.Ui;
import errors.InvalidFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class helps to do all the saving and loading of data to the tasks Arraylist.
 */

public class Storage {
    File filePath;

    public Storage(String filePath) {
        this.filePath = new File(filePath);
    }

    /**
     * Converts all tasks to a string to be written to the file Duke.txt.
     *
     * @param ui    Reference to the UI object passed by Main to print messages.
     * @param tasks Reference to the ArrayList of Tasks passed by Main.
     */
    public void saveData(Ui ui, ArrayList<Task> tasks) {
        if (!filePath.exists()) {
            createDirectory();
        }
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(task.toFile()).append("\n");
        }

        try {
            FileWriter myFile = new FileWriter(filePath);
            myFile.write(output.toString());
            myFile.close();
        } catch (IOException e) {
            ui.customPrint("Could not write to file!");
        }
    }

    /**
     * Loads the data to task ArrayList if Duke.txt exists.
     *
     * @param ui Reference to the UI object passed by Main to print messages.
     * @return ArrayList of Tasks.
     */
    public ArrayList<Task> loadData(Ui ui) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(filePath);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                Task newTask = Parser.fileParser(line);
                tasks.add(newTask);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            ui.customPrint("Data file does not exist! I will create new one.");
            if (!filePath.exists()) {
                createDirectory();
            }
            createFile(ui);
        } catch (InvalidFile invalidFile) {
            ui.customPrint("File contains invalid data!");
            System.exit(0);
        }
        return tasks;
    }

    /**
     * Creates the data folder if it doesn't exist.
     */
    public void createDirectory() {
        File dataDirectory = new File(filePath.getParent());
        dataDirectory.mkdirs();
    }

    /**
     * Creates the file if it doesn't exist.
     */
    public void createFile(Ui ui) {
        try {
            FileWriter myFile = new FileWriter(filePath);
            myFile.close();
        } catch (IOException ex) {
            ui.customPrint("Could not create file!");
        }
    }

}
