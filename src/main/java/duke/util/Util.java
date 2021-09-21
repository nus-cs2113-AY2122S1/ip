package duke.util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Util {
    /**
     * Gets the Path object from filename.
     *
     * @param filename The filename.
     * @return A Path object.
     */
    public static Path getPathFromFilename(String filename) {
        String separator = FileSystems.getDefault().getSeparator();
        String[] filenameSplit = filename.split(separator.replace("\\", "\\\\"));

        String firstPathString = filenameSplit[0];
        Path path;
        if (filenameSplit.length > 1) {
            String[] additionalPathStrings = Arrays.copyOfRange(filenameSplit, 1, filenameSplit.length - 1);
            path = Paths.get(firstPathString, additionalPathStrings);
        } else {
            path = Paths.get(firstPathString);
        }

        return path;
    }

    /**
     * Checks if file exists.
     *
     * @param filename The file to check.
     * @return true if exists, else false.
     */
    public static boolean fileExists(String filename) {
        Path path = getPathFromFilename(filename);

        return Files.exists(path);
    }

    /**
     * Creates a file and parent directories (if necessary).
     *
     * @param filename The file to create.
     * @return true if file already exists or successfully created, else false.
     */
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

    /**
     * Checks if string value is an integer.
     *
     * @param string The string to check.
     * @return true if string can be converted to integer, else false.
     */
    public static boolean isStringInteger(String string) {
        boolean isInt;
        try {
            int value = Integer.parseInt(string);
            isInt = true;
        } catch (NumberFormatException e) {
            isInt = false;
        }

        return isInt;
    }

    /**
     * Returns LocalDateTime object in the specified format.
     *
     * @param dateTime The LocalDateTime object.
     * @param format   The format to return.
     * @return The formatted dateTime.
     */
    public static String getFormattedDateTime(LocalDateTime dateTime, String format) {
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * Converts from string to LocalDateTime.
     *
     * @param string The string to convert.
     * @param format The format of the string.
     * @return A LocalDateTime object if successful, else null.
     */
    public static LocalDateTime getDateTimeFromString(String string, String format) {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(string, DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            dateTime = null;
        }

        return dateTime;
    }
}
