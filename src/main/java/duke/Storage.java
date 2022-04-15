package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class that initializes memory file location and contains all storage methods.
 */
public class Storage {
    private String fileString;
    private Path filePath;
    private File file;

    /**
     * Initializes new Storage object.
     * Sets file parameter to fileString and creates a new file if it does not exist.
     * @param fileString path of memory file as a string
     */
    public Storage(String fileString) {
        this.fileString = fileString;
        this.filePath = Paths.get(fileString);
        this.file = new File(fileString);

        if (!file.exists()) {
            createFile();
        }
    }

    /**
     * Creates a new file object with given filePath.
     */
    private void createFile() {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println("Error: Unable to create file!");
            System.out.println("Seems like the directory does not exist!");
        }
    }

    /**
     * Returns the contents of the memory file as a single string.
     * String returned has no line separator at the end.
     * @return contents of memory file as a string
     * @throws FileNotFoundException If the memory file cannot be found.
     */
    public String load() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        String memAsString = "";
        String memAsTrimmedString;
        while (s.hasNextLine()) {
            String nextLine = s.nextLine();
            memAsString += nextLine + System.lineSeparator();
        }
        if (memAsString.equals("")) {
            memAsTrimmedString = memAsString;
        } else {
            memAsTrimmedString = memAsString.substring(0, memAsString.length()-2);
        }
        rewriteMem(memAsTrimmedString);
        return memAsTrimmedString;
    }

    /**
     * Overwrites the memory file with given data.
     * @param data data to write to memory as a string
     */
    private void rewriteMem(String data) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to write to file");
            System.out.println("Seems like the directory does not exist!");
        }
    }

    // Storage methods for DoneCommand

    /**
     * Alters memory file to reflect completion of task of taskIndex.
     * @param taskIndex Index of completed task
     */
    public void writeDone(int taskIndex) {
        try {
            markDoneInMem(taskIndex);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to write to file");
            System.out.println("Error: Seems like the file does not exist!");
        }
    }

    /**
     * Creates a new string of data reflecting completion of task of taskIndex.
     * Writes new string to the memory file.
     * @param taskIndex Index of completed task
     * @throws FileNotFoundException If unable to write to memory file.
     */
    private void markDoneInMem(int taskIndex) throws FileNotFoundException {
        Scanner s = new Scanner(this.file);
        int lineCount = 0;
        String memAsString = "";
        while (s.hasNextLine()) {
            if (lineCount == taskIndex) {
                String targetTask = s.nextLine().replaceFirst("0", "1");
                memAsString += targetTask + System.lineSeparator();
            } else {
                memAsString += s.nextLine() + System.lineSeparator();
            }
            lineCount++;
        }
        String memAsTrimmedString = memAsString.substring(0, memAsString.length()-2);
        rewriteMem(memAsTrimmedString);
    }

    //Storage methods for adding new tasks

    /**
     * Appends a single line string to the memory file.
     * Does not overwrite the memory file.
     * @param data single line string to be appended to memory file
     */
    public void appendToMem(String data) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to write to file");
            System.out.println("Error: Seems like the directory does not exist!");
        }
    }

    //Storage method for deleting tasks

    /**
     * Deletes task of taskIndex from the memory file.
     * Creates a new string of data reflecting the deletion and writes it to the memory file.
     * @param taskIndex Index of deleted task
     * @throws FileNotFoundException If memory file cannot be found
     */
    public void deleteTaskFromMem(int taskIndex) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        int lineCount = 0;
        String memAsString = "";
        String memAsTrimmedString;
        while (s.hasNextLine()) {
            if (lineCount == taskIndex) {
                String deletedString = s.nextLine();
            } else {
                memAsString += s.nextLine() + System.lineSeparator();
            }
            lineCount++;
        }
        if (taskIndex == 0 && lineCount == 1) {
            memAsTrimmedString = memAsString;
        } else {
            memAsTrimmedString = memAsString.substring(0, memAsString.length()-2);
        }
        rewriteMem(memAsTrimmedString);
    }
}
