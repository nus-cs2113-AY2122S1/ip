import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Duke {

    /*ATTRIBUTES*/

    private static ArrayList<Task> tasks = new ArrayList<>();
    //private static Task[] tasks = new Task[100]; //array to store task list
    //private static int taskCount; //store total number of tasks
    private static String filePath = "data/duke.txt";
    //private static File taskList = new File(filePath);


    /*METHODS*/

    //adds task to the task list
    public static void addTask(Task t) throws IOException {
        tasks.add(t);

        printHorizontalLine();
        t.printTaskNotif();
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printHorizontalLine();

        writeFile();
    }

    //prints task list when "list" is keyed by user
    public static void printList() {

        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String type = tasks.get(i).type;
            String icon = tasks.get(i).getStatusIcon();
            System.out.println((i + 1) + "." + "[" + type + "]" + " [" + icon + "] " + tasks.get(i).description);
        }

        printHorizontalLine();
    }

    //prints specific task that is done
    public static void setDoneTask(Task t, int i) throws IOException {
        tasks.get(i).setDone(true);
        writeFile();

        printHorizontalLine();
        System.out.println("Nice, I've marked this task as done:");
        String type = t.type;
        String icon = t.getStatusIcon();
        System.out.println("[" + type + "]" + " [" + icon + "] " + t.description);
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

    public static void writeFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, true);

        for (int i = 0; i < tasks.size(); i++) {
            try {
                clearFile();
                fw.write(tasks.get(i).type + " | " + tasks.get(i).getStatusIcon() + " | " + tasks.get(i).description + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        fw.close();
    }

    public static void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }




    /*MAIN*/

    public static void main(String[] args) throws DukeException, IOException {

        printStartMessage();

        String input, error;
        Scanner in = new Scanner(System.in);

        Task t;
        boolean isBye = false;
        File taskList = new File(filePath);

        while (!isBye) {
            input = in.nextLine();

            if (input.equalsIgnoreCase(("bye"))) { //end program
                isBye = true;
                printByeMessage();

            } else if (input.equalsIgnoreCase("list")) {
                printList(); //print task list

            } else if (input.contains("done")) {
                String[] splitString = input.split(" ");
                int index = Integer.parseInt(splitString[1]); //task number to be marked as done

                setDoneTask(tasks.get(index - 1), index - 1);

            } else if (input.contains("todo")) { //task is a todo
                String description = input.substring(4).trim();

                if (description.isEmpty()) {
                    error = "toDoDescriptionEmptyException";
                    throw new DukeException(error);

                } else {
                    t = new Todo(description);
                    addTask(t);
                }


            } else if (input.contains("deadline")) { //task is a deadline
                int slash = input.indexOf("/");
                String description = input.substring(9, slash);
                String by = input.substring(slash + 4);
                t = new Deadline(description, by);

                addTask(t);

            } else if (input.contains("event")) { //task is an event
                int slash = input.indexOf("/");
                String description = input.substring(6, slash);
                String at = input.substring(slash + 4);
                t = new Event(description, at);

                addTask(t);

            } else {//basic task
//                t = new Task(input);
//                addTask(t);

                error = "unrecognisedTask";
                throw new DukeException("unrecognisedTask");
            }
        }
    }
}

