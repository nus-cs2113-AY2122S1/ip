package duke.command;

import duke.Duke;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;

public class Command {

    public static void printByeMessage() {
        System.out.println("Bye! I hope to see you again soon :)");
    }

    public static void listTasks() {
        System.out.println("Here are your current tasks and their status:");
        for (Task task : Duke.tasks) {
            System.out.println((Duke.tasks.indexOf(task) + 1) + ". " + task);
        }
    }

    public static void addTodo(String description) throws IOException {
        Duke.tasks.add(Task.getTaskCount(), new Todo(description));
        System.out.println("Okay, I've added that task to your list:");
        System.out.println(Duke.tasks.get(Task.getTaskCount() - 1));
        Storage.saveData();
    }

    public static void addDeadline(String description, String by) throws IOException {
        Duke.tasks.add(Task.getTaskCount(), new Deadline(description, by));
        System.out.println("Okay, I've added that task to your list:");
        System.out.println(Duke.tasks.get(Task.getTaskCount() - 1));
        Storage.saveData();
    }

    public static void addEvent(String description, String at) throws IOException {
        Duke.tasks.add(Task.getTaskCount(), new Event(description, at));
        System.out.println("Okay, I've added that task to your list:");
        System.out.println(Duke.tasks.get(Task.getTaskCount() - 1));
        Storage.saveData();
    }

    public static void doneTask(int taskIndex) throws IOException {
        Duke.tasks.get(taskIndex - 1).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(Duke.tasks.get(Task.getTaskCount() - 1));
        Storage.saveData();
    }

    public static void deleteTask(int taskIndex) throws IOException {
        System.out.println("Okay, I've deleted that task!");
        System.out.println(Duke.tasks.get(taskIndex - 1));
        Duke.tasks.remove(taskIndex - 1);
        Storage.saveData();
    }

}
