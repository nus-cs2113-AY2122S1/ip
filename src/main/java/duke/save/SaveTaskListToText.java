package duke.save;

import duke.commandHandler.DukeCommandHandling;
import duke.taskType.Deadline;
import duke.taskType.Event;
import duke.taskType.Task;
import duke.taskType.ToDo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SaveTaskListToText {
    public static File directoryOfTaskText = new File("./data");
    public static File dukeTaskText = new File("./data/duke.txt");
    public static int numberOfTasksAdded = 0;

    private static void fileTaskCopy(Task[] tasks) throws IOException {
        Scanner scanText = new Scanner(dukeTaskText); // create a Scanner using the File as the source
        while (scanText.hasNext()) {
            String currentCommand = scanText.nextLine();
            DukeCommandHandling commandHandle = new DukeCommandHandling(currentCommand);

            if (commandHandle.isToDo()) {
                String taskName = currentCommand.substring(5);
                tasks[numberOfTasksAdded] = new ToDo(taskName);
                numberOfTasksAdded += 1;
            } else if (commandHandle.isEvent()) {
                String taskName = currentCommand.substring(6).split("/")[0];
                String at = currentCommand.substring(6).split("/")[1];
                tasks[numberOfTasksAdded] = new Event(taskName, at);
                numberOfTasksAdded += 1;
            } else if (commandHandle.isDeadline()) {
                String taskName = currentCommand.substring(9).split("/")[0];
                String by = currentCommand.substring(9).split("/")[1];
                tasks[numberOfTasksAdded] = new Deadline(taskName, by);
                numberOfTasksAdded += 1;
            }
        }
    }

    /**
     * Checks if directory exist first. If it doesn't, it means the duke.txt is also missing.
     * In such a case, make directory and then create duke.txt file there. Then if directory
     * exist, but duke.txt is missing, add duke.txt.
     *
     * @param
     * @return
     */
    public static int loadTask(Task[] tasks) throws IOException {

        if (directoryOfTaskText.exists() == false) {
            directoryOfTaskText.mkdir();
            dukeTaskText.createNewFile();
        } else if (dukeTaskText.exists() == false) {
            dukeTaskText.createNewFile();
        } else {
            fileTaskCopy(tasks);
        }

        return numberOfTasksAdded;
    }

    public static void saveTask() {

    }

}
