import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.Scanner;

public class TaskManager {
    // array storing all task
    private static Task[] tasks = new Task[100];

    // task counter to enumerate through task array
    private static int tasksCounter = 0;


    public static void manageTasks() {
        //String userInput = "";
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();

            // parse userInput
            while (userInput.trim().isEmpty()) {
                userInput = in.nextLine();
            }
            // change to switch case based on enum
            Commands command = InputParser.parseInput(userInput);

            // if bye, exit straight
            if (command == Commands.BYE) {
                MessagePrinter.exitMessage();
                return;
            }
            // t
            switch (command) {
            case LIST:
                getList();
                break;
            }
            if (userInput.equals("bye")) {
                break;
            }


            // TODO
            if (userInput.startsWith("todo")) {
                addToDo(userInput.substring(userInput.indexOf(" ") + 1));
                continue;
            }

            // DEADLINES
            if (userInput.startsWith("deadline")) {
                String taskName = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/")).trim();
                String deadline = userInput.substring(userInput.indexOf("/") + 3).trim();
                addDeadline(taskName, deadline);
                continue;
            }

            //EVENTS
            if (userInput.startsWith("event")) {
                String taskName = userInput.substring(userInput.indexOf(" ") + 1, userInput.indexOf("/")).trim();
                String eventDate = userInput.substring(userInput.indexOf("/") + 3).trim();
                addEvent(taskName, eventDate);
                continue;
            }

            // if user wants to mark as done
            if (userInput.startsWith("done")) {
                // get index of task to change
                int taskIndex = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1)) - 1;

                // if task out of bounds
                if (taskIndex < 0 || taskIndex > 99) {
                    System.out.println(dashes);
                    System.out.println("Apologies sir, there is no such task in your list.");
                    System.out.println(dashes);
                    continue;
                }
                // change task to done
                Task currTask = tasks[taskIndex];
                if (currTask == null) {
                    System.out.println(dashes);
                    System.out.println("Apologies sir, there are currently no tasks in your list");
                    System.out.println(dashes);
                    continue;
                }
                currTask.setDone(true);
                System.out.println(dashes);
                System.out.println("Your task \"" + currTask.getTaskName() + "\" is indicated as complete.");
                System.out.println("[X]" + currTask.getTaskName());
                System.out.println(dashes);
                continue;
            }
            // ask user to input task with todo, event or deadline
            System.out.println(dashes);
            System.out.println("Enter todo, event or deadline before task name to classify task correctly");
            System.out.println(dashes);
        }
    }

    private static void getList() {
        // Message Printer
        System.out.print(dashes);
        System.out.println("Listing all tasks for today.");
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(task);
        }
        System.out.print(dashes);
    }

    private static void addTask(Task newTask, String taskName) {
        tasks[tasksCounter++] = newTask;
        // Message Printer
        System.out.print(dashes);
        System.out.println("Very well, adding task \"" + taskName + "\"");
        System.out.print(dashes);
    }

    public static void addToDo(String taskName) {
        Todo newTodo = new Todo(false, taskName);
        addTask(newTodo, taskName);
    }

    public static void addDeadline(String taskName, String deadline) {
        Deadline newDeadline = new Deadline(false, taskName, deadline);
        addTask(newDeadline, taskName);
    }

    public static void addEvent(String taskName, String date) {
        Event newEvent = new Event(false, taskName, date);
        addTask(newEvent, taskName);
    }

}
