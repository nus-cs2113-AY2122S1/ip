package duke.datasaver;

import duke.exception.InvalidFileDataException;
import duke.exception.InvalidFileTypeException;
import duke.task.Task;

import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.constants.DukeDataStorageConstants.DEFAULT_STORAGE_FILEPATH;
import static duke.constants.DukeDataStorageConstants.VALID_FILE_TYPE;

public class DataManager {

    private Path filePath;

    public DataManager() {
        this.filePath = Paths.get(DEFAULT_STORAGE_FILEPATH);
    }

    public DataManager(String filePath) {
        try {
            checkFileType(filePath);
            this.filePath = Paths.get(filePath);
        } catch (InvalidFileTypeException e) {
            Ui.printInvalidFileTypeMessage();
        }
    }

    public String getFilePath() {
        return filePath.toString();
    }

    public void loadData(ArrayList<Task> taskList) {
        Scanner fileScanner = getScanner(filePath.toString());
        readData(fileScanner, taskList);
    }

    public void saveData(ArrayList<Task> taskList) {
        try {
            writeData(taskList);
        } catch (IOException ioException) {
            System.out.println(" Something went wrong when writing to this file: " + ioException.getMessage());
        }
    }

    private static Scanner getScanner(String filePath) {
        File taskFile = new File(filePath);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(taskFile);
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                createFileInDirectory(filePath);
            } catch (IOException ioException) {
                System.out.println(" Something went wrong when creating this file: " + ioException.getMessage());
            }
        }
        return fileScanner;
    }

    private static void readData(Scanner fileScanner, ArrayList<Task> taskList) {
        if (fileScanner != null) {
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                try {
                    TaskListDecoder.decodeTask(taskList, task);
                } catch (InvalidFileDataException | DateTimeParseException e) {
                    Ui.printFileTaskInvalidFormatMessage();
                }
            }
        }
    }

    private static void writeData(ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(DEFAULT_STORAGE_FILEPATH, false);
        StringBuilder formattedTask = new StringBuilder();
        TaskListEncoder.encodeTask(taskList, fileWriter, formattedTask);
        fileWriter.close();
    }

    private static void createFileInDirectory(String filePath) throws IOException {
        Files.createFile(Paths.get(filePath));
    }

    private static void checkFileType(String filePath) throws InvalidFileTypeException {
        if (!filePath.endsWith(VALID_FILE_TYPE)) {
            throw new InvalidFileTypeException();
        }
    }
}
