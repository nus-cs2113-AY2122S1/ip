package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    //TODO make a function in Formatter for lists
    //TODO make a function in TaskHandler to understand the data in storage
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

    public void appendLinetoFileData(String line) {
        try {
            List<String> lines = Files.readAllLines(dataPath);
            lines.add(line);
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    public void editFileData(int index, String line) {
        try {
            List<String> lines = Files.readAllLines(dataPath);
            lines.set(index, line);
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    public void deleteLinefromFileData(int index) {
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
