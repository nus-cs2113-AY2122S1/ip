package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
//import java.io.FileNotFoundException;

public class Duke {
//    private static final File dukeDir = new File("Duke");
//    private static final Path dukeDataPath = Paths.get("Duke/data.txt");
//    private static final File dukeData = new File(dukeDataPath.toString());
    private static final int MAX_TASKS = 100;
    //private static Task[] tasks = new Task[MAX_TASKS]; // fixed size array for now, each contains a Task element
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_TASKS);
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final int TODO_MIN_LENGTH = 6;
    private static final int TODO_DESCRIPTION_START = 5;
    private static final String TODO_DESCRIPTION_ERROR = "The todo description cannot be empty";
    private static final int EVENT_MIN_LENGTH = 7;
    private static final int EVENT_DESCRIPTION_START = 6;
    private static final String EVENT_EMPTY_ERROR = "The event must contain an '/at'";
    private static final String EVENT_DESCRIPTION_ERROR = "The event description cannot be empty";
    private static final int DEADLINE_MIN_LENGTH = 10;
    private static final int DEADLINE_DESCRIPTION_START = 9;
    private static final String DEADLINE_EMPTY_ERROR = "The deadline must contain a '/by'";
    private static final String DEADLINE_DESCRIPTION_ERROR = "The deadline description cannot be empty";
    private static final int EVENT_DEADLINE_TIME = 3;
    private static final int DONE_DELETE_MIN_LENGTH = 2;
    private static final String DONE_DESCRIPTION_ERROR = "The task to be marked as done cannot be empty";
    private static final String DONE_EXCEED_ERROR = "The task to be marked as done does not exist in the list of tasks";
    private static final String DELETE_DESCRIPTION_ERROR = "The task to be deleted cannot be empty";
    private static final String DELETE_EXCEED_ERROR = "The task to be deleted does not exist in the list of tasks";
    private static final String COMMAND_ERROR = "Sorry, I don't understand what you are saying";


    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printHorizontalLine();
    }

    public static void markAsDone(int currCount, String line) throws DukeException {
        String[] input = line.split(" ");
        if (input.length < DONE_DELETE_MIN_LENGTH) {
            throw new DukeException(DONE_DESCRIPTION_ERROR);
        }
        int index = Integer.parseInt(input[1]) - 1;
        if (index >= currCount || index < 0) {
            throw new DukeException(DONE_EXCEED_ERROR);
        }
        tasks.get(index).markAsDone();
        printHorizontalLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + tasks.get(index).toString());
        printHorizontalLine();
        writeToFile();
    }

    public static int deleteTask(int currCount, String line) throws DukeException {
        String[] input = line.split(" ");
        if (input.length < DONE_DELETE_MIN_LENGTH) {
            throw new DukeException(DELETE_DESCRIPTION_ERROR);
        }
        int index = Integer.parseInt(input[1]) - 1;
        if (index >= currCount || index < 0) {
            throw new DukeException(DELETE_EXCEED_ERROR);
        }
        printHorizontalLine();
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + tasks.get(index).toString());
        tasks.remove(index);
        int numTasks = currCount - 1;
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
        printHorizontalLine();
        currCount -= 1;
        writeToFile();
        return currCount;
    }

    public static void printList() {
        printHorizontalLine();
        System.out.println(" Here are the tasks in your list:");
        int taskCount = 1;
        for (Task elem : tasks) {
            System.out.println(" " + taskCount + ". " + elem.toString());
            taskCount += 1;
        }
        printHorizontalLine();
    }

    public static int completeAddTask(int currCount) {
        printHorizontalLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + tasks.get(currCount).toString());
        int numTasks = currCount + 1;
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
        printHorizontalLine();
        currCount += 1;
        writeToFile();
        return currCount;
    }

    public static void processEventCommand(int currCount, String line) throws DukeException {
        if (line.length() < EVENT_MIN_LENGTH) {
            throw new DukeException(EVENT_DESCRIPTION_ERROR);
        }
        if (!line.contains("/at")) {
            throw new DukeException(EVENT_EMPTY_ERROR);
        }
        String[] input = line.substring(EVENT_DESCRIPTION_START).split("/");
        tasks.add(currCount, addEvent(input[0], input[1].substring(EVENT_DEADLINE_TIME)));
    }

    public static Task addEvent(String description, String at) {
        return new Event(description, at);
    }

    public static void processDeadlineCommand(int currCount, String line) throws DukeException {
        if (line.length() < DEADLINE_MIN_LENGTH) {
            throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
        }
        if (!line.contains("/by")) {
            throw new DukeException(DEADLINE_EMPTY_ERROR);
        }
        String[] input = line.substring(DEADLINE_DESCRIPTION_START).split("/");
        // first elem: description, second elem: deadline
        tasks.add(currCount, addDeadline(input[0], input[1].substring(EVENT_DEADLINE_TIME)));
    }

    public static Task addDeadline(String description, String by) {
        return new Deadline(description, by);
    }

    public static void processTodoCommand(int currCount, String line) throws DukeException {
        if (line.length() < TODO_MIN_LENGTH) {
            throw new DukeException(TODO_DESCRIPTION_ERROR);
        }
        // take the description part of the input string
        tasks.add(currCount, addTodo(line.substring(TODO_DESCRIPTION_START)));
    }

    public static Task addTodo(String description) {
        return new Todo(description);
    }

    public static void printGoodBye() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void executeCommands(int currCount) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            try {
                if (line.contains("done")) { // mark task as done
                    markAsDone(currCount, line);
                } else if (line.contains("delete")) { // delete task
                    currCount = deleteTask(currCount, line);
                } else if (line.contains("todo")) {
                    processTodoCommand(currCount, line);
                    currCount = completeAddTask(currCount);
                } else if (line.contains("deadline")) {
                    processDeadlineCommand(currCount, line);
                    currCount = completeAddTask(currCount);
                } else if (line.contains("event")) {
                    processEventCommand(currCount, line);
                    currCount = completeAddTask(currCount);
                } else if (line.equals("list")){ // print the list
                    printList();
                } else {
                    throw new DukeException(COMMAND_ERROR);
                }
            } catch (DukeException e) {
                printHorizontalLine();
                System.out.println(e.getMessage());
                printHorizontalLine();
            }
            line = in.nextLine();
        }
    }

    public static int readFile() throws FileNotFoundException {
        File dir = new File("Duke");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File data = new File("Duke/data.txt");
        if (data.exists()) {
            Scanner s = new Scanner(data);
            int currCount = 0;
            while (s.hasNext()) {
                parseDataFromFile(s.nextLine(), currCount);
                currCount += 1;
            }
            return currCount;
        }
        else {
            System.out.println("Creating new data file....");
            try {
                data.createNewFile();
                System.out.println("New data file created");
            } catch (IOException e) {
                System.out.println("Unable to create new data file");
            }
        }
        return 0; // initial value of currCount
    }

    public static void parseDataFromFile(String nextLine, int currCount) {
        String[] input = nextLine.split("\\|"); // necessary to escape regex meta character
        String[] trimmedInput = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            trimmedInput[i] = input[i].trim();
        }
        switch (trimmedInput[0]) {
        case "T":
            tasks.add(currCount, addTodo(trimmedInput[2]));
            if (trimmedInput[1].equals("1")) {
                tasks.get(currCount).markAsDone();
            }
            break;
        case "D":
            tasks.add(currCount, addDeadline(trimmedInput[2], trimmedInput[3]));
            if (trimmedInput[1].equals("1")) {
                tasks.get(currCount).markAsDone();
            }
            break;
        case "E":
            tasks.add(currCount, addEvent(trimmedInput[2], trimmedInput[3]));
            if (trimmedInput[1].equals("1")) {
                tasks.get(currCount).markAsDone();
            }
            break;
        }
    }

    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter("Duke/data.txt");
            for (Task t: tasks) {
                System.out.println(t.parseDataIntoString());
                String fileOutput = t.parseDataIntoString() + System.lineSeparator();
                fw.write(fileOutput);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        int currCount = 0;
        try {
            currCount = readFile();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found");
        }
        executeCommands(currCount);
        printGoodBye();
    }
}

//    public static String parseDataIntoFile() {
//        String output = "";
//        for (Task t: tasks) {
//            output += t.parseDataIntoString() + "\n";
//        }
//        return output;
//    }

//    public static int createAndProcessFile() throws IOException {
//        int currCount = 0;
//        if (!dukeData.createNewFile()) {
//            try {
//                currCount = readFile();
//            } catch (FileNotFoundException e) {
//                System.out.println("Duke/data.txt is not found");
//            }
//        }
//        return currCount;
//    }
