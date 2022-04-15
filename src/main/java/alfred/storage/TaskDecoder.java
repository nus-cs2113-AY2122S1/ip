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
    /**
     * This method scans the entire given taskFile and compiles the found Tasks in a format which is compatible with
     * the chat-bot.
     * @param taskFile The File to be scanned
     * @return TaskList This returns a TaskList with all the pre-existing Tasks
     * @throws FileErrorException If there are errors reading from local file
     */
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

    /**
     * This method constructs the chat-bot compatible TaskList from lines of input provided saved.
     * @param taskList TaskList object to construct upon
     * @param input Saved input line to be deciphered
     * @param taskIndex Index of Task in TaskList for marking Task as done
     */
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
