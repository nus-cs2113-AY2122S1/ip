package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    private Path dataPath;

    public Storage() {
        openFile();
    }

    public void openFile() {
        try {
            String home = System.getProperty("user.dir");
            Path dataDirPath = Paths.get(home, "/data/");
            Files.createDirectories(dataDirPath);
            Path dataPath = Paths.get(home, "/data/duke.txt");
            if (!Files.exists(dataPath)) {
                Files.createFile(dataPath);
            }
            this.dataPath = dataPath;
        } catch (IOException e) {
            System.err.println("Open failure: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        List<String> lines = this.returnAllFileData();
        ArrayList<Task> tasks = new ArrayList<>();
        if (lines.isEmpty()) {
            return tasks;
        }
        for (String line : lines) {
            Task task = convertFileLineToTask(line);
            tasks.add(task);
        }
        return tasks;
    }

    public Task convertFileLineToTask(String line) {
        // line is in the format: task type | done | description | additional detail
        String[] taskComponents = line.split("\\|");
        for (String component : taskComponents) {
            component.strip();
        }
        String taskType = taskComponents[0];
        String done = taskComponents[1];
        String description = taskComponents[2];
        String detail = taskComponents[3];

        Task task;
        boolean isDone = done == "X";

        switch (taskType) {
            case "D":
                task = new Deadline(description, detail, isDone);
                break;
            case "E":
                task = new Event(description, detail, isDone);
                break;
            default:
                task = new Todo(description, isDone);
        }
        return task;
    }

    //TODO make a function in Formatter for lists
    public String returnAllFileDataAsList() {
        String output = "";
        try {
            List<String> lines = Files.readAllLines(dataPath);
            for (int i = 0; i < lines.size(); i++) {
                output += System.lineSeparator() + (i + 1) + "."
                        + lines.get(i);
            }
        } catch (IOException e) {
            System.err.println("Read failure: " + e.getMessage());
        }
        return output;
    }

    public List<String> returnAllFileData() {
        List<String> lines = List.of();
        try {
            lines = Files.readAllLines(dataPath);
        } catch (IOException e) {
            System.err.println("Read failure: " + e.getMessage());
        }
        return lines;
    }

    public void addTaskToFileData(String taskString) {
        appendLineToFileData(taskString);
    }

    public void appendLineToFileData(String line) {
        try {
            List<String> lines = Files.readAllLines(dataPath);
            lines.add(line);
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    //TODO clean code in Storage class to use this function
    private List<String> getAllLines() {
        List<String> lines = List.of();
        try {
            lines = Files.readAllLines(dataPath);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
        return lines;
    }

    public String getLine(int index) {
        List<String> lines = getAllLines();
        String output = "";
        try {
            output = lines.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Index out of bounds failure: " + e.getMessage());
        }

        return output;
    }

    public void setDone(int id) {
        String newFileDataLine = getLine(id);
        char[] newFileDataLineChars = newFileDataLine.toCharArray();
        newFileDataLineChars[3] = 'X';
        replaceFileData(id, String.valueOf(newFileDataLineChars));
    }

    public void replaceFileData(int index, String line) {
        try {
            List<String> lines = Files.readAllLines(dataPath);
            lines.set(index, line);
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    public void deleteLineFromFileData(int index) {
        try {
            List<String> lines = Files.readAllLines(dataPath);
            lines.remove(index);
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    public String clearFileData() {
        try {
            Files.write(dataPath, Collections.EMPTY_LIST);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
        return "A clean slate, my liege!";
    }
}
