package karlett.storage;

import karlett.task.Task;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageFile {

    private File file;
    private TaskListDecoder taskListDecoder;
    private TaskListEncoder taskListEncoder;

    public File getFile() {
        return file;
    }

    public StorageFile(String filePath) {
        file = new File(filePath);
        taskListDecoder = new TaskListDecoder();
        taskListEncoder = new TaskListEncoder(filePath);
    }

    public ArrayList<Task> loadData() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String task = s.nextLine();
            tasks = taskListDecoder.processFileData(tasks, task);
        }
        return tasks;
    }

    public void appendNewTaskToFile(Task task) throws IOException {
        taskListEncoder.appendNewTaskToFile(task);
    }

    public void removeTaskInFile(TaskList tasks, int index) throws IOException {
        taskListEncoder.removeTaskInFile(tasks, index - 1);
    }

    public void updateTaskStatus(TaskList tasks, int index) throws IOException {
        taskListEncoder.updateTaskStatusInFile(tasks, index);
    }
}
