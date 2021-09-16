package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS]; // fixed size array for now, each contains a Task element
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
    private static final int DONE_MIN_LENGTH = 2;
    private static final String DONE_DESCRIPTION_ERROR = "The task to be marked as done cannot be empty";
    private static final String DONE_EXCEED_ERROR = "The task to be marked as done does not exist in the list of tasks";
    private static final int EVENT_DEADLINE_TIME = 3;
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

    public static void markAsDone(Task[] tasks, int currCount, String line) throws DukeException {
        String[] input = line.split(" ");
        if (input.length < DONE_MIN_LENGTH) {
            throw new DukeException(DONE_DESCRIPTION_ERROR);
        }
        int index = Integer.parseInt(input[1]) - 1;
        if (index >= currCount) {
            throw new DukeException(DONE_EXCEED_ERROR);
        }
        tasks[index].markAsDone();
        printHorizontalLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + tasks[index].toString());
        printHorizontalLine();
    }

    public static void printList(Task[] tasks, int currCount) {
        printHorizontalLine();
        System.out.println(" Here are the tasks in your list:");
        Task[] taskList = Arrays.copyOf(tasks, currCount);
        int taskCount = 1;
        for (Task elem : taskList) {
            System.out.println(" " + taskCount + ". " + elem.toString());
            taskCount += 1;
        }
        printHorizontalLine();
    }

    public static int completeAddTask(Task[] tasks, int currCount) {
        printHorizontalLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + tasks[currCount].toString());
        int numTasks = currCount + 1;
        System.out.println(" Now you have " + numTasks + " tasks in the list.");
        printHorizontalLine();
        currCount += 1;
        return currCount;
    }

    public static void processEventCommand(Task[] tasks, int currCount, String line) throws DukeException {
        if (line.length() < EVENT_MIN_LENGTH) {
            throw new DukeException(EVENT_DESCRIPTION_ERROR);
        }
        if (!line.contains("/at")) {
            throw new DukeException(EVENT_EMPTY_ERROR);
        }
        String[] input = line.substring(EVENT_DESCRIPTION_START).split("/");
        tasks[currCount] = addEvent(input[0], input[1].substring(EVENT_DEADLINE_TIME));
    }

    public static Task addEvent(String description, String at) {
        return new Event(description, at);
    }

    public static void processDeadlineCommand(Task[] tasks, int currCount, String line) throws DukeException {
        if (line.length() < DEADLINE_MIN_LENGTH) {
            throw new DukeException(DEADLINE_DESCRIPTION_ERROR);
        }
        if (!line.contains("/by")) {
            throw new DukeException(DEADLINE_EMPTY_ERROR);
        }
        String[] input = line.substring(DEADLINE_DESCRIPTION_START).split("/");
        // first elem: description, second elem: deadline
        tasks[currCount] = addDeadline(input[0], input[1].substring(EVENT_DEADLINE_TIME));
    }

    public static Task addDeadline(String description, String by) {
        return new Deadline(description, by);
    }

    public static void processTodoCommand(Task[] tasks, int currCount, String line) throws DukeException {
        if (line.length() < TODO_MIN_LENGTH) {
            throw new DukeException(TODO_DESCRIPTION_ERROR);
        }
        // take the description part of the input string
        tasks[currCount] = addTodo(line.substring(TODO_DESCRIPTION_START));
    }

    public static Task addTodo(String description) {
        return new Todo(description);
    }

    public static void printGoodBye() {
        printHorizontalLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void executeCommands(int currCount) throws IOException {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (!line.equals("bye")) {
            try {
                if (line.contains("done")) { // mark task as done
                    markAsDone(tasks, currCount, line);
                    writeToFile();
                } else if (line.contains("todo")) {
                    processTodoCommand(tasks, currCount, line);
                    currCount = completeAddTask(tasks, currCount);
                    writeToFile();
                } else if (line.contains("deadline")) {
                    processDeadlineCommand(tasks, currCount, line);
                    currCount = completeAddTask(tasks, currCount);
                    writeToFile();
                } else if (line.contains("event")) {
                    processEventCommand(tasks, currCount, line);
                    currCount = completeAddTask(tasks, currCount);
                    writeToFile();
                } else if (line.equals("list")){ // print the list
                    printList(tasks, currCount);
                }
                else {
                    throw new DukeException(COMMAND_ERROR);
                }
            } catch (DukeException e) {
                printHorizontalLine();
                System.out.println(e.getMessage());
                printHorizontalLine();
            }
        }
    }

    public static int readFile() throws FileNotFoundException {
        File f = new File("Duke/data.txt");
        Scanner s = new Scanner(f);
        int currCount = 0;
        while (s.hasNext()) {
            parseDataFromFile(s.nextLine(), currCount);
            currCount += 1;
        }
        return currCount;
    }

    public static void parseDataFromFile(String nextLine, int currCount) {
        String[] input = nextLine.split("\\|"); // necessary to escape regex meta character
//        for (String s: input) {
//            s = s.trim();
//        }
        switch (input[0]) {
        case "T":
            tasks[currCount] = addTodo(input[2]);
            if (input[1].equals("1")) {
                tasks[currCount].markAsDone();
            }
            break;
        case "D":
            tasks[currCount] = addDeadline(input[2], input[3]);
            if (input[1].equals("1")) {
                tasks[currCount].markAsDone();
            }
            break;
        case "E":
            tasks[currCount] = addEvent(input[2], input[3]);
            if (input[1].equals("1")) {
                tasks[currCount].markAsDone();
            }
            break;
        }
    }

    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("Duke/data.txt");
        fw.write(parseDataIntoFile());
        fw.close();
    }

    public static String parseDataIntoFile() {
        String output = "";
        for (Task t: tasks) {
            output += t.parseDataIntoString() + "\n";
        }
        return output;
    }

    public static void createFile() {

    }

    public static void main(String[] args) {
        printWelcomeMessage();
        int currCount = readFile();
        executeCommands(currCount);
        printGoodBye();
    }
}
