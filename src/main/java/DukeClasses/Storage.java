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


/**
 * Deals with operations involving loading data from the file, or saving data into the file
 */
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

    /**
     * Creates a new Deadline or Event task from the file
     * @param taskInfo line in the file describing a task
     * @return either a deadline or event task depending on the first letter
     */
    private Task createNewDeadlineOrEvent(String taskInfo) {
        int indexOfColon = taskInfo.indexOf(":");
        int indexOfOpenBracket = taskInfo.indexOf("(");
        int indexOfCloseBracket = taskInfo.indexOf(")");
        if (taskInfo.startsWith("D")) {
            return new Deadline(taskInfo.substring(8, indexOfOpenBracket - 1), taskInfo.substring(indexOfColon + 2, indexOfCloseBracket));
        }
        return new Event(taskInfo.substring(8, indexOfOpenBracket - 1), taskInfo.substring(indexOfColon + 2, indexOfCloseBracket));
    }

    /**
     * Returns the Todo task description
     * @param taskInfo the full line describing a todo task in the file
     * @return String representing todo description
     */
    private String getTodoTaskDescFromFile(String taskInfo) {
        return taskInfo.substring(8);
    }

    /**
     * Returns true if task is marked as done in txt file
     * @param taskInfo the entire line description of a task in txt file
     * @return true if task is done according to txt file
     */
    private boolean IsDoneFromFile(String taskInfo) {
        return taskInfo.charAt(4) == '1';
    }

    /**
     * Writes and appends to the file containing updated data
     * @param tasks TaskList containing updated data
     * @param filePath file path
     * @throws IOException
     */
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
        String fullTask = getFullTaskDesc(currentTask);

        fw.write(taskType + " | " + isDone + " | " + fullTask + System.lineSeparator());


        //subsequent lines append
        for (int i = 1; i < tasks.list.size(); i++) {
            currentTask = tasks.list.get(i);
            taskType = getTaskType(currentTask);
            isDone = (currentTask.getStatus()) ? 1 : 0;
            fullTask = getFullTaskDesc(currentTask);
            fa.write(taskType + " | " + isDone + " | " + fullTask + System.lineSeparator());
        }

        fw.close();
        fa.close();
    }

    /**
     * Returns the full task description of any one task
     * @param currentTask a Task object
     * @return the full description of a task, including the by/at for deadlines/events
     */
    private String getFullTaskDesc(Task currentTask) {
        return currentTask.toString().substring(taskStartIndex);
    }

    /**
     * Returns an ArrayList containing all the data from the file
     * @param filePath the file path
     * @return ArrayList containing the data from the file
     * @throws FileNotFoundException if file does not exist
     */
    public ArrayList<Task> readData(String filePath) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {

            String taskInfo = s.nextLine();

            Task newTask;
            if (taskInfo.startsWith("T")) {
                newTask = new Todo(getTodoTaskDescFromFile(taskInfo));

            } else {
                newTask = createNewDeadlineOrEvent(taskInfo);
            }
            tasks.add(newTask);
            if (IsDoneFromFile(taskInfo)) {
                newTask.markAsDone();
            }
        }
        return tasks;
    }
}
