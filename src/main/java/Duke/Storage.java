package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static Duke.Messages.DIVIDER;
import static Duke.Messages.NEW_FILE;

public class Storage {
    private static final String TODO_TYPE = "T";
    private static final String DEADLINE_TYPE = "D";
    private static final String EVENT_TYPE = "E";

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws DukeException {
        try {
            return readFile(filePath);
        } catch (FileNotFoundException e) {
            try {
                Path pathFilePath = Paths.get(filePath);
                Files.createDirectories(pathFilePath.getParent());
                System.out.print(NEW_FILE + DIVIDER);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return new TaskList();
    }

    private TaskList readFile(String filePath) throws FileNotFoundException, DukeException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        TaskList fileTasks = new TaskList();
        while (s.hasNext()) {
            String rawLineData = s.nextLine();
            String [] lineData = splitDataString(rawLineData);
            manageTaskFromFile(lineData, fileTasks);
        }
        return fileTasks;
    }

    private void manageTaskFromFile(String[] lineData, TaskList fileTasks) throws DukeException {
        String taskType = lineData[0];
        boolean isDone = lineData[1].equals("1");
        String description = lineData[2];
        String extraDescription = lineData[3];
        Task taskToAdd;
        switch (taskType) {
        case(TODO_TYPE):
            taskToAdd = new Todo(description);
            break;
        case(DEADLINE_TYPE):
            taskToAdd = new Deadline(description, extraDescription);
            break;
        case(EVENT_TYPE):
            taskToAdd = new Event(description, extraDescription);
            break;
        default:
            throw new DukeException();
        }
        taskToAdd.setDone(isDone);
        fileTasks.addTask(taskToAdd);
    }

    public void writeToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.tasksLength(); i += 1) {
                String textToWrite = tasks.formatTaskForFile(i);
                fw.write(textToWrite);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] splitDataString(String input) throws DukeException {
        String[] split = input.trim().split(" \\| ", 4);
        if (split.length < 3) {
            throw new DukeException();
        }
        return split.length == 4 ? split: new String[] {split[0], split[1], split[2], ""};
    }
}
