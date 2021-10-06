package duke.constants;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class that contains configurations of Storage.
 */
public class StorageConfig {

    public static final String PROJECT_ROOT = System.getProperty("user.dir");
    public static final String STORAGE_FOLDER = "data";
    public static final String FILE_NAME = "duke.txt";
    public static final Path PATH_TO_STORAGE_FOLDER = Paths.get(PROJECT_ROOT, STORAGE_FOLDER);
    public static final Path PATH_TO_STORAGE_FILE = Paths.get(PROJECT_ROOT, STORAGE_FOLDER, FILE_NAME);

}
