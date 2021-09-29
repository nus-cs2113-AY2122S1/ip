package unker.task.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import unker.task.Task;
import unker.task.TaskFactory;

public class TasksFile {

    private static final int FILE_LINE_COMPONENTS = 3;
    private final Path directory;
    private final String fileName;

    public TasksFile(Path directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
    }

    /**
     * Load a data CSV file and return all the tasks from it.
     * 
     * Any line that does not follow the convention will be silently ignored.
     * 
     * @return A list of {@link unker.task.Task} 
     * @throws TasksFileException When the file is inaccessible.
     */
    public List<Task> loadDataFile() throws TasksFileException {
        try {
            Path filePath = getFilePath();
            final Stream<Task> taskStream = Files.lines(filePath).map(this::decodeTaskString);
            final Stream<Task> nonNullTaskStream = taskStream.filter(Objects::nonNull);
            return nonNullTaskStream.collect(Collectors.toCollection(ArrayList::new));
        } catch (IOException e) {
            throw new TasksFileException(directory.resolve(fileName),  "Unable to load data file:\n " + e);
        }
    }

    private Path getFilePath() throws IOException {
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        Path filePath = directory.resolve(fileName);
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        return filePath;
    }

    /**
     * Save the data into a CSV file.
     * 
     * @param tasks The tasks to be saved.
     * @throws TasksFileException When the file to be saved to is inaccessible.
     */
    public void saveDataFile(List<Task> tasks) throws TasksFileException {
        try {
            Path filePath = getFilePath();
            Stream<String> tasksDataStream = tasks.stream().map(Task::getSaveableString);
            String data = tasksDataStream.reduce((chunk, line) -> chunk + "\n" + line).orElse("");
            Files.writeString(filePath, data);
        } catch (IOException e) {
            throw new TasksFileException(directory.resolve(fileName), "Unable to save data file:\n" + e);
        }
    }

    private Task decodeTaskString(String taskString) {
        String[] taskComponents = taskString.split(",", FILE_LINE_COMPONENTS);
        if (taskComponents.length < FILE_LINE_COMPONENTS) {
            return null;
        }
        
        Task task;
        
        switch(taskComponents[0].toUpperCase()) {
        case "T":
            task = TaskFactory.createToDoTask(taskComponents[2]);
            break;
        case "D":
            System.out.println(taskComponents[2]);
            task = TaskFactory.createDeadlineTask(taskComponents[2]);
            break;
        case "E":
            task = TaskFactory.createEventTask(taskComponents[2]);
            break;
        default:
            task = null;
        }
        
        if (task != null) {
            boolean isDone = !taskComponents[1].equals("0");
            task.setDone(isDone);
        }
        
        return task;
    }
}
