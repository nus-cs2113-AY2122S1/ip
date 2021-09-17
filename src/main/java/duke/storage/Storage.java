package duke.storage;

import duke.exception.CorruptedDataException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    public static final String TASK_DATA_PATH = "data/tasks.txt";

    /**
     * Returns a Task ArrayList based on data read from the data storage file. Creates the
     * data storage folder if there is no existing one.
     *
     * @return an ArrayList of Tasks corresponding to the data storage file
     */
    public ArrayList<Task> loadFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        File dataFile = new File("data");
        dataFile.mkdir();
        try {
            decodeFileContent(taskList);
            System.out.println("Previous data loaded successfully");
        } catch (FileNotFoundException e) {
            System.out.println("No existing data");
        } catch (CorruptedDataException e) {
            System.out.println("Data file corrupted...");
        }
        return taskList;
    }

    /**
     * Reads and parses the data storage file. Then writes the decoded content into the
     * given Task ArrayList.
     *
     * @param taskList the ArrayList to be written into
     * @throws FileNotFoundException  when no existing data file is found
     * @throws CorruptedDataException when the existing data file has corrupted data
     */
    public void decodeFileContent(ArrayList<Task> taskList) throws FileNotFoundException, CorruptedDataException {
        File f = new File(TASK_DATA_PATH);
        Scanner dataScanner = new Scanner(f);
        while (dataScanner.hasNext()) {
            String[] data = dataScanner.nextLine().split(" \\| ");
            boolean isTaskDone = data[1].equals("1");
            switch (data[0]) {
            case Todo.SYMBOL:
                taskList.add(new Todo(data[2]));
                if (isTaskDone) {
                    taskList.get(Task.getTotalTasks() - 1).setDone(true);
                }
                break;

            case Deadline.SYMBOL:
                taskList.add(new Deadline(data[2], data[3]));
                if (isTaskDone) {
                    taskList.get(Task.getTotalTasks() - 1).setDone(true);
                }
                break;

            case Event.SYMBOL:
                taskList.add(new Event(data[2], data[3]));
                if (isTaskDone) {
                    taskList.get(Task.getTotalTasks() - 1).setDone(true);
                }
                break;

            default:
                throw new CorruptedDataException();
            }
        }
    }

    /**
     * Updates the data storage file to that of the given Task ArrayList
     *
     * @param taskList the Task ArrayList that needs to be written into the data storage file
     */
    public void updateDataFile(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(TASK_DATA_PATH);
            //clear data file before write
            fw.write("");
            fw.close();
            for (Task task : taskList) {
                task.writeToFile(TASK_DATA_PATH);
            }
        } catch (IOException e) {
            System.out.println("Failed to update data file");
            System.exit(0);
        }
    }

}
