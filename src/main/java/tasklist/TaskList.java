package tasklist;

import exceptions.DeadlineException;
import exceptions.EventException;
import storage.Storage;
import jarvis.Jarvis;
import parser.Parser;
import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private static final String LINE = Ui.LINE;
    private static final String LINE_W_NL = Ui.LINE_W_NL;
    public static void listTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            throw new ArithmeticException();
        } else {
            System.out.println(LINE_W_NL + "Here are the tasks in your list Sir:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    public static void markTaskDone(String userLine, ArrayList<Task> taskList) {
        int taskNum = Parser.parseDoneTask(userLine, taskList);
        taskList.get(taskNum-1).markAsDone();
        Ui.printMarkTaskDoneMessage(taskList, taskNum);
        Storage.fillJarvisFile(taskList);
    }

    public static void deleteTask(String userLine, ArrayList<Task> taskList) {
        int taskNum = Parser.parseDeleteTask(userLine, taskList);
        Ui.printDeleteMessage(taskList, taskNum);
        taskList.remove(taskNum-1);
        Storage.fillJarvisFile(taskList);
    }

    public static void addTodoTask(String userLine, ArrayList<Task> taskList) {
        String description = Parser.parseTodoTask(userLine);
        Todo t = new Todo(description);
        taskList.add(t);
        Storage.fillJarvisFile(taskList);
    }

    public static void addDeadlineTask(String userLine, ArrayList<Task> taskList) throws DeadlineException {
        String[] deadlineDescriptions = Parser.parseDeadlineTask(userLine);
        Deadline t = new Deadline(deadlineDescriptions[0], Parser.parseDate(deadlineDescriptions[1]));
        taskList.add(t);
        Storage.fillJarvisFile(taskList);
    }

    public static void addEventTask(String userLine, ArrayList<Task> taskList) throws EventException {
        String[] eventDescriptions = Parser.parseEventTask(userLine);
        Event t = new Event(eventDescriptions[0], Parser.parseDate(eventDescriptions[1]));
        taskList.add(t);
        Storage.fillJarvisFile(taskList);
    }

    public static void addTodoFileTask(String description, ArrayList<Task> taskList) {
        Todo t = new Todo(description);
        taskList.add(t);
    }

    public static void addDeadlineFileTask(String description, String by, ArrayList<Task> taskList) {
        Deadline t = new Deadline(description, Parser.parseStoredDate(by));
        taskList.add(t);
    }

    public static void addEventFileTask(String description, String at, ArrayList<Task> taskList) {
        Event t = new Event(description, Parser.parseStoredDate(at));
        taskList.add(t);
    }
}
