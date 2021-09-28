package DukeClasses;

import DukeClasses.TaskList;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    private static char TODO_TASK = 'T';
    private static char DEADLINE_TASK = 'D';
    private static char EVENT_TASK = 'E';
    private static int taskStartIndex = 7; //7 is the starting index for the actual task, in the data file

    public File dukeData = new File("Duke.txt");

    private char getTaskType(Task task) {
        if (task instanceof Todo) {
            return TODO_TASK;
        }
        if (task instanceof Deadline) {
            return DEADLINE_TASK;
        }
        return EVENT_TASK;
    }

    //creates a new deadline or event, depending on what the line starts with
    private Task createNewDeadlineOrEvent(String taskInfo) {
        int indexOfColon = taskInfo.indexOf(":");
        int indexOfOpenBracket = taskInfo.indexOf("(");
        int indexOfCloseBracket = taskInfo.indexOf(")");
        if (taskInfo.startsWith("D")) {
            return new Deadline(taskInfo.substring(8, indexOfOpenBracket - 1), taskInfo.substring(indexOfColon + 2, indexOfCloseBracket));
        }
        return new Event(taskInfo.substring(8, indexOfOpenBracket - 1), taskInfo.substring(indexOfColon + 2, indexOfCloseBracket));
    }

    private String fileGetTodoTask(String taskInfo) {
        return taskInfo.substring(8);
    }

    private boolean fileTaskIsDone(String taskInfo) {
        return taskInfo.charAt(4) == '1';
    }

    public void updateData(TaskList tasks, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath); //to write first line
        FileWriter fa = new FileWriter(filePath, true); //append the rest

        if (tasks.list.isEmpty()) {
            fw.close();
            fa.close();
            return;
        }

        //WRITING to file for first line
        Task currentTask = tasks.list.get(0);
        char taskType = getTaskType(currentTask);
        int isDone = (currentTask.getStatus()) ? 1 : 0;
        String fullTask = getTaskInStringFromFile(currentTask);

        fw.write(taskType + " | " + isDone + " | " + fullTask + System.lineSeparator());


        //subsequent lines append
        for (int i = 1; i < tasks.list.size(); i++) {
            currentTask = tasks.list.get(i);
            taskType = getTaskType(currentTask);
            isDone = (currentTask.getStatus()) ? 1 : 0;
            fullTask = getTaskInStringFromFile(currentTask);
            fa.write(taskType + " | " + isDone + " | " + fullTask + System.lineSeparator());
        }

        fw.close();
        fa.close();
    }

    private String getTaskInStringFromFile(Task currentTask) {
        return currentTask.toString().substring(taskStartIndex);
    }

    public ArrayList<Task> readData(String filePath) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {

            String taskInfo = s.nextLine();

            Task newTask;
            if (taskInfo.startsWith("T")) {
                newTask = new Todo(fileGetTodoTask(taskInfo));

            } else {
                newTask = createNewDeadlineOrEvent(taskInfo);
            }
            tasks.add(newTask);
            if (fileTaskIsDone(taskInfo)) {
                newTask.markAsDone();
            }
        }
        return tasks;
    }
}
