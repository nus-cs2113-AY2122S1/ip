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

    private Task readTask(String data) {
        Task task = null;
        String[] taskBreakdown = data.split(",");
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
