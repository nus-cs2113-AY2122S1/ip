package duke.task;

import duke.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    static final String SEPARATOR_FOR_LOAD = " \\| ";
    static final String SEPARATOR_FOR_SAVE = " | ";
    private String filePath;
    private File f;
    private Ui ui;

    /**
     * Constructor for a new storage object that stores the data of the file with the specified file path.
     *
     * @param filePath The path of the storage file.
     */
    public Storage(String filePath) {
        ui = new Ui();
        this.filePath = filePath;
        f = new File(filePath);
    }

    /**
     * Loads the content of the storage file into an ArrayList of strings to
     * feed into the creation of a new task list.
     *
     * @return ArrayList of strings of the content in the storage file in the correct format.
     * @throws IOException If there is an error in the reading in of the file.
     */
    public ArrayList<String> load() throws IOException {
        ArrayList<String> data = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] wordsInOneLine = line.split(SEPARATOR_FOR_LOAD);
                for (int i = 0; i < wordsInOneLine.length; i++) {
                    data.add(wordsInOneLine[i]);
                }
            }
        } catch (FileNotFoundException e) {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
        } finally {
            return data;
        }
    }

    /**
     * Writes the current TaskList running in the application into the file
     * in the specified file path in a certain format for storage.
     *
     * @param tasks List of all the tasks.
     * @param numberOfTasks Total number of tasks.
     * @throws IOException If there is an error in writing to the file.
     */
    public void writeToFile(ArrayList<Task> tasks, int numberOfTasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < numberOfTasks; i++) {
            if (tasks.get(i) instanceof Todo) {
                fw.write("T" + SEPARATOR_FOR_SAVE + tasks.get(i).getisDone()
                        + SEPARATOR_FOR_SAVE + tasks.get(i).getDescription());
            } else if (tasks.get(i) instanceof Deadline) {
                fw.write("D" + SEPARATOR_FOR_SAVE + tasks.get(i).getisDone() +
                        SEPARATOR_FOR_SAVE + tasks.get(i).getDescription()
                        + SEPARATOR_FOR_SAVE + ((Deadline) tasks.get(i)).getBy());
            } else { //the final Task type can only be an event
                fw.write("E" + SEPARATOR_FOR_SAVE + tasks.get(i).getisDone() +
                        SEPARATOR_FOR_SAVE + tasks.get(i).getDescription()
                        + SEPARATOR_FOR_SAVE + ((Event) tasks.get(i)).getAt());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Saves the data of the current TaskList running in the application into the file
     * in the specified file path.
     *
     * @param tasks List of all the tasks.
     * @param numberOfTasks Total number of tasks.
     */
    public void save(ArrayList<Task> tasks, int numberOfTasks) {
        try {
            writeToFile(tasks, numberOfTasks);
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e);
        } catch (IOException e) {
            ui.showLoadingError(e);
        }
    }

}
