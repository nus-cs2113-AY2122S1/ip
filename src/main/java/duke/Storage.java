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

public class Storage {
    public final static int TYPE_INDEX = 0;
    public final static int COMPLETION_INDEX = 1;
    public final static int DESC_INDEX = 2;
    public final static int TIME_INDEX = 3;

    public File filepath;
    public boolean fileExists;
    public boolean newFileCreated = false;
    public Ui ui = new Ui();

    // Constructor
    public Storage(File myFile) {
        this.filepath = myFile;
        fileExists = checkFileExists();

        if (!fileExists) {
            newFileCreated = createFile();
            ui.printFileCreationNotice();
        }
    }

    public boolean checkFileExists() {
        if (filepath.exists()) {
            return true;
        }
        return false;
    }


    public boolean createFile() {
        try {
            return filepath.createNewFile();
        } catch (IOException e) {
            ui.printFileError();
            return false;
        }
    }

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
