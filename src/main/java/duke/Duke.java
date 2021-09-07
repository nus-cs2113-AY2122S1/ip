package duke;

import duke.Exceptions.TaskException;
import duke.Exceptions.TimeException;
import duke.tasks.Deadlines;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        IntroductoryMessage();
        runIkaros();
        goodbyeMessage();
    }

    private static void runIkaros() {
        List Tasks = new List(0);
        Task[] listOfTasks = new Task[100];
        boolean isRunning = true;

        Scanner in = new Scanner(System.in);
        String response;
        String[] command;

        while (isRunning) {
            response = in.nextLine();
            lineBreak();
            command = response.split(" ", 10);
            switch (command[0]) {
            case "echo":
                echo();
                break;
            case "list":
                printList(listOfTasks, Tasks.listSize);
                lineBreak();
                break;
            case "done":
                done(listOfTasks, response);
                break;
            case "deadline":
            case "event":
            case "todo":
                taskManager(command, Tasks, listOfTasks, response);
                break;
            case "bye":
                isRunning = false;
                break;
            default:
                System.out.println("I didn't catch that!");
                lineBreak();
                break;
            }
        }
    }

    private static void taskManager(String[] command, List ofTasks, Task[] list,
                                    String response) {
        String TaskType = command[0];
        try {
            if (command.length == 1) {
                throw new TaskException();
            }
            else if (command[0].equalsIgnoreCase("event")) {
                Event(ofTasks, list, response);
            } else if (command[0].equalsIgnoreCase("todo")) {
                toDo(ofTasks, list, response);
            } else if (command[0].equalsIgnoreCase("deadline")) {
                deadLine(ofTasks, list, response);
            }
        } catch (TaskException e) {
            System.out.println("please specify " + TaskType + " to add!");
            //lineBreak();
        } catch (TimeException e) {
            System.out.println(e.getMessage());
            //lineBreak();
        } finally {
            lineBreak();
        }
    }

    private static void Event(List ofTasks, Task[] list, String response)
            throws TimeException {
        String timing = response.substring(response.indexOf("/") + 4);

        //checking if user entered timing
        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it being held? [indicate by adding: /at your_timing]");
        }
        Event event = new Event(response.substring(6, response.indexOf("/") - 1), timing);
        addToList(event, ofTasks, list);

    }

    private static void deadLine(List ofTasks, Task[] list, String response)
            throws TimeException {
        String timing = response.substring(response.indexOf("/") + 4);

        //checking if user entered timing
        if (response.indexOf("/") <= 0) {
            throw new TimeException("when is it due? [indicate by adding: /by your_timing]");
        }
        Deadlines work = new Deadlines(response.substring(9, response.indexOf("/") - 1),
                timing);
        addToList(work, ofTasks, list);
    }

    private static void toDo(List ofTasks, Task[] list, String response) {
        ToDo task = new ToDo(response.substring(5));
        addToList(task, ofTasks, list);
    }

    private static void lineBreak() {
        String lineBreak = "..........................." +
                ".......................................";
        System.out.println(lineBreak);
    }

    private static void IntroductoryMessage() {
        String logo = "  /\\ _ /\\\n"
                + " #  @ @  #    Welcome to IKAROS!\n"
                + " #   ^   #  Your one and only butler\n"
                + " #########";
        lineBreak();

        System.out.println(logo);
        lineBreak();
        System.out.println("What assistance do you require?");
        lineBreak();
    }

    private static void goodbyeMessage() {
        System.out.println("GoodBye, Ikaros awaits for future commands");
        lineBreak();
    }

    private static void done(Task[] list, String response) {
        int i = Integer.parseInt(response.substring(5)) - 1;
        list[i].markAsDone();
        System.out.println("Nice! i have marked this task as done:\n ["
                + list[i].getStatusIcon() + "] " + list[i].getDescription());
        lineBreak();
    }

    private static void addToList(Task task, List ofTasks, Task[] list) {
        list[ofTasks.listSize] = task;
        System.out.println("Task added: " + task);
        System.out.println("Total no. of Tasks = " + (ofTasks.listSize + 1));
        //lineBreak();
        ofTasks.listSize += 1;
    }

    public static void echo() {
        System.out.println("Life is a mirror and will reflect back to "
                + "the thinker what\nhe thinks into it, echoing commencing");
        lineBreak();
        Scanner in = new Scanner(System.in);
        String response;
        response = in.nextLine();
        lineBreak();
        while (!response.equals("exit")) {
            System.out.println(response);
            lineBreak();
            response = in.nextLine();
            lineBreak();
        }
    }

    public static void printList(Task[] list, int listSize) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= listSize; i++) {
            System.out.println(i + ".[" + list[i - 1].getTaskID() + "]" +
                    "[" + list[i - 1].getStatusIcon() +
                    "] " + list[i - 1].description + list[i - 1].getDate());
        }
    }
}
