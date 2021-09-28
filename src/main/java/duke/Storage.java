package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private String fileString;
    private Path filePath;
    private File file;

    public Storage(String fileString) {
        this.fileString = fileString;
        this.filePath = Paths.get(fileString);
        this.file = new File(fileString);

        if (!file.exists()) {
            createFile();
        }
    }

    private void createFile() {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println("Error: Unable to create file!");
            System.out.println("Seems like the directory does not exist!");
        }
    }

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
    public void writeDone(int taskIndex) {
        try {
            markDoneInMem(taskIndex);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to write to file");
            System.out.println("Error: Seems like the file does not exist!");
        }
    }

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
