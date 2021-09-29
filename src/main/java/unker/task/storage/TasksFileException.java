package unker.task.storage;

import java.nio.file.Path;

public class TasksFileException extends Exception {

    private final Path filePath;
    private final String description;

    public TasksFileException(Path filePath, String description) {
        super(description);
        this.filePath = filePath;
        this.description = description;
    }

    /**
     * Get the file path causing the exception.
     * @return The file path causing the exception.
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * Get the details about the exception.
     * @return The details about the exception.
     */
    public String getDescription() {
        return description;
    }
}
