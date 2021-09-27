package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<String> stringList = new ArrayList<>();
    protected static ArrayList<Task> taskList = new ArrayList<>();
    protected static ArrayList<String> dueDateList = new ArrayList<>();


    public int getListCount() {
        return taskList.size();
    }

    public void addToTaskList(Task task) {
        taskList.add(task);
    }

    public void addToStringList(String string) {
        stringList.add(string);
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
        dueDateList.add(taskList.size(), null);
        Todo todoTask = new Todo(taskDisplay, taskType);
        taskList.add(taskList.size(), todoTask);
        return todoTask;
    }

    public static Deadline getDeadlineTask(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(9, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList.add(taskList.size(), taskDisplay);
        dueDateList.add(taskList.size(), line.substring(startingIndex + 1));
        Deadline deadlineTask = new Deadline(taskDisplay, taskType, doBy);
        taskList.add(taskList.size(), deadlineTask);
        return deadlineTask;
    }

    public static Event getEventTask(String line) {
        int startingIndex = line.indexOf("/");
        String taskDisplay = line.substring(6, startingIndex);
        String doBy = "(" + line.substring(startingIndex + 1) + ")";
        char taskType = line.toUpperCase().charAt(0);

        stringList.add(taskList.size(), taskDisplay);
        dueDateList.add(taskList.size(), line.substring(startingIndex + 1));
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
        stringList.remove(taskIndex);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        Ui.printLine();
    }
}
