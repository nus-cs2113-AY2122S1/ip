package duke.data;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static void load(String filePath) throws IOException {
        File newFile = new File(filePath);
        Scanner scanner = new Scanner(newFile);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] array = line.split(" | ");
            switch (array[0]) {
            case "T":
                TaskManager.addToDo(array[2]);
                break;
            case "D":
                TaskManager.addDeadline(array[2], array[3]);
                break;
            case "E":
                TaskManager.addEvent(array[2], array[3]);
                break;
            }
        }
    }


    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void writeToFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.createNewFile()) {
            System.out.println("File created");
        }
        String textToAppend;
        for (Task task: TaskManager.taskList) {
            String taskType = task.getIcon();
            String status = task.getStatus();
            String description = task.getDescription();
            String timing = task.getTime();

            textToAppend = taskType + " | " + status + " | " + description;
            if (task instanceof Event || task instanceof Deadline) {
                textToAppend += " | " + timing;
            }
            textToAppend += "\n";
            appendToFile(filePath, textToAppend);
        }
    }



}
