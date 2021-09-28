package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<String> stringList = new ArrayList<>();
    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static ArrayList<String> formattedDueDateList = new ArrayList<>();
    protected static ArrayList<String> dueDateList = new ArrayList<>();

    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    public int getListCount() {
        return taskList.size();
    }

    public void addToTaskList(Task task) {
        taskList.add(task);
    }

    public void addToStringList(String string) {
        stringList.add(string);
    }

    public void addToFormattedDueDateList(String dueDate) {
        formattedDueDateList.add(dueDate);
    }

    public void addToDueDateList(String dueDate) {
        dueDateList.add(dueDate);
    }

    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

    public static ArrayList<String> getStringList() {
        return stringList;
    }

    public static ArrayList<String> getDueDateList() {
        return dueDateList;
    }

    public void markAsCompleted(int taskIndex) {
        taskList.get(taskIndex).markAsDone();
    }

    public static void printCompletedTask(String line) {
        String taskNumber = line.substring(5);
        int taskListElement = Integer.parseInt(taskNumber) - 1;
        taskList.get(taskListElement).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskList.get(taskListElement).getDescription());
        Ui.printLine();
    }

    public static void printTaskList() {
        int taskCount = 1;
        for (Task task : taskList) {
            System.out.println(taskCount + "." + task);
            taskCount++;
        }
        Ui.printLine();
    }

    public static Todo getToDoTask(String line) {
        char taskType = line.toUpperCase().charAt(0);
        String taskDisplay = line.substring(5);

        stringList.add(taskList.size(), taskDisplay);
        dueDateList.add(taskList.size(),null);
        formattedDueDateList.add(taskList.size(), null);
        Todo todoTask = new Todo(taskDisplay, taskType);
        taskList.add(taskList.size(), todoTask);
        return todoTask;
    }

    public static Deadline getDeadlineTask(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(9, startingIndex);
        char taskType = line.toUpperCase().charAt(0);

        stringList.add(taskList.size(), taskDisplay);
        dueDateList.add(taskList.size(),line.substring(startingIndex + 1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime formatDateTime = LocalDateTime.parse(line.substring(startingIndex + 1),formatter);
        String formattedDateTime = formatDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        formattedDueDateList.add(taskList.size(), formattedDateTime);
        String doBy = "(" + formattedDateTime + ")";
        Deadline deadlineTask = new Deadline(taskDisplay, taskType, doBy);
        taskList.add(taskList.size(), deadlineTask);
        return deadlineTask;
    }

    public static Event getEventTask(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(6, startingIndex);
        char taskType = line.toUpperCase().charAt(0);

        stringList.add(taskList.size(), taskDisplay);
        dueDateList.add(taskList.size(),line.substring(startingIndex + 1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        LocalDateTime formatDateTime = LocalDateTime.parse(line.substring(startingIndex + 1),formatter);
        String formattedDateTime = formatDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        formattedDueDateList.add(taskList.size(), formattedDateTime);
        String doBy = "(" + formattedDateTime + ")";
        Event eventTask = new Event(taskDisplay, taskType, doBy);
        taskList.add(taskList.size(), eventTask);
        return eventTask;
    }

    public static void printTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        Ui.printLine();
    }

    public static void deleteTask(String line) {
        int taskIndex = Integer.parseInt(line.substring(7)) - 1;
        Task task = taskList.get(taskIndex);
        Ui.printLine();
        taskList.remove(taskIndex);
        dueDateList.remove(taskIndex);
        formattedDueDateList.remove(taskIndex);
        stringList.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        Ui.printLine();
    }

    public void printMatchingTask(String keyword) {
        //ArrayList<Task> matchingTaskList = new ArrayList<>();
        int matchingTaskCount = 0;
        for (int i = 0; i < getListCount(); i++) {
            if (stringList.get(i).contains(keyword)){
                //matchingTaskList.add(taskList.get(i));
                matchingTaskCount++;
                System.out.println(matchingTaskCount + "." + taskList.get(i));
            }
        }
        Ui.printLine();
        if (matchingTaskCount == 0){
            System.out.println("No matching task found.");
            Ui.printLine();
        }
    }
}
