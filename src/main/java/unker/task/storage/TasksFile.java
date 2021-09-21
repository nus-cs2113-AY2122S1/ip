package unker.task.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import unker.task.Deadline;
import unker.task.Event;
import unker.task.Task;
import unker.task.ToDo;
import unker.util.StringUtil;

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
     * @throws IOException When the file is inaccessible.
     */
    public List<Task> loadDataFile() throws IOException {
        Path filePath = getFilePath();

        final Stream<Task> taskStream = Files.lines(filePath).map(this::decodeTaskString);
        final Stream<Task> nonNullTaskStream = taskStream.filter(Objects::nonNull);
        return nonNullTaskStream.collect(Collectors.toCollection(ArrayList::new));
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
     * @throws IOException When the file to be saved to is inaccessible.
     */
    public void saveDataFile(List<Task> tasks) throws IOException {
        Path filePath = getFilePath();
        Stream<String> tasksDataStream = tasks.stream().map(Task::getSaveableString);
        String data = tasksDataStream.reduce((chunk, line) -> chunk + "\n" + line).orElse("");
        Files.writeString(filePath, data);
    }

    private Task decodeTaskString(String taskString) {
        String[] taskComponents = taskString.split(",", FILE_LINE_COMPONENTS);
        if (taskComponents.length < FILE_LINE_COMPONENTS) {
            return null;
        }
        boolean isDone = !taskComponents[1].equals("0");
        switch(taskComponents[0].toUpperCase()) {
        case "T":
            ToDo toDo = new ToDo(taskComponents[2]);
            toDo.setDone(isDone);
            return toDo;
        case "D":
            Matcher deadlineMatcher = StringUtil.parseUserInput(Deadline.DEADLINE_DATA_PATTERN, taskComponents[2]);
            if (deadlineMatcher != null) {
                Deadline deadline = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
                deadline.setDone(isDone);
                return deadline;
            } else {
                return null;
            }
        case "E":
            Matcher eventMatcher = StringUtil.parseUserInput(Event.EVENT_DATA_PATTERN, taskComponents[2]);
            if (eventMatcher != null) {
                Event event = new Event(eventMatcher.group(1), eventMatcher.group(2));
                event.setDone(isDone);
                return event;
            } else {
                return null;
            }
        }

        return null;
    }
}
