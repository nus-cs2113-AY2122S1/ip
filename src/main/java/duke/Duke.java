package duke;

import java.util.ArrayList;
import duke.exceptions.InvalidCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;


public class Duke {
    public static final int END_OF_DONE_INDEX = 4;
    public static final int END_OF_EVENT_INDEX = 5;
    public static final int END_OF_DEADLINE_INDEX = 8;
    public static final int END_OF_TODO_INDEX = 4;
    public static final int END_OF_DELETE_INDEX = 6;
    public static final int AT_LENGTH = 3;
    public static final int BY_LENGTH = 3;
    public static final String LOGO = " _____         _____\n"
            + "|     \\ _____ |     \\ _____\n"
            + "|  o  /|     ||  o  /|     |\n"
            + "|  o  \\|  o  ||  o  \\|  o  |\n"
            + "|_____/|_____||_____/|_____|\n";
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static boolean isRunning = true;
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static String dukeString = "dukeMem.txt";
    public static Path dukePath = Paths.get(dukeString);
    public static File dukeMem = new File(dukeString);

    public static void main(String[] args) {

        initTasksArrayFromMem();

        String line;
        Scanner in = new Scanner(System.in);

        sayHello();
        while (isRunning) {
            line = in.nextLine();
            try {
                if (line.trim().equals("bye")) {
                    exitProgram();
                } else if(line.trim().equals("list")) {
                    printList();
                } else if (line.startsWith("done")) {
                    makeDone(line);
                } else if (line.startsWith("todo")) {
                    addTodoTask(line);
                } else if (line.startsWith("deadline")) {
                    addDeadlineTask(line);
                } else if (line.startsWith("event")) {
                    addEventTask(line);
                } else if (line.startsWith("delete")) {
                    deleteTask(line);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                sayInvalid();
            }
        }
        sayBye();
    }

    private static void initTasksArrayFromMem() {
        if (!dukeMem.exists()) {
            createDukeMem();
        } else {
            try {
                loadMemToTaskArray();
            } catch (FileNotFoundException e) {
                System.out.println("Error: Seems like the file does not exist!");
            }
        }
    }

    private static void loadMemToTaskArray() throws FileNotFoundException {
        Scanner s = new Scanner(dukeMem);
        String memAsString = "";
        while (s.hasNextLine()) {
            String nextLine = s.nextLine();
            memAsString += nextLine + System.lineSeparator();
            writeTaskToTasksArray(nextLine);
        }
        String memAsTrimmedString = memAsString.substring(0, memAsString.length()-2);
        rewriteMem(memAsTrimmedString);
    }

    private static void writeTaskToTasksArray(String nextLine) {
        String lineData[] = nextLine.split("~");
        if (lineData[0].equals("T")) {
            todoMemToTasksArray(lineData);
        } else if (lineData[0].equals("D")) {
            deadlineMemToTaskArray(lineData);
        } else if (lineData[0].equals("E")) {
            eventMemToTaskArray(lineData);
        }
    }

    private static void createDukeMem() {
        try {
            Files.createFile(dukePath);
        } catch (IOException e) {
            System.out.println("Error: Seems like the directory does not exist!");
        }
    }

    private static void eventMemToTaskArray(String[] lineData) {
        tasks.add(new Event(lineData[2], lineData[3]));
        if (lineData[1].equals("1")) {
            tasks.get(tasks.size()-1).markAsDone();
        }
    }

    private static void deadlineMemToTaskArray(String[] lineData) {
        tasks.add(new Deadline(lineData[2], lineData[3]));
        if (lineData[1].equals("1")) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    private static void todoMemToTasksArray(String[] lineData) {
        tasks.add(new Todo(lineData[2]));
        if (lineData[1].equals("1")) {
            tasks.get(tasks.size()-1).markAsDone();
        }
    }

    private static void sayHello() {
        System.out.println(LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Bobo!");
        System.out.println("I'm a little blur, but I'd love to help!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void sayBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Ok. Bye bye!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void sayInvalid() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Sorry I don't understand that! Can you rephrase?");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printList() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        int taskNumber = 1;
        for (Task task : tasks) {
            System.out.println((taskNumber) + "." + task);
            taskNumber++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printDone(int taskIndex) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Okie! Marked this as done:");
        System.out.println((taskIndex + 1) + "." + tasks.get(taskIndex));
        System.out.println(HORIZONTAL_LINE);
    }

    private static void makeDone(String line) {
        try {
            handleDone(line);
        } catch (NumberFormatException e) {
            printMissingDoneIndexMsg();
        } catch (IndexOutOfBoundsException e) {
            printInvalidTaskIndexMsg();
        }
    }

    private static void handleDone(String line) {
        int taskNumber = Integer.parseInt(line.substring(END_OF_DONE_INDEX).trim());
        int taskIndex = taskNumber - 1;
        if (tasks.get(taskIndex).isDone()) {
            System.out.println("This task is already done!");
        } else {
            tasks.get(taskIndex).markAsDone();
            printDone(taskIndex);
            writeDoneToMem(taskIndex);
        }
    }

    private static void printInvalidTaskIndexMsg() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oh no! There isn't a task with that index");
        System.out.println("Please try again!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printMissingDoneIndexMsg() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oh no! An integer must come after the done command!");
        System.out.println("Please try again!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printTaskConfirmation() {
        int numberOfTasks = tasks.size();
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Umm ok added:");
        System.out.println("  " + tasks.get(numberOfTasks - 1));
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addEventTask(String line) {
        try {
            handleEventTask(line);
            printTaskConfirmation();
        } catch (StringIndexOutOfBoundsException e) {
            printMissingAtErrorMsg();
        }
    }

    private static void handleEventTask(String line) {
        int endOfDescriptionIndex = line.indexOf("/at");
        int startOfAtIndex = line.indexOf("/at") + AT_LENGTH;
        String description = line.substring(END_OF_EVENT_INDEX, endOfDescriptionIndex).trim();
        String at = line.substring(startOfAtIndex).trim();
        String eventText = makeEventText(description, at);
        tasks.add(new Event(description, at));
        appendToMem(eventText);
    }

    private static void printMissingAtErrorMsg() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oh no! Event tasks must be followed by /at keyword!");
        System.out.println("Please try again!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addDeadlineTask(String line) {
        try {
            handleDeadlineTask(line);
            printTaskConfirmation();
        } catch (StringIndexOutOfBoundsException e) {
            printMissingByErrorMsg();
        }
    }

    private static void handleDeadlineTask(String line) {
        int endOfDescriptionIndex = line.indexOf("/by");
        int startOfByIndex = line.indexOf("/by") + BY_LENGTH;
        String description = line.substring(END_OF_DEADLINE_INDEX, endOfDescriptionIndex).trim();
        String by = line.substring(startOfByIndex).trim();
        String deadlineText = makeDeadlineText(description, by);
        tasks.add(new Deadline(description, by));
        appendToMem(deadlineText);
    }

    private static void printMissingByErrorMsg() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oh no! Deadline tasks must be followed by /by keyword!");
        System.out.println("Please try again!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addTodoTask(String line) {
        handleTodoTask(line);
        printTaskConfirmation();
    }

    private static void handleTodoTask(String line) {
        String description = line.substring(END_OF_TODO_INDEX).trim();
        String todoText = makeTodoText(description);
        tasks.add(new Todo(description));
        appendToMem(todoText);
    }

    private static void deleteTask(String line) {
        try {
            Task deletedTask = handleDeleteTask(line);
            printDeleteConfirmation(deletedTask);
        } catch (IndexOutOfBoundsException e) {
            printInvalidDeleteIndexMsg();
        } catch (NumberFormatException e) {
            printMissingDeleteIndexMsg();
        }
    }

    private static void printInvalidDeleteIndexMsg() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oh no! There isn't a task with that index");
        System.out.println("Please try again!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printMissingDeleteIndexMsg() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Oh no! An integer must come after the delete command!");
        System.out.println("Please try again!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printDeleteConfirmation(Task deletedTask) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Okie! Deleted this task:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    private static Task handleDeleteTask(String line) {
        int taskNumber = Integer.parseInt(line.substring(END_OF_DELETE_INDEX).trim());
        int taskIndex = taskNumber - 1;
        Task deletedTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        try {
            deleteTaskFromMem(taskIndex);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Seems like the file does not exist!");
        }
        return deletedTask;
    }

    private static void deleteTaskFromMem(int taskIndex) throws FileNotFoundException {
        Scanner s = new Scanner(dukeMem);
        int lineCount = 0;
        String memAsString = "";
        while (s.hasNextLine()) {
            if (lineCount == taskIndex) {
                String deletedString = s.nextLine();
            } else {
                memAsString += s.nextLine() + System.lineSeparator();
            }
            lineCount++;
        }
        String memAsTrimmedString = memAsString.substring(0, memAsString.length()-2);
        rewriteMem(memAsTrimmedString);
    }

    private static void writeDoneToMem(int taskIndex) {
        try {
            markDoneInMem(taskIndex);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Seems like the file does not exist!");
        }
    }

    private static void markDoneInMem(int taskIndex) throws FileNotFoundException {
        Scanner s = new Scanner(dukeMem);
        int lineCount = 0;
        String memAsString = "";
        while (s.hasNextLine()) {
            if (lineCount == taskIndex) {
                String targetTask = s.nextLine().replaceFirst("0", "1");
                memAsString += targetTask + System.lineSeparator();
            } else {
                memAsString += s.nextLine() + System.lineSeparator();
            }
            lineCount++;
        }
        String memAsTrimmedString = memAsString.substring(0, memAsString.length()-2);
        rewriteMem(memAsTrimmedString);
    }

    private static void rewriteMem(String memAsTrimmedString) {
        try {
            FileWriter fw = new FileWriter(dukeMem);
            fw.write(memAsTrimmedString);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Seems like the directory does not exist!");
        }
    }

    private static void appendToMem(String text) {
        try {
            FileWriter fw = new FileWriter(dukeMem, true);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Seems like the directory does not exist!");
        }
    }

    private static String makeTodoText(String description) {
        return System.lineSeparator() + "T~0~" + description;
    }

    private static String makeDeadlineText(String description, String by) {
        return System.lineSeparator() + "D~0~" + description + "~" + by;
    }

    private static String makeEventText(String description, String at) {
        return System.lineSeparator() + "E~0~" + description + "~" + at;
    }

    private static void exitProgram() {
        isRunning = false;
    }
}
