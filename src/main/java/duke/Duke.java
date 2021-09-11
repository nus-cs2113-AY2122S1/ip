package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.Task;

import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    public static final String HORIZONTAL_LINE = "_____________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        printHello();
        task();
        printBye();
    }

    private static void printHello() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void task() {
        Scanner in = new Scanner(System.in);
        String inputCommand = in.nextLine();
        while (!(inputCommand.equals("bye"))) {

            try {
                if (inputCommand.equals("list")) {
                    showList();
                } else if (inputCommand.contains("done")) {
                    markAsDone(inputCommand);
                } else if (inputCommand.contains("todo")) {
                    addToDo(inputCommand);
                } else if (inputCommand.contains("deadline")) {
                    addDeadline(inputCommand);
                } else if (inputCommand.contains("event")) {
                    addEvent(inputCommand);
                }
                else{
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(HORIZONTAL_LINE);
            }
            inputCommand = in.nextLine();
        }
    }

    private static void addEvent(String inputCommand) {
        System.out.println(HORIZONTAL_LINE);
        try {
            String description = inputCommand.substring(6);
            String[] parts = description.split("/at");
            String eventDescription = parts[0];
            String timingDescription = parts[1];
            tasks.add(new Event(eventDescription, timingDescription));
            gotItMessage();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addDeadline(String inputCommand) {
        System.out.println(HORIZONTAL_LINE);
        try {
            String description = inputCommand.substring(9);
            String[] parts = description.split("/by");
            String taskDescription = parts[0];
            String deadlineDescription = parts[1];
            tasks.add(new Deadline(taskDescription, deadlineDescription));
            gotItMessage();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addToDo(String inputCommand) {
        System.out.println(HORIZONTAL_LINE);
        try {
            String taskToDo = inputCommand.substring(5);
            tasks.add(new ToDo(taskToDo));
            gotItMessage();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void gotItMessage() {
        System.out.println("Got it!! I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void markAsDone(String inputCommand) {
        System.out.println(HORIZONTAL_LINE);
        try {
            int position = inputCommand.indexOf(" ");
            int taskNum = Integer.parseInt(inputCommand.trim().substring(position + 1));
            tasks.get(taskNum - 1).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNum - 1).getDescription().trim());
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please enter a number after done.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void showList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
