package duke.common;

import duke.parser.Parser;
import duke.task.Task;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Implements reading from and writing to the data file saved on the user's local disk under the given filepath.
 */
public class Storage {

    private static final String FILE_NAME = "duke.txt";
    private static final String DIR_NAME = "data";
    private static final String FILE_PATH = DIR_NAME + "/" + FILE_NAME;

    /**
     * Opens access to the data file if it exists, and creates one otherwise. Notifies the user if an error occurred
     * while doing so.
     */
    public static void startDuke() throws IOException {
            if (Files.notExists(Paths.get(FILE_PATH))) {
                Files.createDirectories(Paths.get(FILE_PATH).getParent());
                Files.createFile(Paths.get(FILE_PATH));
                Ui.printNewHello();
            } else {
                loadData();
                Ui.printReturningHello(TaskList.tasks);
            }
    }

    private static void loadData() throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            TaskList.tasks.add(Parser.parseTaskFromData(s.nextLine()));
        }
    }

    /**
     * Writes the current tasklist to the data file.
     *
     * @throws IOException if an error occurred writing to the data file
     */
    public static void saveData() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : TaskList.tasks) {
            fw.write(task.toStorageString() + "\n");
        }
        fw.close();
    }
}
