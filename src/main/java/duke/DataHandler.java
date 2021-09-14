package duke;

import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataHandler {
    private final String STORAGE_PATH = "data/tasks.csv";
    private final File file = new File(STORAGE_PATH);

    public DataHandler() throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Data file can't be found.");
        }
    }

    /**
     * Loads data from csv file into the array list
     * @param taskManager Manages the tasks after loading data
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public void loadData(TaskManager taskManager) throws DukeException {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task = readTask(scanner.nextLine());
                taskManager.addTasks(task,false);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("☹ OOPS!!! Error loading data!");
        }
    }

    /**
     * Reads in data from csv file, breaks it down and stores them in task object
     * @param data Data read in from csv file
     * @return task Returns task created from data read from csv file
     */
    private Task readTask(String data) {
        Task task = null;
        String[] taskBreakdown = data.split(",");
        if (taskBreakdown[2].contains("|")) {
            taskBreakdown[2] = taskBreakdown[2].replace("|", ",");
        }
        switch (taskBreakdown[0]) {
        case "T":
            task = new ToDos(taskBreakdown[2]);
            break;
        case "D":
            task = new Deadline(taskBreakdown[2], taskBreakdown[3]);
            break;
        case "E":
            task = new Events(taskBreakdown[2], taskBreakdown[3]);
            break;
        default:
            System.out.println("☹ OOPS!!! Error reading data!");
            break;
        }
        if (taskBreakdown[1].equals("[X]") && task != null) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Saves Tasks from arraylist tasklist into csv file
     * @param taskManager Manages the tasks after loading data
     * @throws DukeException Throws exception to aid in identifying errors
     */
    public void saveData(TaskManager taskManager) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(STORAGE_PATH);
            for (Task task : taskManager.getTasks()) {
                if (task != null) {
                    fileWriter.write(task.convertToCSV() + System.lineSeparator());
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Error saving data!");
        }
    }

}
