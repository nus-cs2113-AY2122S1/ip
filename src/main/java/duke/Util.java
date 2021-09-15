package duke;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Util {
    public static Path getPathFromFilename(String filename) {
        String separator = FileSystems.getDefault().getSeparator();
        String[] filenameSplit = filename.split(separator.replace("\\","\\\\"));

        Path path;
        if (filenameSplit.length > 1) {
            path = Paths.get(filenameSplit[0], Arrays.copyOfRange(filenameSplit, 1, filenameSplit.length - 1));
        } else {
            path = Paths.get(filenameSplit[0]);
        }

        return path;
    }

    public static boolean fileExists(String filename) {
        Path path = getPathFromFilename(filename);

        return Files.exists(path);
    }

    public static boolean createFile(String filename) {
        if (fileExists(filename)) {
            return true;
        }

        Path path = getPathFromFilename(filename);
        Path parentPath = path.getParent();

        try {
            Files.createDirectories(parentPath);
            Files.createFile(path);
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}
