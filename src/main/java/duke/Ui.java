package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Ui {
    private static final String LINE = "─────────────────────────────────────────────────────────────\n";

    public void printByeMessage() {
        String byeGreeting = "Bye. Hope to see you again soon!\n";
        System.out.println(LINE + byeGreeting + LINE);
    }

    public void printHelloMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String helloGreeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE + helloGreeting + LINE);
    }

    public void printErrorMessage(DukeException e) {
        System.out.println(LINE);
        System.out.println(e.getMessage());
        System.out.println(LINE);
    }

    public void printAddTaskMessage(Task task, ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + (tasks.size()) + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printDeleteTaskMessage(int taskIndex, ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(taskIndex));
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printTaskDoneMessage(int taskIndex, ArrayList<Task> tasks) {
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex));
        System.out.println(LINE);
    }

    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println(LINE);
        if (tasks.size() == 0) {
            System.out.println("Task list is empty. Add some tasks in!");
        } else {
            System.out.println("Here are the tasks in your list:");
            tasks.stream()
                    .forEach(System.out::println);
        }
        System.out.println(LINE);
    }

    public void printFindTask(List<Task> matchingTasks) {
        System.out.println(LINE);
        if (matchingTasks.size() == 0) {
            System.out.println("No matching tasks found! ");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            matchingTasks.stream()
                    .forEach(System.out::println);
        }
        System.out.println(LINE);
    }
}
