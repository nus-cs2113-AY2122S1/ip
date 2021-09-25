package karlett.storage;

import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;
import karlett.tasklist.TaskList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaskListEncoder {

    public static void removeTaskInFile(int index) throws IOException {
        String fileContent = "";
        String line = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        reader = new BufferedReader(new FileReader(StorageFile.filePath));
        for (int i = 0; i < TaskList.getNumberOfTasks(); i++) {
            line = reader.readLine();
            if (i == index) {
                continue;
            }
            fileContent = fileContent + line + System.lineSeparator();
        }
        writer = new FileWriter(StorageFile.filePath);
        writer.write(fileContent);
        reader.close();
        writer.close();
    }

    public static void modifyTaskStatusInFile(int index) throws IOException {
        String fileContent = "";
        String line = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        reader = new BufferedReader(new FileReader(StorageFile.filePath));
        for (int i = 0; i < index; i++) {
            line = reader.readLine();
            fileContent = fileContent + line + System.lineSeparator();
        }
        line = reader.readLine();
        String updatedTask = line.replaceFirst("0", "1");
        fileContent = fileContent + updatedTask + System.lineSeparator();
        for (int i = index + 1; i < TaskList.getNumberOfTasks(); i++) {
            line = reader.readLine();
            fileContent = fileContent + line + System.lineSeparator();
        }
        writer = new FileWriter(StorageFile.filePath);
        writer.write(fileContent);
        reader.close();
        writer.close();
    }

    public static void appendNewTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(StorageFile.filePath, true);
        String textToAppend = "T | 0 | " + task.getDescription() + "\n";
        fw.write(textToAppend);
        fw.close();
    }

    public static void appendNewDeadlineToFile(Deadline deadline) throws IOException {
        FileWriter fw = new FileWriter(StorageFile.filePath, true);
        String textToAppend = "D | 0 | " + deadline.getDescription() +
                " | " + deadline.getBy() + "\n";
        fw.write(textToAppend);
        fw.close();
    }

    //@Override
    public static void appendNewEventToFile(Event event) throws IOException {
        FileWriter fw = new FileWriter(StorageFile.filePath, true);
        String textToAppend = "E | 0 | " + event.getDescription() +
                " | " + event.getAt() + "\n";
        fw.write(textToAppend);
        fw.close();
    }
}
