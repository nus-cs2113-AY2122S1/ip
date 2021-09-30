package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskList;

public class Storage {
    public static final String FILE_PATH = "data";
    private final String fileNamePath;

    public Storage(String filePath) {
        this.fileNamePath = filePath;
    }

    /**
     * load the previous tasks into an arraylist when using the program
     *
     * @return An arraylist that corresponds to the data stored
     */
    public ArrayList<Task> load() throws DukeException {
        final ArrayList<Task> recordedtasks = new ArrayList<>();
        try {
            File f = new File(fileNamePath);
            if (!f.isFile()) {
                throw new FileNotFoundException();
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                recordedtasks.add(Decoder.decoder(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("No previous data");
        }
        return recordedtasks;
    }

    /**
     * Update the stored data whenever there is a changing
     *
     * @param tasks The list of tasks known
     */
    public void changeToFile(TaskList tasks) {
        try {
            File f = new File(fileNamePath);
            File dir = new File(FILE_PATH);
            if (!dir.exists()) {
                boolean isCreated = dir.mkdirs();
                if (!isCreated) {
                    System.out.println("h");
                    throw new IOException();
                }
            }
            if (!f.isFile()) {
                boolean isCreated = f.createNewFile();
                if (!isCreated) {
                    System.out.println("hh");
                    throw new IOException();
                }
            }

            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.getTask(i + 1).store());
                fw.write("\r\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong when storing local file");
        }
    }
}
