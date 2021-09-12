package duke.task;

import duke.exceptions.DukeException;
import duke.exceptions.ExceptionChecker;
import java.util.ArrayList;

public class TaskManager {

    private static final ExceptionChecker CHECKER = new ExceptionChecker();
    private static int currentTasksCount = 0;

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber <= currentTasksCount && taskNumber > 0);
    }

    public static int getCurrentTasksCount() {
        return currentTasksCount;
    }

    public void clearAllTasks() {
        tasks.clear();
        currentTasksCount = 0;
    }

    public Task addTodo(String taskDescription) {
        tasks.add(new ToDo(taskDescription));
        currentTasksCount++;
        return tasks.get(currentTasksCount - 1);
    }

    public Task addDeadline(String taskDescription, String taskDue) {
        tasks.add(new Deadline(taskDescription, taskDue));
        currentTasksCount++;
        return tasks.get(currentTasksCount - 1);
    }

    public Task addEvent(String taskDescription, String eventDateTime) {
        tasks.add(new Event(taskDescription, eventDateTime));
        currentTasksCount++;
        return tasks.get(currentTasksCount - 1);
    }

    public void listTasks() {
        for (int i = 0; i < currentTasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public Task deleteTask(int taskIndex) {
        Task deletedTask = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        currentTasksCount--;
        return deletedTask;
    }

    public Task markTaskDone(int taskIndex) {
        Task doneTask = tasks.get(taskIndex - 1);
        doneTask.setDone();
        return doneTask;
    }

    public void convertTasksToData() throws DukeException {

        ArrayList<String> fileLines = new ArrayList<>();

        for (int i = 0; i < currentTasksCount; i++) {
            fileLines.add(tasks.get(i).toData() + "\n");
        }

        CHECKER.tryToWriteFile(fileLines);
    }

    public void convertDataToTasks() throws DukeException {

        ArrayList<String> fileLines = CHECKER.tryToReadFile();
        String data;
        String[] dataArray;

        for (String line : fileLines) {
            data = line;
            dataArray = data.split(" \\| ");
            switch (dataArray[0]) {
            case "T":
                addTodo(dataArray[2]);
                break;
            case "D":
                addDeadline(dataArray[2], dataArray[3]);
                break;
            case "E":
                addEvent(dataArray[2], dataArray[3]);
                break;
            }
            if (dataArray[1].equals("1")) {
                markTaskDone(currentTasksCount);
            }
        }
    }
}
