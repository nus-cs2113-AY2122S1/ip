package duke;

import duke.exception.EmptyCommandArgumentException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidCommandSeparatorException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private Task[] taskList = new Task[100];
    private int listSize = 0;

    public void handleCommand() {
        String userInput;
        Scanner in = new Scanner(System.in);

        userInput = in.nextLine();
        String[] inputWords = userInput.split(" ");
        String userCommand = inputWords[0];

        try {
            switch (userCommand) {
            case "bye":
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            case "list":
                printLine();
                System.out.println("Here are the tasks in your list:");
                showList();
                printLine();
                handleCommand();
                break;
            case "done":
                printLine();
                System.out.println("Nice! I've marked this task as done:");
                markTaskAsDone(inputWords[1]);
                printLine();
                handleCommand();
                break;
            case "deadline":
                addDeadlineOrEventTask(inputWords, "deadline");
                handleCommand();
                break;
            case "event":
                addDeadlineOrEventTask(inputWords, "event");
                handleCommand();
                break;
            case "todo":
                addTodoTask(inputWords);
                handleCommand();
                break;
            default:
                throw new InvalidCommandException();

            }
        } catch (InvalidCommandException e) {
            printLine();
            System.out.println("OOPS! I'm sorry, but I don't know what that means! :(");
            System.out.println("Available commands: deadline, todo, event, done, list, bye");
            printLine();
            handleCommand();
        } catch (EmptyCommandArgumentException e) {
            printLine();
            System.out.println("OOPS! The description of deadline/event/todo cannot be empty! " +
                    "Please follow this format:");
            System.out.println("deadline <your task here> /by <your deadline time>");
            System.out.println("event <your task here> /at <your event time period>");
            System.out.println("todo <your task here>");
            printLine();
            handleCommand();
        } catch (InvalidCommandSeparatorException e) {
            printLine();
            System.out.println("OOPS! The deadline/event description must be separated from " +
                    "the time using '/by' or '/at'. Please follow this format:");
            System.out.println("deadline <your task here> /by <your deadline time>");
            System.out.println("event <your task here> /at <your event time period>");
            printLine();
            handleCommand();
        }
    }

    public void addDeadlineOrEventTask(String[] inputWords, String type)
            throws EmptyCommandArgumentException, InvalidCommandSeparatorException {
        // Throw exception where command argument is empty
        if (inputWords.length < 2) {
            throw new EmptyCommandArgumentException();
        }

        // Find separator index
        int separatorIndex = -1;
        for (int i = 1; i < inputWords.length; i++) {
            if (inputWords[i].equals("/by") || inputWords[i].equals("/at")) {
                separatorIndex = i;
                break;
            }
        }

        // Throw exception where separator is not found
        if (separatorIndex == -1) {
            throw new InvalidCommandSeparatorException();
        }

        // Set description
        String description = inputWords[1];
        for (int i = 2; i < separatorIndex; i++) {
            description = description + " " + inputWords[i];
        }

        // Set deadline (by when) or event time (at what time)
        String time = inputWords[separatorIndex + 1];
        for (int i = separatorIndex + 2; i < inputWords.length; i++) {
            time = time + " " + inputWords[i];
        }

        if (type.equals("deadline")) {
            taskList[listSize] = new Deadline(description, time);
        } else {
            taskList[listSize] = new Event(description, time);
        }

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList[listSize]);
        printLine();
        appendTaskToFile(taskList[listSize]);

        listSize++;
    }

    public void addTodoTask(String[] inputWords) throws EmptyCommandArgumentException {
        // Throw exception where command argument is empty
        if (inputWords.length < 2) {
            throw new EmptyCommandArgumentException();
        }

        String description = inputWords[1];
        for (int i = 2; i < inputWords.length; i++) {
            description = description + " " + inputWords[i];
        }

        taskList[listSize] = new Todo(description);

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList[listSize]);
        printLine();
        appendTaskToFile(taskList[listSize]);

        listSize++;
    }

    public void showList() {
        for (int i = 0; i < listSize; i++) {
            System.out.println((i + 1) + ". " + taskList[i]);
        }
    }

    public void markTaskAsDone(String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        taskList[taskIndex].setAsDone();
        System.out.println(taskList[taskIndex]);
    }

    public static void printLine() {
        System.out.println("-----------------------------------------------");
    }

    public void createDataFile() {
        File dataFolder = new File("data");
        File dataFile = new File("data/duke.txt");

        try {
            dataFolder.mkdir();
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void loadDataFile() {
        File dataFile = new File("data/duke.txt");
        try {
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                processDataFile(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(("File not found!"));
        }
    }

    public void processDataFile(String dataTask) {
        String[] taskDetails = dataTask.split(" \\| ");
        String taskType = taskDetails[0];
        boolean taskIsDone = Boolean.parseBoolean(taskDetails[1]);
        String taskDescription = taskDetails[2];

        switch (taskType) {
        case "T":
            taskList[listSize] = new Todo(taskDescription);
            if (taskIsDone) {
                taskList[listSize].setAsDone();
            }
            listSize++;
            break;
        case "D":
            String deadline = taskDetails[3];
            taskList[listSize] = new Deadline(taskDescription, deadline);
            if (taskIsDone) {
                taskList[listSize].setAsDone();
            }
            listSize++;
            break;
        case "E":
            String eventTime = taskDetails[3];
            taskList[listSize] = new Event(taskDescription, eventTime);
            if (taskIsDone) {
                taskList[listSize].setAsDone();
            }
            listSize++;
            break;
        default:
            break;
        }
    }

    public void appendTaskToFile(Task task) {
        try {
            FileWriter file = new FileWriter("data/duke.txt", true);
            String taskDetails = task.getTaskDetailsInFileFormat() + "\n";
            file.write(taskDetails);
            file.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        chatBot.createDataFile();
        chatBot.loadDataFile();
        Scanner in = new Scanner(System.in);
        chatBot.handleCommand();
    }
}
