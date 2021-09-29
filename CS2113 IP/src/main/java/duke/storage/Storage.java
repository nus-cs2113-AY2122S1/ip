package duke.storage;

import duke.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    final private static String DIRECTORY_CREATED = "Data directory created successfully >>::))";
    final private static String DIRECTORY_EXISTS = "Duke-data directory exists >>::))";
    final private static String DATABASE_CREATED = "Duke database created >>::))";
    final private static String DATABASE_EXISTS = "Duke database up-to-date! >>::))";
    final private static String filePath = getFilePath();

    public Storage() {
    }

    /**
     * Loads the newly created file, and creates a directory at the specified file path.
     * Prints a message to signal the directory creation if it does not exist.
     */
    public void createDirectory() {
        File newFile = load();
        boolean isCreated = newFile.mkdir();
        if (isCreated) {
            System.out.println(DIRECTORY_CREATED);
        } else {
            System.out.println(DIRECTORY_EXISTS);
        }
    }

    /**
     * Creates a file specified by filePath, for creation of text database.
     * If file is successfully created, notify the user.
     *
     * @throws IOException If file cannot be created at the specified file path (i.e. path does not exist)
     */
    public void createFile(Ui ui) throws IOException {
        File newFile = new File(filePath);
        if (newFile.createNewFile()) {
            System.out.println(DATABASE_CREATED);
        } else {
            System.out.println(DATABASE_EXISTS);
        }
        ui.showHorizontalLine();
    }

    /**
     * Returns a File object that prepares the creation of a directory (data) if it does not exist.
     *
     * @return File object with specified file path for storage creation.
     */
    public File load() {
        Path currentRelativePath = Paths.get("");
        Path currentPath = currentRelativePath.toAbsolutePath();
        File newFile = new File(currentPath + "/data");
        return newFile;
    }

    /**
     * Returns the concatenation of the current path and the path to the database.
     *
     * @return File path where database resides.
     */
    public static String getFilePath() {
        Path currentRelativePath = Paths.get("");
        Path currentPath = currentRelativePath.toAbsolutePath();
        String filePath = currentPath + "/data/duke.txt";
        return filePath;
    }

    /**
     * Writes the list of tasks and its descriptive fields into the database at the end of runtime of programme.
     * Deadline and Event typed tasks will have their descriptions formatted different compared to Todo type tasks.
     *
     * @param tasks List of tasks.
     * @throws IOException If file does not exist, or file path does not exist, or no access rights to the file.
     */
    public void writeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (int i = 0; i < TaskList.getTaskCount(); i++) {
            boolean isEvent = tasks.get(i).taskType.equals("E");
            boolean isDeadline = tasks.get(i).taskType.equals("D");
            String formatDescription;
            if (isEvent) {
                formatDescription = String.format("%s /at %s",
                        tasks.get(i).specificDescription, tasks.get(i).date);
            } else if (isDeadline) {
                formatDescription = String.format("%s /by %s",
                        tasks.get(i).specificDescription, tasks.get(i).deadline);
            } else {
                formatDescription = tasks.get(i).description;
            }

            String formatToWrite = String.format("%s | %s | %s\n",
                    tasks.get(i).taskType, tasks.get(i).getStatusIcon(), formatDescription);
            fw.write(formatToWrite);
        }
        fw.close();
    }
}
