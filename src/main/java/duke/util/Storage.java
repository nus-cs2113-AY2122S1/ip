package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Storage {

    private Path storagePath;

    public Storage() {
        String path = System.getProperty("user.dir");
        int findIpIndex = path.indexOf("ip");
        if (findIpIndex != -1) {
            path = path.substring(0, findIpIndex + 2);
        }
        storagePath = Paths.get(path, "data", "duke.txt");
    }

    private void createDataDirectory() throws IOException {
        if (!Files.exists(storagePath.getParent())) {
            Files.createDirectories(storagePath.getParent());
        }
    }

    private void createStorageFile() throws IOException {
        if (!Files.exists(storagePath)) {
            Files.createFile(storagePath);
        }
    }

    private Task parseTask(String task) throws IllegalArgumentException{
        String[] taskInfo = Arrays.stream(task.split("\\|"))
                .map(String::strip)
                .toArray(String[]::new);
        if (taskInfo.length < 3) {
            throw new IllegalArgumentException();
        }
        Task SingleTask;
        if (taskInfo[0].equals("T") && taskInfo.length == 3) {
            SingleTask = new Todo(taskInfo[2]);
        } else if (taskInfo[0].equals("E") && taskInfo.length == 4) {
            SingleTask = new Event(taskInfo[2],taskInfo[3]);
        } else if (taskInfo[0].equals("D") && taskInfo.length == 4) {
            SingleTask = new Deadline(taskInfo[2],taskInfo[3]);
        } else {
            throw new IllegalArgumentException();
        }
        if (taskInfo[1].equals("1")) {
            SingleTask.markAsDone();
        }
        return SingleTask;
    }

    public void loadFile(TaskManager taskList) throws IOException, IllegalArgumentException {
        createDataDirectory();
        createStorageFile();
        try (Stream<String> stream = Files.lines(storagePath)) {
            stream.map(this::parseTask).forEach(taskList::addTask);
        } catch (IOException fileException) {
            throw new IOException();
        }
    }

    public void saveFile(TaskManager taskList) throws IOException {
        FileWriter storageFile = new FileWriter(storagePath.toFile());
        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            storageFile.write(taskList.getTask(i).saveToText() + "\n");
            storageFile.flush();
        }
        storageFile.close();
    }
}
