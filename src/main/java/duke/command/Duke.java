package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.Scanner;


public class Duke {
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
        for (int i = index + 1; i < words.length; i++) {
            by = by + words[i] + " ";
        }
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
        for (int i = index + 1; i < words.length; i++) {
            at = at + words[i] + " ";
        }
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
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
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
