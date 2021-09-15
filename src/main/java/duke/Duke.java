package duke;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;

public class Duke {
    public static final int FIND_TASK_TODO = 5;
    public static final int FIND_TASK_DEADLINE = 9;
    public static final int FIND_TASK_EVENT = 6;
    public static final int FIND_TIME = 3;
    public static ArrayList<List> list = new ArrayList<>();
    public static ArrayList<List> doneList = new ArrayList<>();
    private static final String SPLIT_LINE  = "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    public static void main(String[] args) throws IOException {
        showWelcomeScreen();
        readFiles();
        printList();
        printDoneList();
        while (true) {
            Scanner in = new Scanner(System.in);
            String order;
            order = in.nextLine();
            if (order.contains("bye")) {
                try {
                    createListFile();
                    createDoneListFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                terminateProgram();
                break;
            } else if (order.contains("done")) {
                try {
                    doneTask(order);
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println(SPLIT_LINE);
                    System.out.println("    ☹ OOPS!!! The index of a done command cannot be empty.");
                    System.out.println(SPLIT_LINE);
                    continue;
                }
            } else if (order.contains("list")) {
                printList();
            } else if(order.contains("delete")) {
                try {
                    int index = Integer.parseInt(order.substring(order.indexOf(" ") + 1));
                    if (index > list.size()) {
                        System.out.println(SPLIT_LINE);
                        System.out.println("    Out of range. Please try again");
                        System.out.println(SPLIT_LINE);
                        continue;
                    }
                    System.out.println(SPLIT_LINE);
                    System.out.println("    Noted. I've removed this task:\n    " + list.get(index - 1));
                    list.remove(index - 1);
                    System.out.println("    Now you have " + list.size() + " tasks in the list.");
                    System.out.println(SPLIT_LINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    ☹ OOPS!!! The description of a delete cannot be empty! Please try again.");
                    System.out.println(SPLIT_LINE);
                    continue;
                }
            } else {
                if (order.contains("todo")) {
                    try {
                        String task = order.substring(order.indexOf("todo") + FIND_TASK_TODO);
                        list.add(new Todo(task));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(SPLIT_LINE);
                        System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(SPLIT_LINE);
                        continue;
                    }
                } else if (order.contains("event")) {
                    try {
                        String task = order.substring(order.indexOf("event")
                            + FIND_TASK_EVENT, order.indexOf("/") - 1);
                        String at = order.substring(order.indexOf("at") + FIND_TIME);
                        list.add(new Event(task,at));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(SPLIT_LINE);
                        System.out.println("    ☹ OOPS!!! The description of an event cannot be empty.");
                        System.out.println(SPLIT_LINE);
                        continue;
                    }
                } else if (order.contains("deadline")) {
                    try {
                        String task = order.substring(order.indexOf("deadline")
                            + FIND_TASK_DEADLINE, order.indexOf("/") - 1);
                        String by = order.substring(order.indexOf("by") + FIND_TIME);
                        list.add(new Deadline(task, by));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(SPLIT_LINE);
                        System.out.println("    ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(SPLIT_LINE);
                        continue;
                    }
                } else {
                    try {
                        invalidInput(true);
                    } catch (DukeException e) {
                        System.out.println(SPLIT_LINE);
                        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(SPLIT_LINE);
                        continue;
                    }
                }
                System.out.println(SPLIT_LINE);
                System.out.println("    Got it. I've added this task:");
                System.out.println("     " + list.get(list.size() - 1));
                System.out.println("    Now you have " + list.size() + " task(s) in the list.");
                System.out.println(SPLIT_LINE);
            }
        }

    }

    private static void showWelcomeScreen() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(SPLIT_LINE + "\n    Hello! I'm Duke\n"
            + "    What can I do for you?\n" + SPLIT_LINE);
    }

    public static void readFiles() throws IOException {
        try {
            File listFile = new File("data/dukeList.txt");
            File listDoneFile = new File("data/dukeDoneList.txt");
            BufferedReader bufferList = new BufferedReader(new FileReader(listFile));
            BufferedReader bufferDoneList = new BufferedReader(new FileReader(listDoneFile));
            String str;
            while ((str = bufferList.readLine()) != null) {
                String[] tasks = str.split(",");
                for (int i = 0; i < tasks.length; i++) {
                    String description = tasks[i].substring(8);
                    if (i == tasks.length - 1) {
                        description = description.substring(0, description.length() - 1);
                    }
                    switch (tasks[i].substring(2, 3)) {
                    case "T":
                        if (tasks[i].contains("X")) {
                            Todo todo = new Todo(description);
                            todo.description = todo.description.replaceFirst(" ", "X");
                            todo.isDone = true;
                            list.add(todo);
                        } else {
                            list.add(new Todo(description));
                        }
                        continue;
                    case "D":
                        String by = tasks[i].substring(tasks[i].indexOf(":") + 2, tasks[i].indexOf(")"));
                        if (tasks[i].contains("X")) {
                            Deadline deadline = new Deadline(description.substring(0, description.indexOf("(")), by);
                            deadline.description = deadline.description.replaceFirst(" ", "X");
                            deadline.isDone = true;
                            list.add(deadline);
                        } else {
                            list.add(new Deadline(description.substring(0, description.indexOf("(")), by));
                        }
                        continue;
                    case "E":
                        String at = tasks[i].substring(tasks[i].indexOf(":") + 2, tasks[i].indexOf(")"));
                        if (tasks[i].contains("X")) {
                            Event event = new Event(description.substring(0, description.indexOf("(")), at);
                            event.description = event.description.replaceFirst(" ", "X");
                            event.isDone = true;
                            list.add(event);
                        } else {
                            list.add(new Event(description.substring(0, description.indexOf("(")), at));
                        }
                        continue;
                    default:
                        System.out.println("There are errors in reading in files.");
                    }
                }
            }

            while ((str = bufferDoneList.readLine()) != null) {
                String[] tasks = str.split(",");
                for (int i = 0; i < tasks.length; i++) {
                    String description = tasks[i].substring(8);
                    if (i == tasks.length - 1) {
                        description = description.substring(0, description.length() - 1);
                    }
                    switch (tasks[i].substring(2, 3)) {
                    case "T":
                        Todo todo = new Todo(description);
                        todo.description = todo.description.replaceFirst(" ", "X");
                        todo.isDone = true;
                        doneList.add(todo);
                        continue;
                    case "D":
                        String by = tasks[i].substring(tasks[i].indexOf(":") + 2, tasks[i].indexOf(")"));
                        Deadline deadline = new Deadline(description.substring(0, description.indexOf("(")), by);
                        deadline.description = deadline.description.replaceFirst(" ", "X");
                        deadline.isDone = true;
                        doneList.add(deadline);
                        continue;
                    case "E":
                        String at = tasks[i].substring(tasks[i].indexOf(":") + 2, tasks[i].indexOf(")"));
                        Event event = new Event(description.substring(0, description.indexOf("(")), at);
                        event.description = event.description.replaceFirst(" ", "X");
                        event.isDone = true;
                        doneList.add(event);
                        continue;
                    default:
                        System.out.println("There are errors in reading in files.");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No files saved. Please start using Duke now!");
        }
    }
    public static void terminateProgram() {
        System.out.println(SPLIT_LINE);
        System.out.println("    Bye. Hope to see you again soon!\n" + SPLIT_LINE);
    }

    public static void printList() {
        System.out.println(SPLIT_LINE + "\n    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("    " + (i + 1) + "." + list.get(i));
        }
        System.out.println(SPLIT_LINE);
    }

    public static void printDoneList() {
        System.out.println("    Congratulations! Now you have done " + doneList.size() + " task(s):");
        for (int i = 0; i < doneList.size(); i++) {
            System.out.println("    " + (i + 1) + "." + doneList.get(i));
        }
        System.out.println(SPLIT_LINE);
    }

    public static void doneTask(String task) {
        task = task.trim();
        String str = "";
        if (task != null && !"".equals(task)) {
            for (int i = 0; i < task.length(); i++) {
                if (task.charAt(i) >= 48 && task.charAt(i) <= 57) {
                    str = str + task.charAt(i);
                }
            }
        }
        int index = Integer.parseInt(str) - 1;
        if (index >= list.size()) {
            System.out.println(SPLIT_LINE);
            System.out.println("    Out of range. Please try again");
            System.out.println(SPLIT_LINE);
            return;
        } else {
            list.get(index).isDone = true;
            list.get(index).description = list.get(index).description.replaceFirst(" ", "X");
            doneList.add(list.get(index));
            System.out.println(SPLIT_LINE);
            System.out.println("    Nice! I've marked this task as done:\n    "
                + list.get(index));
            System.out.println(SPLIT_LINE);
            printDoneList();
        }
    }

    public static void createListFile() throws IOException {
        File dukeData = new File("data/dukeList.txt");
        if (!dukeData.exists()) {
            dukeData.createNewFile();
        }
        FileWriter writer = new FileWriter(dukeData);
        BufferedWriter out = new BufferedWriter(writer);
        out.write(String.valueOf(list));
        out.flush();
        out.close();
    }

    public static void createDoneListFile() throws IOException {
        File dukeDoneData = new File("data/dukeDoneList.txt");
        if (!dukeDoneData.exists()) {
            dukeDoneData.createNewFile();
        }
        FileWriter writer = new FileWriter(dukeDoneData);
        BufferedWriter out = new BufferedWriter(writer);
        out.write(String.valueOf(doneList));
        out.flush();
        out.close();
    }

    public static void invalidInput(boolean isInValid) throws DukeException{
        if (isInValid) {
            throw new DukeException();
        }
    }
}