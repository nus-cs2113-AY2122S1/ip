package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Duke {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String EXIT = "bye";
    public static final String DELETE = "delete";
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static int numberOfTasks = 0;
    private static File save;

    public static void main(String[] args) {
        saveCheck();
        greet();
        instructions();
        chooseTask();
    }

    private static void saveCheck() {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            save = new File(directory + "/" + "duke1.txt");
            save.createNewFile();
            Scanner s = new Scanner(save); // create a Scanner using the File as the source
            int counter = 1;
            while (s.hasNext()) {
                String[] parts = s.nextLine().split("\\|");
                String taskType = parts[0].trim();
                String status = parts[1].trim();
                String taskDescription = parts[2].trim();
                switch (taskType) {
                case "T":
                    addTask(TODO, taskDescription);
                    break;
                case "D":
                    addTask(DEADLINE, taskDescription);
                    break;
                case "E":
                    addTask(EVENT, taskDescription);
                    break;
                }
                if (status.equals("X")) {
                    markTaskComplete(Integer.toString(counter));
                }
                counter++;
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(save);
        for (int i = 0; i < numberOfTasks; i++) {
            fw.write(taskList.get(i).getType() + " | " + taskList.get(i).getStatusIcon() + " | " + taskList.get(i).getOriginalDescription() + System.lineSeparator());
        }
        fw.close();
    }

    private static void chooseTask() {
        String line, taskType, taskDescription = null;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        try {
            int typePos = line.indexOf(" ");
            taskType = line.substring(0, typePos);
            taskDescription = line.substring(typePos).trim();
        } catch (StringIndexOutOfBoundsException e) {
            taskType = line;
        }

        switch (taskType) {
        case TODO:
            addTask(TODO, taskDescription);
            break;
        case DEADLINE:
            addTask(DEADLINE, taskDescription);
            break;
        case EVENT:
            addTask(EVENT, taskDescription);
            break;
        case LIST:
            displayList();
            break;
        case DONE:
            markTaskComplete(taskDescription);
            break;
        case EXIT:
            bye();
            return;
        case DELETE:
            deleteTask(taskDescription);
            break;
        default:
            printDividerLine();
            System.out.println("Invalid Input!");
            printDividerLine();
        }
        chooseTask();
    }

    private static void greet() {
        printDividerLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static void instructions() {
        System.out.println("The following can be done: ");
        printDividerLine();
        System.out.println("1.  ToDos: tasks without any date/time attached to it");
        System.out.println("    COMMAND: todo xxx");
        System.out.println("2.  Deadlines: tasks that need to be done before a specific date/time");
        System.out.println("    COMMAND: deadline xxx /by yyy");
        System.out.println("3.  Events: tasks that start at a specific time and ends at a specific time");
        System.out.println("    COMMAND: event xxx /at yyy");
        System.out.println("4.  Display list");
        System.out.println("    COMMAND: list");
        System.out.println("5.  Mark task complete");
        System.out.println("    COMMAND: done x");
        System.out.println("6.  Exit");
        System.out.println("    COMMAND: bye");
        printDividerLine();
    }

    private static void bye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividerLine();
    }

    private static void printDividerLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void addTask(String taskType, String taskDescription) {

        if (taskDescription == null) {
            printDividerLine();
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            printDividerLine();
            return;
        }

        switch (taskType) {
        case TODO:
            taskList.add((new ToDo(taskDescription)));
            break;
        case DEADLINE:
            if (!taskDescription.contains("/by")) {
                printDividerLine();
                System.out.println("Missing /by time!");
                printDividerLine();
                return;
            }
            taskList.add((new Deadline(taskDescription)));
            break;
        case EVENT:
            if (!taskDescription.contains("/at")) {
                printDividerLine();
                System.out.println("Missing /at time!");
                printDividerLine();
                return;
            }
            taskList.add((new Event(taskDescription)));
            break;
        }

        printDividerLine();
        System.out.println("I have added this task:");
        System.out.println("[" + taskList.get(numberOfTasks).getType() + "][" + taskList.get(numberOfTasks).getStatusIcon() + "] " + taskList.get(numberOfTasks).getDescription());
        numberOfTasks++;
        System.out.println("You have " + numberOfTasks + " task(s) in the list.");
        printDividerLine();

        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void displayList() {
        printDividerLine();
        if (numberOfTasks == 0) {
            System.out.println("to-do list is empty! add something");
            return;
        }
        System.out.println("The current to-do list is as follows:");
        int counter = 1;
        for (Task item : taskList) {
            System.out.println(counter + ". [" + item.getType() + "][" + item.getStatusIcon() + "] " + item.getDescription());
            counter++;
        }
        printDividerLine();
    }

    private static void markTaskComplete(String taskDescription) {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber > numberOfTasks || taskNumber <= 0) {
                System.out.println("Error! This task does not exist!");
                return;
            }
            taskNumber--;
            taskList.get(taskNumber).markAsDone();
            printDividerLine();
            System.out.println("I have marked it as completed!");
            System.out.println(taskNumber + 1 + ". [" + taskList.get(taskNumber).getType() + "][" + taskList.get(taskNumber).getStatusIcon() + "] " + taskList.get(taskNumber).getDescription());
            printDividerLine();
        } catch (NumberFormatException e) {
            System.out.println("Error! This task does not exist!");
        }
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void deleteTask(String taskDescription) {
        int taskNumber;

        try {
            taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber > numberOfTasks || taskNumber <= 0) {
                System.out.println("Error! This task does not exist!");
                return;
            }
            taskNumber--;
            printDividerLine();
            System.out.println("Noted. I've removed this task: ");
            System.out.println("  [" + taskList.get(taskNumber).getType() + "][" + taskList.get(taskNumber).getStatusIcon() + "] " + taskList.get(taskNumber).getDescription());
            printDividerLine();
            taskList.remove(taskNumber);
            numberOfTasks--;
        } catch (NumberFormatException e) {
            System.out.println("Error! This task does not exist!");
        }
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}
