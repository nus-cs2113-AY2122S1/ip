package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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
    private static File taskText = new File(filePath);


    /*METHODS*/


    //adds task to the task list
    public static void addTask(Task t) throws IOException {
        printHorizontalLine();
        tasks.add(t);

        t.printTaskNotif();
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printHorizontalLine();

        writeToFile();
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

    //prints specific task that is done
    public static void setDoneTask(Task t, int i) throws IOException {
        tasks.get(i).setDone(true);
        writeToFile();

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

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, true);

        for (Task task : tasks) {
            try {
                clearFile();

                String status = "ND";
                if (task.getStatusIcon().equals("X")) {
                    status = "D";
                }
                fw.write(task.type + " | " + status + " | "
                        + task.description + System.lineSeparator());
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

    public static void readFile() throws FileNotFoundException {
        Scanner s = new Scanner(taskText);
        String[] arr;

        while (s.hasNext()) {
            String text = s.nextLine();
            arr = text.split("\\|");

            boolean status = arr[1].trim().equals("D");

            Task t;
            switch (arr[0].trim()) {
            case "T":
                t = new Todo(arr[2].trim());
                t.isDone = status;
                tasks.add(t);


                break;
            case "D": {
                String fulltext = arr[2];
                int index = fulltext.lastIndexOf("(by:");
                String name = fulltext.substring(0, index).trim();
                String by = fulltext.substring(index + 4, fulltext.length() - 1).trim();

                t = new Deadline(name, by);
                t.isDone = status;
                tasks.add(t);

                break;
            }
            case "E": {
                String fulltext = arr[2];
                int index = fulltext.lastIndexOf("(at:");
                String name = fulltext.substring(0, index).trim();
                String at = fulltext.substring(index + 4, fulltext.length() - 1).trim();

                t = new Event(name, at);
                t.isDone = status;
                tasks.add(t);
                break;
            }
            }

        }
        s.close();
    }

    /*MAIN*/

    public static void main(String[] args) throws DukeException, IOException {

        printStartMessage();

        try {
            readFile();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        String input, error;
        Scanner in = new Scanner(System.in);

        Task t;
        boolean isBye = false;
//        File taskList = new File(filePath);

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
                setDoneTask(tasks.get(index - 1), index - 1);


            } else if (input.contains("todo")) { //duke.task is a todo
                String description = input.substring(4).trim();

                if (description.isEmpty()) {
                    error = "toDoDescriptionEmptyException";
                    throw new DukeException(error);

                } else {
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

