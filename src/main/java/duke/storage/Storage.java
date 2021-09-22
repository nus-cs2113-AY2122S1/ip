package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.ui.Message;
import duke.ui.Ui;

public class Storage {
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private static final String STORAGE_DIRECTORY = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final Path PATH_TO_FILE = Paths.get(ROOT_DIRECTORY, STORAGE_DIRECTORY, FILE_NAME);

    public Storage() {}

    /** Creates a new data file. */
    public void createNewData(Ui ui) {
        try {
            File file = new File(PATH_TO_FILE.toString());
            boolean isDirectoryCreated = file.getParentFile().mkdirs();
            boolean isFileCreated = file.createNewFile();

            if (!isDirectoryCreated || !isFileCreated) {
                throw new IOException(Message.ERROR_CREATING_FILE);
            }
        } catch (IOException e) {
            ui.printMessage(Message.ERROR_CREATING_FILE);
        }
    }

    /**
     * Loads task data from a saved file.
     *
     * @return Data stored in a list of strings.
     * @throws IOException If an I/O error occurs.
     */
    public List<String> loadData() throws IOException {
        try {
            FileReader fin = new FileReader(PATH_TO_FILE.toString());
            BufferedReader bin = new BufferedReader(fin);
            List<String> data = new ArrayList<>();
            String line;
            while ((line = bin.readLine()) != null) {
                data.add(line);
            }
            bin.close();
            return data;
        } catch (IOException e) {
            throw new IOException(Message.ERROR_RETRIEVING_DATA);
        }
    }

    /**
     * Saves task data to a file.
     *
     * @param data Task data.
     */
    public void saveData(String data) throws IOException {
        try {
            FileWriter fout = new FileWriter(PATH_TO_FILE.toString());
            BufferedWriter bout = new BufferedWriter(fout);
            bout.write(data);
            bout.close();
        } catch (IOException e) {
            throw new IOException(Message.ERROR_SAVING_DATA);
        }
    }
}
