package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;


public class Duke {
    private static int loadFromFile(String filePath, Task[] tasks) {
        int taskNumber = 0;
        try {
            File f = new File(filePath);
            if (!f.createNewFile()) {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String task = s.nextLine();
                    String[] words = task.split(" \\| ");
                    if (words[0].equals("T")) {
                        tasks[taskNumber] = new ToDo(Integer.parseInt(words[1]), words[2]);
                        taskNumber++;
                    } else if (words[0].equals("D")) {
                        tasks[taskNumber] = new Deadline(Integer.parseInt(words[1]), words[2], words[3]);
                        taskNumber++;
                    } else if (words[0].equals("E")) {
                        tasks[taskNumber] = new Event(Integer.parseInt(words[1]), words[2], words[3]);
                        taskNumber++;
                    }
                }
            }
            return taskNumber;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return 0;
        }
    }

    private static void saveToFile(String filePath, Task[] tasks, int taskNumber) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskNumber; i++) {
                if (tasks[i].getType().equals("todo")) {
                    fw.write("T" + " | " + tasks[i].getStatusNumber() + " | " + tasks[i].getDescription() + System.lineSeparator());
                } else if (tasks[i].getType().equals("deadline")) {
                    fw.write("D" + " | " + tasks[i].getStatusNumber() + " | " + tasks[i].getDescription() + " | " + tasks[i].getTime() + System.lineSeparator());
                } else if (tasks[i].getType().equals("event")) {
                    fw.write("E" + " | " + tasks[i].getStatusNumber() + " | " + tasks[i].getDescription() + " | " + tasks[i].getTime() + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void listOut(Task[] tasks, int taskNumber) {
        for (int i = 0; i < taskNumber; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    public static void markDone(String line, Task[] tasks) {
        try {
            int index = Integer.parseInt(line.substring(5)) - 1;
            tasks[index].setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].getDescription());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a mark-done cannot be empty.");
        }
    }

    public static boolean addTodo(String line, Task[] tasks, int taskNumber) {
        try {
            tasks[taskNumber] = new ToDo(line.substring(5));
            taskNumber++;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks[taskNumber - 1]);
            System.out.println("Now you have " + taskNumber + " tasks in the list");
            return true;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
            return false;
        }
    }

    public static boolean addDeadline(String line, Task[] tasks, int taskNumber) {
        String[] words = line.split(" ");
        int index = 0;
        String deadlineDescription = "";
        String by = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/by")) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
            return false;
        }
        for (int i = 1; i < index; i++) {
            deadlineDescription = deadlineDescription + words[i] + " ";
        }
        deadlineDescription = deadlineDescription.substring(0, deadlineDescription.length() - 1);
        for (int i = index + 1; i < words.length; i++) {
            by = by + words[i] + " ";
        }
        by = by.substring(0, by.length() - 1);
        tasks[taskNumber] = new Deadline(deadlineDescription, by);
        taskNumber++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskNumber - 1]);
        System.out.println("Now you have " + taskNumber + " tasks in the list");
        return true;
    }

    public static boolean addEvent(String line, Task[] tasks, int taskNumber) {
        String[] words = line.split(" ");
        int index = 0;
        String eventDescription = "";
        String at = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("/at")) {
                index = i;
                break;
            }
        }
        if (index == 0) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
            return false;
        }
        for (int i = 1; i < index; i++) {
            eventDescription = eventDescription + words[i] + " ";
        }
        eventDescription = eventDescription.substring(0, eventDescription.length() - 1);
        for (int i = index + 1; i < words.length; i++) {
            at = at + words[i] + " ";
        }
        at = at.substring(0, at.length() - 1);
        tasks[taskNumber] = new Event(eventDescription, at);
        taskNumber++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks[taskNumber - 1]);
        System.out.println("Now you have " + taskNumber + " tasks in the list");
        return true;
    }

    public static void sayHi() {
        System.out.println("Hello I'm duke.command.Duke");
        System.out.println("What can I do for you?");
    }

    public static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void running() {
        Task[] tasks = new Task[100];
        int taskNumber = 0;
        taskNumber = loadFromFile("data/tasks.txt", tasks);
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                saveToFile("data/tasks.txt", tasks, taskNumber);
                return;
            }
            if (line.equals("list")) {
                listOut(tasks, taskNumber);
            } else if (line.startsWith("done")) {
                markDone(line, tasks);
            } else if (line.startsWith("todo")) {
                if (addTodo(line, tasks, taskNumber)) {
                    taskNumber++;
                }
            } else if (line.startsWith("deadline")) {
                if (addDeadline(line, tasks, taskNumber)) {
                    taskNumber++;
                }
            } else if (line.startsWith("event")) {
                if (addEvent(line, tasks, taskNumber)) {
                    taskNumber++;
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        sayHi();
        running();
        sayBye();
    }
}
