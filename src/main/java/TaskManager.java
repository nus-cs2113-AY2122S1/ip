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
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.nextLine();

            // parse userInput
            while (userInput.trim().isEmpty()) {
                userInput = in.nextLine();
            }
            // change to switch case based on enum
            Commands command = InputParser.getCommand(userInput);

            // if bye, exit straight
            if (command == Commands.BYE) {
                MessagePrinter.exitMessage();
                return;
            }
            // t
            switch (command) {
            case INVALID:
                MessagePrinter.invalidCommand();
                break;
            case LIST:
                getList();
                break;
            case TODO:
                addToDo(userInput);
                break;
            case DEADLINE:
                addDeadline(userInput);
                break;
            case EVENT:
                addEvent(userInput);
                break;
            case DONE:
                markAsDone(userInput);
                break;
            }
        }
    }

    private static void getList() {
        if (tasksCounter == 0) {
            MessagePrinter.emptyListMessage();
            return;
        }
        // Message Printer
        MessagePrinter.printList(tasks, tasksCounter);
    }

    private static void addTask(Task newTask, String taskName) {
        tasks[tasksCounter++] = newTask;
        // Message Printer
        MessagePrinter.addedTask(taskName);
    }

    // catch exception for not enough parameters
    private static void addToDo(String userInput) {
        // get taskName
        String taskName = userInput.substring(userInput.indexOf(" ")).trim();
        // run through input parser to check for parameters
        Todo newTodo = new Todo(false, taskName);
        addTask(newTodo, taskName);
    }

    private static void addDeadline(String userInput) {
        // get taskName
        String taskName = InputParser.getTaskName(userInput);
        // get deadline; catch exception for no deadline
        String deadline = InputParser.getDate(userInput);
        Deadline newDeadline = new Deadline(false, taskName, deadline);
        addTask(newDeadline, taskName);
    }

    private static void addEvent(String userInput) {
        // get taskName
        String taskName = InputParser.getTaskName(userInput);
        // get event date; catch exception for no event date
        String eventDate = InputParser.getDate(userInput);
        Event newEvent = new Event(false, taskName, eventDate);
        addTask(newEvent, taskName);
    }

    private static void markAsDone(String userInput) {
        // get index of task to chang
        int taskIndex = InputParser.getTaskIndex(userInput);

        // catch exception for task being out of bounds
        if (taskIndex < 0 || taskIndex > 99) {
            MessagePrinter.outOfBoundsTaskIndex();
            return;
        }

        // change task to done
        Task currTask = tasks[taskIndex];
        if (currTask == null) {
            MessagePrinter.invalidTaskIndex();
            return;
        }
        currTask.setDone(true);
        MessagePrinter.taskMarkedAsDone(currTask);
    }

}
