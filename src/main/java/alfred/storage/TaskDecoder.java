package alfred.storage;

import alfred.exception.FileErrorException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.TaskList;
import alfred.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static alfred.storage.Storage.SEPARATOR;

public class TaskDecoder {
    public static TaskList scanTasks(File taskFile) throws FileErrorException {
        TaskList scannedTaskList = new TaskList();
        int taskIndex = 0;
        try {
            Scanner fileScanner = new Scanner(taskFile);
            while (fileScanner.hasNext()) {
                String inputLine = fileScanner.nextLine();
                decodeAndLoadTask(scannedTaskList, inputLine, taskIndex);
                taskIndex++;
            }
        } catch (FileNotFoundException e) {
            throw new FileErrorException();
        }
        return scannedTaskList;
    }

    private static void decodeAndLoadTask(TaskList taskList, String input, int taskIndex) {
        String[] destructuredInputs = input.split(SEPARATOR);
        String taskType = destructuredInputs[0];
        String taskStatus = destructuredInputs[1];
        String taskDescription = destructuredInputs[2];
        String taskDate;
        switch (taskType) {
        case "T":
            taskList.addTask(new Todo(taskDescription));
            break;
        case "E":
            taskDate = destructuredInputs[3];
            taskList.addTask(new Event(taskDescription, taskDate));
            break;
        case "D":
            taskDate = destructuredInputs[3];
            taskList.addTask(new Deadline(taskDescription, taskDate));
            break;
        default:
            return;
        }
        if (taskStatus.equals("true")) {
            taskList.setTaskDoneInList(taskIndex);
        }
    }
}
