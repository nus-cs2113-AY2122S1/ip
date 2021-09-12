package duke;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.exceptions.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    private static Task[] tasks;
    private static int taskCount;
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String HELLO_MESSAGE = "Hello! I'm MARK\n" + "What can I do for you?";
    public static final String BYE_MESSAGE = "You've terminated MARK. Have a good day!";
    public static final String TASK_DONE = "Task has been marked as done:\n";
    public static final String INVALID_INPUT = "Your input is invalid.";

    public static final String TODO = "todo";
    public static final String TODO_EXCEPTION = "Description of todo cannot be empty.";
    public static final String DEADLINE = "deadline";
    public static final String DEADLINE_EXCEPTION ="Deadline task requires a /by property.";
    public static final String EVENT = "event";
    public static final String EVENT_EXCEPTION = "Event task requires a /at property.";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String BYE = "bye";

    private static final Path FILE_PATH = Paths.get("/repos/iP/duke.txt");

    public static void readFile() throws DukeException {
        //past tense of "read" used
        Task[] readTasks = new Task[100];
        int readTaskCount = 0;

        try {
            File f = new File(FILE_PATH.toString());

            if (f.createNewFile()) {
                printWithLines("New file created.");
            } else {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    Task t;
                    String line = s.nextLine();
                    //type | isDone | description | attribute
                    String[] inputArray = line.split(" [ | ] ");

                    switch (inputArray[0]) {
                    case "T":
                        t = new ToDo(inputArray[2]);
                        break;
                    case "D":
                        t = new Deadline(inputArray[2], inputArray[3]);
                        break;
                    case "E":
                        t = new Event(inputArray[2], inputArray[3]);
                        break;
                    default:
                        throw new DukeException("Error while parsing");
                    }

                    if (inputArray[1].equals("1")) {
                        t.setDone();
                    }

                    readTasks[readTaskCount] = t;
                    readTaskCount++;
                }

                tasks = readTasks.clone();
                taskCount = readTaskCount;
            }
        } catch (IOException e) {
            throw new DukeException("Error while reading file.");
        }
    }

    public static void saveFile() throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            System.out.println("File stored in: " + FILE_PATH);

            String newText = "";
            for (int i = 0; i < taskCount; i++) {
                Task t = tasks[i];
                newText = newText.concat("\n");
            }

            fw.write(newText);
            fw.close();

        } catch (IOException e) {
            throw new DukeException("IO exception");
        }
    }

    /**
     * Prints text within two horizontal lines.
     *
     * @param text String to be printed.
     */
    public static void printWithLines(String text) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(text);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints welcome message upon running MARK.
     */
    public static void printHelloMessage() {
        printWithLines(HELLO_MESSAGE);
    }

    /**
     * Prints exit message upon terminating MARK.
     */
    public static void printByeMessage() {
        printWithLines(BYE_MESSAGE);
     }

    public static void parseString(String taskData) {
        if (taskData.startsWith(DEADLINE)) {
            tasks[taskCount] = new Deadline(taskData.substring(0, taskData.indexOf("/by"))
                    .replaceFirst(DEADLINE, "").trim(),
                    taskData.substring(taskData.indexOf("/by") + "/by".length()).trim());
        }

        else if (taskData.startsWith(EVENT)) {
            tasks[taskCount] = new Event(taskData.substring(0, taskData.indexOf("/at"))
                    .replaceFirst(EVENT, "").trim(),
                    taskData.substring(taskData.indexOf("/at") + "/at".length()).trim());
        }

        else if (taskData.startsWith(TODO)) {
            tasks[taskCount] = new ToDo(taskData.replaceFirst(TODO, "").trim());

        }
    }

    public static void addTask(String task) throws DukeException {

        if (task.startsWith(TODO)) {
            if (task.substring(4).isEmpty()) {
                throw new DukeException(TODO_EXCEPTION);
            }
            parseString(task);

        } else if (task.startsWith(DEADLINE)) {
              if (!task.contains("/by")) {
                  throw new DukeException(DEADLINE_EXCEPTION);
              }
              parseString(task);

        } else if (task.startsWith(EVENT)) {
              if (!task.contains("/at")) {
                  throw new DukeException(EVENT_EXCEPTION);
              }
              parseString(task);
        }

        if (taskCount == 0) {
            printWithLines("I've added this task:\n" + tasks[taskCount].toString() + "\n" + "You have " + 1 + " task in the list.");
        } else {
            printWithLines("I've added this task:\n" + tasks[taskCount].toString() + "\n" + "You have " + (taskCount + 1) + " tasks in the list.");
        }
        /* increments after adding a task */
        taskCount++;
    }

    /**
     * Lists down tasks that have been added, displaying their description and their status.
     */
    public static void listTasks() {
        String taskList = "Your list of tasks:\n";

        if (taskCount == 0) {
            printWithLines("No tasks listed!");
            return;
        }

        for (int i = 0; i < taskCount; i++) {
            taskList = taskList.concat((i + 1) + ". " + tasks[i].toString() + "\n");
        }

        // erase last newline character
        taskList = taskList.substring(0, taskList.length() - 1);

        printWithLines(taskList);
    }

    /**
     * Identify a task in the list and marks it as completed.
     *
     * @param input a String to be parsed to identify a task number in the list.
     */
    public static void setTaskDone(String input) throws DukeException {
        int taskNumber = Integer.parseInt(input.replace("done ", "")) - 1;

        if (taskNumber > taskCount - 1) {
            throw new DukeException("Task number " + (taskNumber + 1) + " is invalid!\nEnter a valid task number.");
        }

        Task chosenTask = tasks[taskNumber];
        chosenTask.setDone();
        printWithLines(TASK_DONE + chosenTask.getStatusIcon() + " " + chosenTask.description);
    }


    public static void selectCommand(String input) throws DukeException {
        String inputCommand = input.trim().split(" ")[0];
        String inputData = input.replaceFirst(inputCommand, "").trim();

        switch (inputCommand){
        case TODO: case DEADLINE: case EVENT:
            addTask(input);
            saveFile();
            break;
        case LIST:
            listTasks();
            break;
        case DONE:
            setTaskDone(inputData);
            saveFile();
            break;
        case BYE:
            printByeMessage();
            System.exit(0);
            break;
        default:
            printWithLines(INVALID_INPUT);
            break;
        }
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (DukeException e) {
            printWithLines(e.toString());
        }

        printHelloMessage();

        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();

        while (true) {
            try{
                selectCommand(line);
            } catch (DukeException x) {
                printWithLines(x.toString());
            }

            line = in.nextLine();
        }
    }
}
