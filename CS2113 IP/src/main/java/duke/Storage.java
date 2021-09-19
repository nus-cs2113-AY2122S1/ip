package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    public Storage(String filePath) throws IOException {
        createDirectory();
        createFile(filePath);
    }

    private static void createDirectory() {
        File f = load();
        boolean isCreated = f.mkdir();
        if (isCreated) {
            System.out.println("data directory created successfully >>::))");
        } else {
            System.out.println("Duke-data directory exists >>::))");
        }
    }

    private static void createFile(String filePath) throws IOException {
        final String HORIZONTAL_LINE = "_________________________________________________________________";

        File f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println("Duke database creation <<>><<>><<>><<>><<>> created " + f.getName());
        } else {
            System.out.println("Duke database up-to-date! <<>><<>><<>><<>><<>> >:)");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static File load() {
        Path currentRelativePath = Paths.get("");
        Path currentPath = currentRelativePath.toAbsolutePath();
        File f = new File(currentPath + "/data");
        return f;
    }
}
