package UI;

import Tasks.Task;

import java.util.List;

public class UI {

    public static void dukeGreeting () {
        System.out.println("What can I do for you today?");
    }

    public static void dukeGoodbye () {
        System.out.println("See you later alligator");
    }

    public static void listTask (List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ".");
            tasks.get(i).describePrint();
        }
    }

    public static void doneTask (List<Task> tasks, int taskIndex) {
        System.out.println("This task is done:");
        tasks.get(taskIndex-1).describePrint();
    }

    public static void addedTask (Task addedTask) {
        System.out.println("I've added:");
        addedTask.describePrint();
        System.out.println("You now have " + Task.numberOfTasks + " tasks in the list");
    }

    public static void deletedTask (List<Task> tasks, int taskIndex) {
        System.out.println("I have removed this task:");
        tasks.get(taskIndex - 1).describePrint();
        tasks.remove(taskIndex -1);
        Task.numberOfTasks -= 1;
        System.out.println("You now have " + Task.numberOfTasks + " tasks in the list");
    }

    public static void fileCreated (String input) {
        System.out.println(input + " created for you");
    }

    public static void fileExistsError (String input) {
        System.out.println(input + " already created");
    }

    public static void dukeError (String input) {
        System.out.println(input);
    }
}
