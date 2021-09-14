package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

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

    public static void printRemoveIt() {
        System.out.println("Noted. I've removed this task:");
    }

    public static void printTaskCount() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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

    public static void printDoneException() {
        System.out.println("The description of done cannot be empty.");
    }

    public static void printDeleteException() {
        System.out.println("The description of delete cannot be empty.");
    }

    public static void printTaskTypeResponse() {
        //printing different responses depending if its duke.Todo/duke.Deadline/duke.Event
        printGotIt();
        tasks.get(tasks.size() - 1).printTask();
        printTaskCount();
    }

    public static void printDeleteResponse() {
        printRemoveIt();
        tasks.get(tasks.size() - 1).printTask();
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i += 1) {
            System.out.print((i + 1) + ".");
            tasks.get(i).printTask();
        }
    }

    public static void taskDone(String line) throws DoneException {
        if (line.equals("") || line.equals("done")) {
            throw new DoneException();
        } else {
            int index = Integer.parseInt(line) - 1;
            tasks.get(index).setDone();
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(index).printTask();
        }
    }

    public static void addTodo(String line) throws TodoException {
        if (line.equals("") || line.equals("todo")) {
            throw new TodoException();
        } else {
            tasks.add(new Todo (line));
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
            tasks.add(new Deadline (description, by));
            printTaskTypeResponse();
        }
    }

    public static void addEvent(String line) throws EventException {
        if (!line.matches("(.*)/at(.*)")) {
            throw new EventException();
        } else {
            //extracting the description and date
            String description = line.replaceAll("/.+", "");
            String at = line.replaceAll(".+/at", "");
            tasks.add(new Event (description, at));
            printTaskTypeResponse();
        }
    }

    public static void deleteTask(String line) throws DeleteException {
        if (line.equals("") || line.equals("delete")) {
            throw new DeleteException();
        } else {
            int index = Integer.parseInt(line) - 1;
            printDeleteResponse();
            tasks.remove(index);
            printTaskCount();
        }
    }

    public static void readInput(String line) {
        String[] splitLine = line.split(" ", 2);
        String command = splitLine[0];
        line = line.replaceAll("^" + command + " ", "");
        if (command.equals("list")) {
            printList();
        } else if (command.equals("done")) {
            try {
                //find which task user is done with
                taskDone(line);
            } catch (DoneException e) {
                printDoneException();
            }
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
        } else if (command.equals("delete")) {
            try {
                deleteTask(line);
            } catch (DeleteException e) {
                printDeleteException();
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
        printExit();
    }
}
