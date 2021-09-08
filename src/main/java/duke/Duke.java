package duke;

import java.util.Scanner;

public class Duke {
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void printGreeting() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void printConfused() {
        System.out.println("Could you say that again?");
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printGotIt() {
        System.out.println("Got it. I've added this task:");
    }

    public static void printTaskCount() {
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void printTodoException() {
        System.out.println("The description of todo cannot be empty.");
    }

    public static void printDeadlineException() {
        System.out.println("The description of deadline cannot be empty.");
    }

    public static void printEventException() {
        System.out.println("The description of event cannot be empty.");
    }

    public static void printTaskTypeResponse() {
        //printing different responses depending if its duke.Todo/duke.Deadline/duke.Event
        printGotIt();
        tasks[taskCount - 1].printTask();
        printTaskCount();
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i += 1) {
            System.out.print((i + 1) + ".");
            tasks[i].printTask();
        }
    }

    public static void TaskDone(int index) {
        tasks[index].setDone();
        System.out.println("Nice! I've marked this task as done:");
        tasks[index].printTask();
    }

    public static void addTodo(String line) throws TodoException {
        if (line.equals("") || line.equals("todo")) {
            throw new TodoException();
        } else {
            tasks[taskCount] = new Todo(line);
            taskCount = taskCount + 1;
            printTaskTypeResponse();
        }
    }

    public static void addDeadline(String line) throws DeadlineException {
        if (!line.matches("(.*)/by(.*)")) {
            throw new DeadlineException();
        } else {
            //extracting the description and date
            String description = line.replaceAll("/.+", "");
            String by = line.replaceAll(".+/by", "");
            tasks[taskCount] = new Deadline(description, by);
            taskCount = taskCount + 1;
            printTaskTypeResponse();
        }
    }

    public static void addEvent(String line) throws EventException {
        if (!line.matches("(.*)/at(.*)")) {
            throw new EventException();
        } else {
            //extracting the description and date
            String description = line.replaceAll("/.+", "");
            String by = line.replaceAll(".+/at", "");
            tasks[taskCount] = new Event(description, by);
            taskCount = taskCount + 1;
            printTaskTypeResponse();
        }
    }

    public static void readInput(String line) {
        String[] splitLine = line.split(" ", 2);
        String command = splitLine[0];
        line = line.replaceAll("^" + command + " ", "");
        if (command.equals("list")) {
            printList();
        } else if (command.equals("done")) {
            //find which task user is done with
            int index = Integer.parseInt(line) - 1;
            tasks[index].setDone();
            TaskDone(index);
        } else if (command.equals("todo")) {
            try {
                addTodo(line);
            } catch (TodoException e) {
                printTodoException();
            }
        } else if (command.equals("deadline")) {
            try {
                addDeadline(line);
            } catch (DeadlineException e) {
                printDeadlineException();
            }
        } else if (command.equals("event")) {
            try {
                addEvent(line);
            } catch (EventException e) {
                printEventException();
            }
        } else {
            //error with input
            printConfused();
        }
    }

    public static void main(String[] args) {
        printGreeting();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        //continue to run program unless types "bye" to exit program
        while(!line.equals("bye")) {
            readInput(line);
            line = in.nextLine();
        }
    }
}
