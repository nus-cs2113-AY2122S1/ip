package triss;

import triss.exception.TrissException;
import triss.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    TaskList tasklist = new TaskList();

    public Storage() {
        // Check for any stored data
        try {
            initialiseDataStorage();
        } catch (TrissException error) {
            System.out.println(error.getMessage());
        } catch (IOException error) {
            System.out.println("Tasks storage has been corrupted! Try restarting.");
        }
    }

    private void initialiseDataStorage() throws IOException, TrissException {
        File dataDirectory = new File("data");
        File storedTasks = new File("data/storedtasks.txt");
        if (dataDirectory.exists()) {
            Scanner fileReader = new Scanner(storedTasks);
            while (fileReader.hasNext()) {
                String lineInFile = fileReader.nextLine();
                String[] taskDetails = lineInFile.split(",");
                switch (taskDetails[0]) {
                case "[T]":
                    tasklist.createNewTodo("todo " + taskDetails[2], true);
                    break;
                case "[E]":
                    tasklist.createNewEvent("event " + taskDetails[2] + " /" + taskDetails[3], true);
                    break;
                case "[D]":
                    tasklist.createNewDeadline("deadline " + taskDetails[2] + " /" + taskDetails[3], true);
                    break;
                default:
                    throw new TrissException("Tasks storage has been corrupted! Try restarting.");
                }

                if (taskDetails[1].equals("[X]")) {
                    tasklist.getTaskByIndex(tasklist.getSize() - 1).setDone(true);
                }

            }
        } else {
            dataDirectory.mkdir();
            FileWriter fw = new FileWriter(storedTasks.getAbsoluteFile());
            fw.close();
        }
    }

    public void saveTasks() {

        try {
            FileWriter fw = new FileWriter("data/storedtasks.txt");

            for (Task task : tasklist.getTasks()) {
                fw.write(task.printTaskForStoring() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException error) {
            System.out.println("Tasks storage has been corrupted! Try restarting.");
        }

    }
}
