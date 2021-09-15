package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Manages the loading and saving of the list of user tasks to a text file, saveDuke.txt, in a folder in the same
 * directory, /data.
 */
public class Storage {
    public static final String PATHNAME = Paths.get("data/saveDuke.txt").toString();

    /**
     * Tries to load the save file from PATHNAME. If the directory does not exist, creates the folder /data in the
     * same directory. If the file does not exist, creates the file saveDuke.txt within /data. If the file exists, the
     * function loads the Tasks into the given list.
     * @param userTasks The list of Tasks that the Tasks are loaded into.
     */
    public Storage(TaskList userTasks) {
        File load = new File(PATHNAME);

        if (!load.getParentFile().exists()) {
            load.getParentFile().mkdir();
        }

        try {
            Scanner loadScanner = new Scanner(load);
            String currTask;

            while (loadScanner.hasNext()) {
                currTask = loadScanner.nextLine();
                String[] atomArray = currTask.split(" \\| ");
                if (atomArray[1].equals("T")) {
                    userTasks.taskList.add(new Todo(atomArray[2]));
                } else if (atomArray[1].equals("D")) {
                    userTasks.taskList.add(new Deadline(atomArray[2], atomArray[3]));
                } else {
                    userTasks.taskList.add(new Event(atomArray[2], atomArray[3]));
                }
                if (atomArray[0].equals("[X]")) {
                    userTasks.taskList.get(Task.getTotalTasks() - 1).markComplete();
                }
            }
            System.out.println(UI.DIVIDING_LINE);
            System.out.println("Previous session restored.");
        } catch (DukeException e) {
            System.out.println("DukeException:" + e.getMessage());
        } catch (FileNotFoundException e) {
            try {
                load.createNewFile();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the specified list of Tasks to a text file.
     * @param userTasks The list of Tasks to be saved.
     */
    public void saveTasks(TaskList userTasks) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            output.append(userTasks.taskList.get(i).saveString());
            output.append(System.lineSeparator());
        }
        try {
            FileWriter writer = new FileWriter(PATHNAME);
            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
