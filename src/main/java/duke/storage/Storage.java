package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDos;
import duke.text.Text;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage extends Text {

    private String filePath = FILE_PATH;

    /**
     * A constructor to save data into text file.
     *
     * @param filePath pathway of file storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a file and directory for storage of task list if there is no existing file.
     *
     * @throws IOException exception thrown when unexpected error occurs when creating file/
     */
    public void createFile() throws IOException {
        Files.createDirectories(Path.of("data")); //create directory data
        Files.createFile(Path.of(filePath)); //create text file to store data
    }

    /**
     * Save all contents from task list by rewriting previous contents.
     *
     * @param taskList user's task list.
     */
    public void saveFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);

            String textToAdd = "";

            for (int i = 0; i < taskList.size(); i++) {
                textToAdd = textToAdd.concat(taskList.getTask(i).storageText() + "\n"); //combine all task in task list
            }
            fw.write(textToAdd); //write all the task to file
            fw.close();
        }
        catch (IOException | DukeException e) {
            Ui.printWithLine(SAVING_ERROR);
        }
    }

    /**
     * Open and read existing storage file with previously saved task list.
     *
     * @return TaskList object.
     * @throws DukeException exception thrown when error opening file.
     */
    public TaskList openFile() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String task = s.nextLine();
                String[] splitTaskString = task.split("\\|");
                switch (splitTaskString[0]) {
                case "T":
                    taskList.addTask(new ToDos(splitTaskString[2]));
                    break;
                case "D":
                    taskList.addTask(new Deadlines(splitTaskString[2], splitTaskString[3]));
                    break;
                case "E":
                    taskList.addTask(new Events(splitTaskString[2], splitTaskString[3]));
                    break;
                }
                if (splitTaskString[1].equals("1")) {
                    taskList.getTask(taskList.size() - 1).markDone();
                }
            }
        }
        catch (FileNotFoundException e) { //if no existing file, then create new file
            try {
                createFile();
            }
            catch (IOException ioe) {
                throw new DukeException(FILE_ERROR);
            }
        }
        return taskList;
    }
}
