package storage;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
        int numTaskAdded = 0;
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
                    taskManager.storageDoneTask(numTaskAdded);
                }
                numTaskAdded++;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Unable to parse line " + (numTaskAdded + 1) + " of " + filePath);
            }
        }
        taskManager.printTask();
        return taskManager;
    }

    public static void writeToFile(String filePath, TaskManager taskManager) throws IOException, DukeException {
        FileWriter fw = new FileWriter(filePath);
        ArrayList<Task> tasks = taskManager.getTasks();
        for (Task task : tasks) {
            String textToWrite;
            int isDone = task.isDone() ? 1 : 0;

            if (task instanceof ToDo) {
                ToDo todo = (ToDo) task;
                textToWrite = "T | " + isDone + " | " + todo.getDescription();
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                textToWrite = "D | " + isDone + " | " + deadline.getDescription()
                        + " | " + deadline.getDeadlineBy();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                textToWrite = "E | " + isDone + " | " + event.getDescription()
                        + " | " + event.getEventTime();
            } else {
                fw.close();
                throw new DukeException("Invalid task instance: " + task);
            }
            fw.write(textToWrite + System.lineSeparator());
        }
        fw.close();
    }

}
