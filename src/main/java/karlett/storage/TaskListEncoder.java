package karlett.storage;

import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;
import karlett.tasklist.TaskList;

import java.io.*;

public class TaskListEncoder {

    private File file;
    private String filePath;


    public TaskListEncoder(String path) {
        filePath = path;
        file = new File(filePath);
    }

    public void removeTaskInFile(TaskList tasks, int index) throws IOException {
        String fileContent = "";
        String line = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        reader = new BufferedReader(new FileReader(filePath));
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            line = reader.readLine();
            if (i == index) {
                continue;
            }
            fileContent = fileContent + line + System.lineSeparator();
        }
        writer = new FileWriter(filePath);
        writer.write(fileContent);
        reader.close();
        writer.close();
    }

    public void updateTaskStatusInFile(TaskList tasks, int index) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        String line = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        reader = new BufferedReader(new FileReader(filePath));
        for (int i = 0; i < index; i++) {
            line = reader.readLine();
            fileContent.append(line).append(System.lineSeparator());
        }
        line = reader.readLine();
        String updatedTask = line.replaceFirst("0", "1");
        fileContent.append(updatedTask).append(System.lineSeparator());
        for (int i = index + 1; i < tasks.getNumberOfTasks(); i++) {
            line = reader.readLine();
            fileContent.append(line).append(System.lineSeparator());
        }
        writer = new FileWriter(filePath);
        writer.write(fileContent.toString());
        reader.close();
        writer.close();
    }

    public void appendNewTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String textToAppend;
        if (task instanceof Deadline) {
            textToAppend = "D | 0 | " + ((Deadline) task).getDescription() +
                    " | " + ((Deadline) task).getBy() + "\n";
        } else if (task instanceof Event) {
            textToAppend = "E | 0 | " + ((Event) task).getDescription() +
                    " | " + ((Event) task).getAt() + "\n";
        } else {
            textToAppend = "T | 0 | " + task.getDescription() + "\n";
        }
        fw.write(textToAppend);
        fw.close();
    }
}
