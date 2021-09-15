package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /*ATTRIBUTES*/

    private static ArrayList<Task> tasks = new ArrayList<>(); //array to store duke.task list
    private static int taskCount; //store total number of tasks


    /*METHODS*/

    //adds duke.task to the duke.task list
    public static void addTask(Task t) {
        printHorizontalLine();
        tasks.add(t);
        t.printTaskNotif();
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printHorizontalLine();

        taskCount++;
    }

    //prints duke.task list when "list" is keyed by user
    public static void printList() {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String type = tasks.get(i).type;
            String icon = tasks.get(i).getStatusIcon();
            System.out.println((i + 1) + "." + "[" + type + "]" + " [" + icon + "] " + tasks.get(i).description);
        }
    }

    //prints specific duke.task that is done
    public static void setDoneTask(Task t, int i) {
        tasks.get(i).setDone(true);

        printHorizontalLine();
        System.out.println("Nice, I've marked this task as done:");
        String type = t.type;
        String icon = t.getStatusIcon();
        System.out.println("[" + type + "]" + " [" + icon + "] " + t.description);
        printHorizontalLine();
    }


    public static void deleteTask (Task t){
        tasks.remove(t);

        printHorizontalLine();
        System.out.println("Noted, I've removed this task:");
        String type = t.type;
        String icon = t.getStatusIcon();
        System.out.println("[" + type + "]" + " [" + icon + "] " + t.description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 11; i++) {
            System.out.print("-");
        }
        System.out.println("-");
    }

    public static void printStartMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public static void printByeMessage() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }


    /*MAIN*/

    public static void main(String[] args) throws DukeException {

        printStartMessage();

        String input, error;
        Scanner in = new Scanner(System.in);

        Task t;
        taskCount = 0;
        boolean isBye = false;


        while (!isBye) {
            input = in.nextLine();

            if (input.equalsIgnoreCase(("bye"))) { //end program
                isBye = true;
                printByeMessage();

            } else if (input.equalsIgnoreCase("list")) {
                printList(); //print duke.task list

            } else if (input.contains("done")) {
                String[] splitString = input.split(" ");
                int index = Integer.parseInt(splitString[1]); //duke.task number to be marked as done

                setDoneTask(tasks.get(index - 1), index - 1);

            } else if (input.contains("delete")){
                String[] splitString = input.split(" ");
                int index = Integer.parseInt(splitString[1]);

                deleteTask(tasks.get(index - 1));


            } else if (input.contains("todo")) { //duke.task is a todo
                String description = input.substring(4).trim();

                if (description.isEmpty()) {
                    error = "toDoDescriptionEmptyException";
                    throw new DukeException(error);

                }else{
                    t = new Todo(description);
                    addTask(t);
                }

            } else if (input.contains("deadline")) { //duke.task is a deadline
                int slash = input.indexOf("/");
                String description = input.substring(9, slash);
                String by = input.substring(slash + 4);
                t = new Deadline(description, by);

                addTask(t);

            } else if (input.contains("event")) { //duke.task is an event
                int slash = input.indexOf("/");
                String description = input.substring(6, slash);
                String at = input.substring(slash + 4);
                t = new Event(description, at);

                addTask(t);

            } else {//basic duke.task
//                t = new duke.task.Task(input);
//                addTask(t);

                error = "unrecognisedTask";
                throw new DukeException(error);
            }
        }
    }
}
