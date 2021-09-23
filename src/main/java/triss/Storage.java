package triss;

import triss.exception.TrissException;
import triss.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

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

    private static void initialiseDataStorage() throws IOException, TrissException {
        File dataDirectory = new File("data");
        File storedTasks = new File("data/storedtasks.txt");
        if (dataDirectory.exists()) {
            Scanner fileReader = new Scanner(storedTasks);
            while (fileReader.hasNext()) {
                String lineInFile = fileReader.nextLine();
                String[] taskDetails = lineInFile.split(",");
                switch (taskDetails[0]) {
                case "[T]":
                    Triss.createNewTodo("todo " + taskDetails[2], true);
                    break;
                case "[E]":
                    Triss.createNewEvent("event " + taskDetails[2] + " /" + taskDetails[3], true);
                    break;
                case "[D]":
                    Triss.createNewDeadline("deadline " + taskDetails[2] + " /" + taskDetails[3], true);
                    break;
                default:
                    throw new TrissException("Tasks storage has been corrupted! Try restarting.");
                }

                if (taskDetails[1].equals("[X]")) {
                    Triss.tasks.get(Triss.tasks.size() - 1).setDone(true);
                }

            }
        } else {
            dataDirectory.mkdir();
            FileWriter fw = new FileWriter(storedTasks.getAbsoluteFile());
            fw.close();
        }
    }

    public static void saveTasks() {

        try {
            FileWriter fw = new FileWriter("data/storedtasks.txt");

            for (Task task : Triss.tasks) {
                fw.write(task.printTaskForStoring() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException error) {
            System.out.println("Tasks storage has been corrupted! Try restarting.");
        }

    }
}
