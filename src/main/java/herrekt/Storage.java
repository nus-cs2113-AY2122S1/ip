package herrekt;

import herrekt.taskmanager.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Storage {

    private final String filePath;

    /**
     * Initialize the Storage class as an instant.
     *
     * @param filePath directory and name of the file to load from and save on.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("save.txt");
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Returns a list of tasks obtained from the save.txt file.
     *
     * @return a list of tasks
     * @throws FileNotFoundException  If save.txt does not exist.
     */
    public List<Task> load() throws FileNotFoundException {
        List<String> stringList = loadSaveAsStringList(filePath);
        List<Task> tasks = convertStringListToTaskList(stringList);
        return tasks;
    }

    /** Save the current tasks kept recorded as a txt file called save.txt. */
    public void save(TaskList tasks) {
        try {
            if (tasks.getSize() == 0) {
                writeToFile("");
            } else {
                StringBuilder toSaveToFile = tasks.convertTaskListToSaveFormat();
                writeToFile(toSaveToFile.toString());

            }
        } catch (IOException e) {
            System.out.println("ERROR! FILE NOT FOUND!");
        }
    }

    private List<String> loadSaveAsStringList(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        List<String> taskInStringList = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            taskInStringList.add(sc.nextLine());
        }
        return taskInStringList;
    }

    private List<Task> convertStringListToTaskList(List<String> stringList) {
        return stringList.stream()
                .map(y -> convertSavedStringToTask(y))
                .collect(toList());
    }

    private Task convertSavedStringToTask(String taskInString) {
        Task task;
        String[] taskInArray = taskInString.split(" / ");
        switch (taskInArray[0]) {
        case "T":
            task = new Todo(taskInArray[2]);
            break;
        case "D":
            task = Parser.dateConverter(taskInArray[2], taskInArray[3]);
            break;
        case "E":
            task = new Event(taskInArray[2], taskInArray[3]);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskInArray[0]);
        }
        if (taskInArray[1].equals("1")) {
            task.setDone();
        }
        return task;
    }
}
