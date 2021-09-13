package storage;

import duke.DukeException;
import duke.task.TaskManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static TaskManager readFile(String filePath) throws IOException, DukeException {
        File file = new File(filePath);
        if (file.createNewFile()) {
            System.out.println("New file created " + filePath);
            return new TaskManager();
        }

        Scanner s = new Scanner(file);
        TaskManager taskManager = new TaskManager();
        int taskAdded = 1;
        while (s.hasNext()) {
            try {
                String[] savedData = s.nextLine().split(" \\| ");
                String command;
                switch (savedData[0]) {
                case "T":
                    command = "todo " + savedData[2];
                    taskManager.addToDoTask(command);
                    break;
                case "D":
                    command = "deadline " + savedData[2] + " /by " + savedData[3];
                    taskManager.addDeadlineTask(command);
                    break;
                case "E":
                    command = "event " + savedData[2] + " /at " + savedData[3];
                    taskManager.addEventTask(command);
                    break;
                default:
                    throw new DukeException("Unable to parse file: " + filePath);
                }
                if (savedData[1].equals("1")) {
                    taskManager.storageDoneTask(taskAdded);
                }
                taskAdded++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Unable to parse file at line " + taskAdded + " of " + filePath);
            }
        }
        taskManager.printTask();
        return taskManager;
    }

}
