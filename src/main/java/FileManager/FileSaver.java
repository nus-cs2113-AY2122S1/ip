package FileManager;

import tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>Save users' tasks</h1>
 * <p>This FileSaver provides methods to save users' tasks.</p>
 *
 */
public class FileSaver {
    private Path filePath;

    public FileSaver (String userName) {
        String fileName = userName + ".txt";
        filePath = Paths.get("UserStatus", fileName);
    }

    /**
     * Saves the user's tasks into a <em>txt</em> file under <b><em>UserStatus</em></b> directory.
     * @param {@link TaskList} a TaskList object stores all the remaining tasks of a user.
     * @return void.
     */
    public void save (TaskList tasks) {
        File directory = new File("UserStatus");
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            FileWriter writer = new FileWriter(filePath.toString());
            writer.write(tasks.save());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
