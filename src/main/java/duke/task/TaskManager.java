package duke.task;

import duke.util.FileManager;

import java.util.ArrayList;

public class TaskManager {

    private static final FileManager FILE_MANAGER = new FileManager();
    private static final int MAX_TASKS_COUNT = 100;
    private static int currentTasksCount = 0;

    private static Task[] tasks = new Task[MAX_TASKS_COUNT];

    public static boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber <= currentTasksCount && taskNumber > 0);
    }

    public static int getCurrentTasksCount() {
        return currentTasksCount;
    }

    public void clearAllTasks() {
        tasks = new Task[MAX_TASKS_COUNT];
        currentTasksCount = 0;
    }

    public Task addTodo(String taskDescription) {
        tasks[currentTasksCount] = new ToDo(taskDescription);
        currentTasksCount++;
        return tasks[currentTasksCount - 1];
    }

    public Task addDeadline(String taskDescription, String taskDue) {
        tasks[currentTasksCount] = new Deadline(taskDescription, taskDue);
        currentTasksCount++;
        return tasks[currentTasksCount - 1];
    }

    public Task addEvent(String taskDescription, String eventDateTime) {
        tasks[currentTasksCount] = new Event(taskDescription, eventDateTime);
        currentTasksCount++;
        return tasks[currentTasksCount - 1];
    }

    public void listTasks() {
        for (int i = 0; i < currentTasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
    }

    public Task markTaskDone(int taskIndex) {
        tasks[taskIndex - 1].setDone();
        return tasks[taskIndex - 1];
    }

    public void convertTasksToData() {
        String data;
        boolean append = false;
        for (int i = 0; i < currentTasksCount; i++) {
            data = tasks[i].toData() + "\n";
            FILE_MANAGER.writeFile(data, append);
            append = true;
        }
    }

    public void convertDataToTasks() {

        ArrayList<String> contentArray = FILE_MANAGER.readFile();
        String data;
        String[] dataArray;

        for (int i = 0; i < contentArray.size(); i++) {

            data = contentArray.get(i);
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
