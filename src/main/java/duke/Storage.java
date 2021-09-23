package duke;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class is used to read/write data from the given filepath
 */
public class Storage {
    public final static int TYPE_INDEX = 0;
    public final static int COMPLETION_INDEX = 1;
    public final static int DESC_INDEX = 2;
    public final static int TIME_INDEX = 3;

    public File filepath;
    public boolean fileExists;
    public boolean newFileCreated = false;
    public Ui ui = new Ui();

    /**
     * Upon calling, check whether provided filepath is an existing file. If it is not, create the file
     * @param myFile filepath to check whether file already exists
     */
    public Storage(File myFile) {
        this.filepath = myFile;
        fileExists = checkFileExists();

        if (!fileExists) {
            newFileCreated = createFile();
            ui.printFileCreationNotice();
        }
    }

    /**
     * Checks if file exists
     * @return True if file exists, False otherwise
     */
    public boolean checkFileExists() {
        if (filepath.exists()) {
            return true;
        }
        return false;
    }


    /**
     * Creates a new file
     * @return True if file is successfully created, False otherwise
     */
    public boolean createFile() {
        try {
            return filepath.createNewFile();
        } catch (IOException e) {
            ui.printFileError();
            return false;
        }
    }

    /**
     * Writes Task to file. Will append to end of file
     * @param myTask Task to write to file
     */
    public void writeToFile(Task myTask) {
        try {
            String writeData = myTask.toFile();
            FileWriter myWriter = new FileWriter(filepath, true);

            myWriter.write(writeData);
            myWriter.close();
        } catch (IOException e) {
            ui.printWriteError();
        }
    }

    /**
     * Overwrites all existing data in file with Tasks from TaskList
     * @param taskList TaskList which contains the tasks to store in file
     */
    public void updateFile(ArrayList<Task> taskList) {
        try {
            FileWriter myWriter = new FileWriter(filepath);

            for (int i = 0; i < taskList.size(); i++) {
                myWriter.write(taskList.get(i).toFile());
            }
            myWriter.close();
        } catch (IOException e) {
            ui.printWriteError();
        }
    }

    /**
     * Parses the data from file and converts them into Tasks to store into TaskList
     * @return ArrayList of Task that contains all Tasks read from file
     */
    public ArrayList<Task> load() {

        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner readFileInput = new Scanner(filepath);

            while (readFileInput.hasNextLine()) {
                String currTask = readFileInput.nextLine();
                String[] currTaskDetails = currTask.split(",");
                String taskDesc = currTaskDetails[DESC_INDEX];

                if (currTaskDetails[TYPE_INDEX].equals("T")) {
                    ToDo toDo = new ToDo(taskDesc);
                    if (currTaskDetails[COMPLETION_INDEX].equals("X")) {
                        toDo.markAsDone();
                    }
                    taskList.add(toDo);
                }

                else if (currTaskDetails[TYPE_INDEX].equals("D")) {
                    String timing = currTaskDetails[TIME_INDEX];
                    Deadline deadline = new Deadline(taskDesc, timing);
                    if (currTaskDetails[COMPLETION_INDEX].equals("X")) {
                        deadline.markAsDone();
                    }
                    taskList.add(deadline);
                }

                else if (currTaskDetails[TYPE_INDEX].equals("E")) {
                    String timing = currTaskDetails[TIME_INDEX];
                    Event event = new Event(taskDesc, timing);
                    if (currTaskDetails[COMPLETION_INDEX].equals("X")) {
                        event.markAsDone();
                    }
                    taskList.add(event);
                }
            }
        } catch (FileNotFoundException e) {
            ui.printFileNotFoundError();
        }


        return taskList;
    }

}
