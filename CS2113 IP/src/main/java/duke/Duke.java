package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    public static void Greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String horizontalLine = "________________________";
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void listOperations(Task[] tasks) throws IOException {

        Scanner sc = new Scanner(System.in);
        String horizontalLine = "________________________";
        final String GOODBYE_COMMENT = "Bye. Hope to see you again soon!";
        final String ERROR_MARK_TASK = "Please do not leave your task number empty :-(";
        final String ERROR_UNKNOWN_INPUT = ":-( OOPS!!! I'm sorry, but I don't know what that means :-(";
        final String ERROR_EMPTY_TODO_DESCRIPTION = "Please do not leave your todo description empty :-(";
        final String ERROR_EMPTY_DEADLINE_DESCRIPTION = "Please do not leave your deadline description empty :-(";
        final String ERROR_EMPTY_EVENT_DESCRIPTION = "Please do not leave your event description empty :-(";

        boolean isBye;
        boolean isList;
        boolean isDone;
        boolean isTodo;
        boolean isDeadline;
        boolean isEvent;

        do {
            String userInput = sc.nextLine();

            isBye = userInput.equals("bye");
            isList = userInput.equals("list");
            isDone = userInput.startsWith("done");
            isTodo = userInput.startsWith("todo");
            isDeadline = userInput.startsWith("deadline");
            isEvent = userInput.startsWith("event");
            System.out.println(horizontalLine);

            if (isBye) {
                System.out.println(GOODBYE_COMMENT);
            } else if (isList) {
                listTask(tasks, Task.taskCount);
            } else if (isDone) {
                try {
                    markTask(tasks, userInput);
                } catch (DukeException e) {
                    System.out.println(ERROR_MARK_TASK);
                }
            } else if (isTodo) {
                try {
                    addTask(tasks, Task.taskCount, userInput, TaskType.TODO);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_TODO_DESCRIPTION);
                }
            } else if (isDeadline) {
                try {
                    addTask(tasks, Task.taskCount, userInput, TaskType.DEADLINE);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_DEADLINE_DESCRIPTION);
                }
            } else if (isEvent) {
                try {
                    addTask(tasks, Task.taskCount, userInput, TaskType.EVENT);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_EVENT_DESCRIPTION);
                }
            } else {
                System.out.println(ERROR_UNKNOWN_INPUT);
            }
            System.out.println(horizontalLine);

        } while (!isBye);
    }

    private static void addTask(Task[] taskList, int taskCount, String userInput, TaskType specificTask) throws DukeException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        }
        final String ADDED_TASK_COMMENT = "Got it. I've added this task:";
        System.out.println(ADDED_TASK_COMMENT);
        Task newTask;

        switch (specificTask) {
        case EVENT:
            newTask = new Event(userInput, taskCount);
            break;
        case TODO:
            newTask = new Todo(userInput, taskCount);
            break;
        case DEADLINE:
            newTask = new Deadline(userInput, taskCount);
            break;
        default:
            newTask = new Task(userInput, taskCount);
            break;
        }
        taskList[taskCount] = newTask;
        Task.taskCount++;

        String printTask = String.format(" [%s][ ] %s", newTask.taskType, newTask.description);
        String printTaskNumber = String.format("Now you have %d items in the list.", Task.taskCount);
        System.out.println(printTask);
        System.out.println(printTaskNumber);
    }

    private static void markTask(Task[] tasks, String userInput) throws DukeException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        }
        char userInputIntChar = userInput.charAt(userInput.length() - 1);
        int userInputInt = Character.getNumericValue(userInputIntChar);
        final String MARK_TASK_COMMENT = "Nice! I've marked this task as done:";
        tasks[userInputInt].markAsDone();
        String formatOutput = String.format("[%s][%s] %s", tasks[userInputInt].taskType, tasks[userInputInt].getStatusIcon(), tasks[userInputInt].description);

        System.out.println(MARK_TASK_COMMENT);
        System.out.println(formatOutput);
    }

    private static void listTask(Task[] tasks, int taskCount) {
        final String LIST_TASK_COMMENT = "Here are the tasks in your list:";
        System.out.println(LIST_TASK_COMMENT);
        for (int i = 0; i < taskCount; i++) {
            int indexNumber = i + 1;
            String formatOutput = String.format("%d.[%s][%s] %s", indexNumber, tasks[i].taskType, tasks[i].getStatusIcon(), tasks[i].description);
            System.out.println(formatOutput);
        }
    }

    private static boolean isEmptyDescription(String userInput) {
        String[] trimDescription = userInput.trim().split("\\s+", 2);
        return trimDescription.length < 2;
    }

    private static void createFile(String writePath) throws IOException {
        File f = new File(writePath);
        if (f.createNewFile()) {
            System.out.println("Duke database creation <<>><<>><<>><<>><<>> created " + f.getName());
        } else {
            System.out.println("Duke database up-to-date! <<>><<>><<>><<>><<>> >:)");
        }
    }

    private static void setUpDuke(String filePath, Task[] tasks) throws IOException {
        File backupData = new File(filePath);
        Scanner sc = new Scanner(backupData);
        final int TASK_TYPE_INDEX = 0;
        final int DONE_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;

        while (sc.hasNext()) {
            boolean isDone;
            Task newTask;
            String userInput;
            String lineDataString = sc.nextLine();
            String[] lineData = lineDataString.trim().split(" \\| ");
            String taskTypeString = lineData[TASK_TYPE_INDEX];
            String isDoneString = lineData[DONE_INDEX];
            String description = lineData[DESCRIPTION_INDEX];
            isDone = isDoneString.equals("X");

            switch (taskTypeString) {
            case ("T"):
                userInput = String.format("todo %s", description.trim());
                newTask = new Todo(userInput, Task.taskCount);
                tasks[Task.taskCount] = newTask;
                break;
            case ("E"):
                userInput = String.format("event %s", description.trim());
                newTask = new Event(userInput, Task.taskCount);
                tasks[Task.taskCount] = newTask;
                break;
            case ("D"):
                userInput = String.format("deadline %s", description.trim());
                newTask = new Deadline(userInput, Task.taskCount);
                tasks[Task.taskCount] = newTask;
                break;
            }
            if (isDone) {
                tasks[Task.taskCount].markAsDone();
            }
            Task.taskCount++;
        }
    }

    private static void writeFile(String writePath, Task[] tasks) throws IOException {
        FileWriter fw = new FileWriter(writePath, true);
        for (int i = 0; i < Task.taskCount; i++) {
            boolean isEvent = tasks[i].taskType.equals("E");
            boolean isDeadline = tasks[i].taskType.equals("D");
            String formatDescription;
            if (isEvent) {
                formatDescription = String.format("%s /at %s", tasks[i].specificDescription, tasks[i].date);
            } else if (isDeadline) {
                formatDescription = String.format("%s /by %s", tasks[i].specificDescription, tasks[i].deadline);
            } else {
                formatDescription = tasks[i].description;
            }
            String formatToWrite = String.format("%s | %s | %s\n", tasks[i].taskType, tasks[i].getStatusIcon(), formatDescription);
            fw.write(formatToWrite);
        }
        fw.close();
    }

    private static String getFilePath() throws IOException {
        Path currentRelativePath = Paths.get("");
        Path currentPath = currentRelativePath.toAbsolutePath();
        String filePath = currentPath + "/data/duke.txt";
        return filePath;
    }

    public static void main(String[] args) throws IOException {
        Task[] tasks = new Task[100];
        String filePath = getFilePath();

        createFile(filePath);
        setUpDuke(filePath, tasks);
        Greet();
        listOperations(tasks);
        writeFile(filePath, tasks);
    }
}
