package duke.utilities;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws DukeException {
        checkForFile();
        TaskList data = readFromFile();
        return data.getTasks();
    }

    private void checkForFile() {
        File file = new File(filePath);
        try {
            Files.createDirectories(Paths.get("./data"));
            if (file.createNewFile()) {
                System.out.println("No data found! A new file was created successfully.");
            }
        } catch (IOException ioError) {
            ioError.printStackTrace();
        }
    }

    public TaskList readFromFile() throws DukeException {
        File file = new File(filePath);
        TaskList temp = new TaskList();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                convertFileData(temp, scanner.nextLine());
            }
        } catch (FileNotFoundException fileE) {
            fileE.printStackTrace();
        }
        return temp;
    }

    private void convertFileData(TaskList temp, String line) throws DukeException {
        String[] words = line.split(" ");
        boolean isDone = checkDone(words[0]);
        String task = line.substring(2);
        String type = checkType(task);
        temp.addTask(task, isDone, type);
    }

    private String checkType(String task) throws DukeException {
        String type = new String();
        switch (Parser.scanCommand(task)) {
        case "todo":
            type = "T";
            break;
        case "event":
            type = "E";
            break;
        case "deadline":
            type = "D";
            break;
        }
        return type;
    }

    private boolean checkDone(String word) {
        return "1".equals(word);
    }

    public void saveToFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task t : tasks) {
                writer.write(Parser.parseLineForSaving(t));
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
