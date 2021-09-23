package alfred.storage;

import alfred.exception.FileErrorException;
import alfred.task.Deadline;
import alfred.task.Event;
import alfred.task.TaskList;
import alfred.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
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
        String taskDateString;
        LocalDate taskDate;
        switch (taskType) {
        case "T":
            taskList.addTask(new Todo(taskDescription));
            break;
        case "E":
            taskDateString = destructuredInputs[3];
            taskDate = LocalDate.parse(taskDateString);
            taskList.addTask(new Event(taskDescription, taskDate));
            break;
        case "D":
            taskDateString = destructuredInputs[3];
            taskDate = LocalDate.parse(taskDateString);
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
