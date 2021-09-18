package herrekt;

import herrekt.taskmanager.Deadline;
import herrekt.taskmanager.Event;
import herrekt.taskmanager.Task;
import herrekt.taskmanager.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws FileNotFoundException {
        List<String> stringList = loadSaveAsStringList(filePath);
        return convertStringListToTaskList(stringList);
    }

    public void save(TaskList tasks) {
        try {
            if (tasks.getSize() == 0) {
                FileWriting.writeToFile("save.txt", "");
            } else {
                StringBuilder toSaveToFile = tasks.convertTaskListToSaveFormat();
                FileWriting.writeToFile("save.txt", toSaveToFile.toString());

            }
        } catch (IOException e) {
            System.out.println("ERROR! FILE NOT FOUND!");
        }
    }

    public List<String> loadSaveAsStringList(String filePath) throws FileNotFoundException {
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
            task = new Deadline(taskInArray[2], taskInArray[3]);
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
