package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {

    public static final String HORIZONTAL = "____________________________________________________________\n";

    public static final String TO_DO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    public static final int INDEX_OF_TO_DO_TASK = 4;
    public static final int INDEX_OF_DEADLINE_TASK = 8;
    public static final int INDEX_OF_EVENT_TASK = 5;
    public static final int INDEX_OF_TASK_DONE = 4;
    public static final int INDEX_OF_TASK_DELETED = 6;

    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void deleteFile(String filePath) throws IOException {
        Files.delete(Paths.get(filePath));
    }

    public static void sayHello() {
        System.out.println(HORIZONTAL + "Hello! I'm Duke\n" + "What can I do for you?\n" + HORIZONTAL);
    }

    public static void sayBye() {
        System.out.println(HORIZONTAL + "Bye. Hope to see you again soon!\n" + HORIZONTAL);
    }

    public static void addToDo(String line) throws DukeException {
        String task = line.trim().substring(INDEX_OF_TO_DO_TASK);
        if (task.equals("")) {
            throw new DukeException();
        }
        tasks.add(new ToDo(task));
        System.out.println(HORIZONTAL + "Got it. I've added this task: \n  " + tasks.get(tasks.size() - 1) +
                "\n" + "Now you have " + tasks.size() + " task" + (tasks.size() == 1? " " : "s ") +
                "in the list. \n" + HORIZONTAL);
    }

    public static void addDeadline(String line) throws DukeException {
        if (!line.contains("/by")) {
            throw new DukeException();
        }
        int slash = line.indexOf("/by");
        String task = line.trim().substring(INDEX_OF_DEADLINE_TASK, slash);
        if (task.equals("")) {
            throw new DukeException();
        }
        String dueDate = line.trim().substring(slash + 3);
        if (dueDate.equals("")) {
            throw new DukeException();
        }
        tasks.add(new Deadline(task, dueDate));
        System.out.println(HORIZONTAL + "Got it. I've added this task: \n  " + tasks.get(tasks.size() - 1) +
                "\n" + "Now you have " + tasks.size() + " task" + (tasks.size() == 1? " " : "s ") +
                "in the list. \n" + HORIZONTAL);
    }

    public static void addEvent(String line) throws DukeException {
        if (!line.contains("/at")) {
            throw new DukeException();
        }
        int slash = line.indexOf("/at");
        String task = line.trim().substring(INDEX_OF_EVENT_TASK, slash);
        if (task.equals("")) {
            throw new DukeException();
        }
        String eventTime = line.trim().substring(slash + 3);
        if (eventTime.equals("")) {
            throw new DukeException();
        }
        tasks.add(new Event(task, eventTime));
        System.out.println(HORIZONTAL + "Got it. I've added this task: \n  " + tasks.get(tasks.size() - 1) +
                "\n" + "Now you have " + tasks.size() + " task" + (tasks.size() == 1? " " : "s ") +
                "in the list. \n" + HORIZONTAL);
    }

    public static void addTask(String line) {
        if (line.contains(TO_DO)) {
            try {
                addToDo(line);
            } catch (DukeException e) {
                System.out.println(HORIZONTAL + "☹ OOPS!!! The description of a todo cannot be empty. \n" +
                        HORIZONTAL);
            }
        } else if (line.contains(DEADLINE)) {
            try {
                addDeadline(line);
            } catch (DukeException e) {
                System.out.println(HORIZONTAL + "☹ OOPS!!! A component of the deadline task is empty. \n" +
                        HORIZONTAL);
            }
        } else if (line.contains(EVENT)) {
            try {
                addEvent(line);
            } catch (DukeException e) {
                System.out.println(HORIZONTAL + "☹ OOPS!!! A component of the event task is empty. \n" +
                        HORIZONTAL);
            }
        } else {
            System.out.println(HORIZONTAL + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + HORIZONTAL);
        }
    }

    public static void listTasks() {
        System.out.print(HORIZONTAL + "Here are the tasks in your list: \n");
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + "." + task);
        }
        System.out.println(HORIZONTAL);
    }

    public static void markDone(int taskDone) {
        tasks.get(taskDone - 1).markAsDone();
        System.out.println(HORIZONTAL + "Nice! I've marked this task as done: \n  "
                + tasks.get(taskDone - 1) + "\n" + HORIZONTAL);
    }

    public static void delete(int taskDeleted) {
        System.out.println(HORIZONTAL + "Noted. I've removed this task: \n  " + tasks.get(taskDeleted - 1));
        tasks.remove(taskDeleted - 1);
        System.out.println("Now you have " + tasks.size() + " task"
                + (tasks.size() == 1? " " : "s ") + "in the list. \n" + HORIZONTAL);
    }

    public static void readCommand(String line) {
        if (line.equals("list")) {
            listTasks();
        } else if (line.contains("done")) {
            int taskDone = Integer.parseInt(line.substring(INDEX_OF_TASK_DONE).trim());
            markDone(taskDone);
        } else if (line.contains("delete")) {
            int taskDeleted = Integer.parseInt(line.substring(INDEX_OF_TASK_DELETED).trim());
            delete(taskDeleted);
        } else {
            addTask(line);
        }
    }

    public static String readTasks() {
        StringBuilder listOfTasks = new StringBuilder();
        for (Task task : tasks) {
            listOfTasks.append(task.toText()).append("\n");
        }
        return listOfTasks.toString();
    }

    public static void main(String[] args) {
        sayHello();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String dataFile = "data/duke.txt";
        while (!line.equals("bye")) {
            readCommand(line);
            try {
                writeToFile(dataFile, readTasks());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            line = in.nextLine();
        }
        sayBye();
        try {
            deleteFile(dataFile);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
