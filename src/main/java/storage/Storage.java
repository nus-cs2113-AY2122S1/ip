package storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String input) {
        filePath = input;
    }

    /**
     * If the file "duke.txt" exists, reads the file.
     * If the file "duke.txt" does not exist, creates the file.
     */
    public void readFile() {
        try {
            Path dataFilePath = Paths.get("data/");
            Files.createDirectories(dataFilePath);

            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("New file created " + filePath);
            }
        } catch (IOException e) {
            System.out.println("IO exception error unable to create file " + filePath);
        }
    }

    /**
     * Reads the content of the saved file.
     *
     * @return List of task from the txt file.
     */
    public ArrayList<Task> loadData() {
        TaskList taskList = new TaskList();
        try {
            File file = new File(filePath);
            Scanner readFile = new Scanner(file);
            int numTaskAdded = 0;

            while (readFile.hasNext()) {
                try {
                    String[] savedData = readFile.nextLine().split(" \\| ");
                    String command;
                    switch (savedData[0]) {
                    case "T":
                        command = "todo " + savedData[2];
                        taskList.addToDoTask(command);
                        break;
                    case "D":
                        command = "deadline " + savedData[2] + " /by " + savedData[3];
                        taskList.addDeadlineTask(command);
                        break;
                    case "E":
                        command = "event " + savedData[2] + " /at " + savedData[3];
                        taskList.addEventTask(command);
                        break;
                    default:
                        throw new DukeException("Unable to parse file: " + filePath);
                    }
                    if (savedData[1].equals("1")) {
                        taskList.storageDoneTask(numTaskAdded);
                    }
                    numTaskAdded++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Unable to parse line " + (numTaskAdded + 1) + " of " + filePath);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
            Ui.printTasks(taskList.getTasks());
        } catch (IOException e) {
            System.out.println("IO exception error when loading data " + filePath);
        }
        return taskList.getTasks();
    }

    /**
     * Saves the current list of tasks in the file.
     *
     * @param tasks List of tasks to write to save file
     */
    public void saveData(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                String textToWrite;
                int isDone = task.isDone() ? 1 : 0;
                String description = task.getDescription();

                if (task instanceof ToDo) {
                    textToWrite = "T | " + isDone + " | " + description;
                } else if (task instanceof Deadline) {
                    textToWrite = "D | " + isDone + " | " + description + " | " + ((Deadline) task).getDeadlineBy();
                } else if (task instanceof Event) {
                    textToWrite = "E | " + isDone + " | " + description + " | " + ((Event) task).getEventTime();
                } else {
                    fw.close();
                    throw new DukeException("Invalid task instance: " + task);
                }
                fw.write(textToWrite + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving your data. Please try again");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
