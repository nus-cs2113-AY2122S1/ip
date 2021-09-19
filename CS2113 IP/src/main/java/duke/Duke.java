package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    final private static String GOODBYE_COMMENT = "Bye. Hope to see you again soon!";
    final private static String ERROR_MARK_TASK = "Please do not leave your task number empty :-(";
    final private static String ERROR_UNKNOWN_INPUT = ":-( OOPS!!! I'm sorry, but I don't know what that means :-(";
    final private static String ERROR_EMPTY_TODO_DESCRIPTION = "Please do not leave your todo description empty :-(";
    final private static String ERROR_EMPTY_DEADLINE_DESCRIPTION = "Please do not leave your deadline description empty :-(";
    final private static String ERROR_EMPTY_EVENT_DESCRIPTION = "Please do not leave your event description empty :-(";
    final private static String ERROR_EMPTY_DELETE_DESCRIPTION = "Please do not leave your delete task number empty :-(";
    final private static String ERROR_WRONG_HANDLE_TODO_DESCRIPTION = "Check for missing fields in your description!";
    final private static String ERROR_WRONG_HANDLE_EVENT_DESCRIPTION = "Include /at handler and insert date of event!";
    final private static String ERROR_WRONG_HANDLE_DEADLINE_DESCRIPTION = "Include /by handler and insert deadline!";
    final private static String ERROR_MARK_TASK_UNKNOWN_INPUT = "Please enter as follows: done (INT in number)";
    final private static String ERROR_DELETE_TASK_UNKNOWN_INPUT = "Please enter as follows: delete (INT in number)";
    final private static String ERROR_EMPTY_FIELDS = "There are empty fields, check storage text file!";
    final private static String ERROR_OUT_OF_BOUNDS = "That task does not exist! Stop fooling around!";
    final private static String HORIZONTAL_LINE = "_________________________________________________________________";
    final private static ArrayList<Task> tasks = new ArrayList<Task>();
    final private static String filePath = getFilePath();


    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void listOperations() throws IOException {

        Scanner sc = new Scanner(System.in);

        boolean isBye;
        boolean isList;
        boolean isDone;
        boolean isTodo;
        boolean isDeadline;
        boolean isEvent;
        boolean isDelete;

        do {
            String userInput = sc.nextLine();

            isBye = userInput.equals("bye");
            isList = userInput.equals("list");
            isDone = userInput.startsWith("done");
            isTodo = userInput.startsWith("todo");
            isDeadline = userInput.startsWith("deadline");
            isEvent = userInput.startsWith("event");
            isDelete = userInput.startsWith("delete");
            System.out.println(HORIZONTAL_LINE);

            if (isBye) {
                System.out.println(GOODBYE_COMMENT);
            } else if (isList) {
                listTask(Task.taskCount);
            } else if (isDone) {
                try {
                    markTask(userInput);
                } catch (DukeException e) {
                    System.out.println(ERROR_MARK_TASK);
                } catch (NumberFormatException e) {
                    System.out.println(ERROR_MARK_TASK_UNKNOWN_INPUT);
                } catch (OutOfBoundsException e) {
                    System.out.println(ERROR_OUT_OF_BOUNDS);
                }
            } else if (isDelete) {
                try {
                    deleteTask(userInput);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_DELETE_DESCRIPTION);
                } catch (NumberFormatException e) {
                    System.out.println(ERROR_DELETE_TASK_UNKNOWN_INPUT);
                } catch (OutOfBoundsException e) {
                    System.out.println(ERROR_OUT_OF_BOUNDS);
                }
            } else if (isTodo) {
                try {
                    addTask(userInput, TaskType.TODO);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_TODO_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_WRONG_HANDLE_TODO_DESCRIPTION);
                }
            } else if (isDeadline) {
                try {
                    addTask(userInput, TaskType.DEADLINE);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_DEADLINE_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_WRONG_HANDLE_DEADLINE_DESCRIPTION);
                }
            } else if (isEvent) {
                try {
                    addTask(userInput, TaskType.EVENT);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_EVENT_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_WRONG_HANDLE_EVENT_DESCRIPTION);
                }
            } else {
                System.out.println(ERROR_UNKNOWN_INPUT);
            }
            System.out.println(HORIZONTAL_LINE);

        } while (!isBye);
    }

    private static void addTask(String userInput, TaskType specificTask) throws DukeException, FormatException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        } else if (isIncorrectFormat(userInput, specificTask)) {
            throw new FormatException();
        }

        Task newTask;
        switch (specificTask) {
        case EVENT:
            newTask = new Event(userInput, Task.taskCount);
            break;
        case TODO:
            newTask = new Todo(userInput, Task.taskCount);
            break;
        case DEADLINE:
            newTask = new Deadline(userInput, Task.taskCount);
            break;
        default:
            newTask = new Task(userInput, Task.taskCount);
            break;
        }
        tasks.add(newTask);
        Task.taskCount++;

        final String ADDED_TASK_COMMENT = "Got it. I've added this task:";
        System.out.println(ADDED_TASK_COMMENT);
        String printTask = String.format(" [%s][ ] %s", newTask.taskType, newTask.description);
        String printTaskNumber = String.format("Now you have %d items in the list.", Task.taskCount);
        System.out.println(printTask);
        System.out.println(printTaskNumber);
    }

    private static void deleteTask(String userInput) throws DukeException, OutOfBoundsException, NumberFormatException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        }

        String[] splitStringBySpace = userInput.trim().split("\\s+", 2);
        String userInputIntString = splitStringBySpace[1];
        int userInputInt = Integer.parseInt(userInputIntString);

        if (userInputInt > tasks.size()) {
            throw new OutOfBoundsException();
        }

        final String DELETE_TASK_COMMENT = "Noted. I've removed this task:";
        System.out.println(DELETE_TASK_COMMENT);

        String printTask = String.format(" [%s][ ] %s",
                tasks.get(userInputInt).taskType, tasks.get(userInputInt).description);
        tasks.remove(userInputInt);
        Task.taskCount--;
        String printTaskNumber = String.format("Now you have %d items in the list.", Task.taskCount);

        System.out.println(printTask);
        System.out.println(printTaskNumber);
    }

    private static void markTask(String userInput) throws DukeException, NumberFormatException, OutOfBoundsException {
        if (isEmptyDescription(userInput)) {
            throw new DukeException();
        }
        String[] splitStringBySpace = userInput.trim().split("\\s+", 2);
        String userInputIntString = splitStringBySpace[1];
        int userInputInt = Integer.parseInt(userInputIntString);

        if (userInputInt > tasks.size()) {
            throw new OutOfBoundsException();
        }

        final String MARK_TASK_COMMENT = "Nice! I've marked this task as done:";
        tasks.get(userInputInt).markAsDone();
        String formatOutput = String.format("[%s][%s] %s",
                tasks.get(userInputInt).taskType, tasks.get(userInputInt).getStatusIcon(), tasks.get(userInputInt).description);

        System.out.println(MARK_TASK_COMMENT);
        System.out.println(formatOutput);
    }

    private static void listTask(int taskCount) {
        final String LIST_TASK_COMMENT = "Here are the tasks in your list:";
        System.out.println(LIST_TASK_COMMENT);
        for (int i = 0; i < taskCount; i++) {
            int indexNumber = i + 1;
            String formatOutput = String.format("%d.[%s][%s] %s",
                    indexNumber, tasks.get(i).taskType, tasks.get(i).getStatusIcon(), tasks.get(i).description);
            System.out.println(formatOutput);
        }
    }

    private static boolean isEmptyDescription(String userInput) {
        String[] trimDescription = userInput.trim().split("\\s+", 2);
        return trimDescription.length < 2;
    }

    private static boolean isIncorrectFormat(String userInput, TaskType specificTask) {
        switch (specificTask) {
        case EVENT:
            boolean hasEventPlaceholder = userInput.contains("/at");
            boolean hasNoEventPlaceholder = !hasEventPlaceholder;
            return hasNoEventPlaceholder;

        case DEADLINE:
            boolean hasDeadlinePlaceholder = userInput.contains("/by");
            boolean hasNoDeadlinePlaceholder = !hasDeadlinePlaceholder;
            return hasNoDeadlinePlaceholder;

        default:
            return false;
        }
    }

    private static void createFile() throws IOException {
        File f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println("Duke database creation <<>><<>><<>><<>><<>> created " + f.getName());
        } else {
            System.out.println("Duke database up-to-date! <<>><<>><<>><<>><<>> >:)");
        }
    }

    private static void setUpDuke() throws IOException, DukeException {
        File backupData = new File(filePath);
        Scanner sc = new Scanner(backupData);
        final int TASK_TYPE_INDEX = 0;
        final int DONE_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;

        try {
            while (sc.hasNext()) {
                boolean isDone;
                Task newTask;
                String userInput;
                String lineDataString = sc.nextLine();
                String[] lineData = lineDataString.trim().split(" \\| ");
                completeChecker(lineData);

                String taskTypeString = lineData[TASK_TYPE_INDEX];
                String isDoneString = lineData[DONE_INDEX];
                String description = lineData[DESCRIPTION_INDEX];
                isDone = isDoneString.equals("X");

                switch (taskTypeString) {
                case ("T"):
                    userInput = String.format("todo %s", description.trim());
                    newTask = new Todo(userInput, Task.taskCount);
                    tasks.add(Task.taskCount, newTask);
                    break;
                case ("E"):
                    userInput = String.format("event %s", description.trim());
                    newTask = new Event(userInput, Task.taskCount);
                    tasks.add(Task.taskCount, newTask);
                    break;
                case ("D"):
                    userInput = String.format("deadline %s", description.trim());
                    newTask = new Deadline(userInput, Task.taskCount);
                    tasks.add(Task.taskCount, newTask);
                    break;
                }
                if (isDone) {
                    tasks.get(Task.taskCount).markAsDone();
                }
                Task.taskCount++;
            }
        } catch (DukeException e) {
            System.out.println(ERROR_EMPTY_FIELDS);
        }
    }

    private static void completeChecker(String[] lineData) throws DukeException {
        if (lineData.length < 3) {
            throw new DukeException();
        }
    }

    private static void writeFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (int i = 0; i < Task.taskCount; i++) {
            boolean isEvent = tasks.get(i).taskType.equals("E");
            boolean isDeadline = tasks.get(i).taskType.equals("D");
            String formatDescription;
            if (isEvent) {
                formatDescription = String.format("%s /at %s",
                        tasks.get(i).specificDescription, tasks.get(i).date);
            } else if (isDeadline) {
                formatDescription = String.format("%s /by %s",
                        tasks.get(i).specificDescription, tasks.get(i).deadline);
            } else {
                formatDescription = tasks.get(i).description;
            }

            String formatToWrite = String.format("%s | %s | %s\n",
                    tasks.get(i).taskType, tasks.get(i).getStatusIcon(), formatDescription);
            fw.write(formatToWrite);
        }
        fw.close();
    }

    private static String getFilePath() {
        Path currentRelativePath = Paths.get("");
        Path currentPath = currentRelativePath.toAbsolutePath();
        String filePath = currentPath + "/data/duke.txt";
        return filePath;
    }

    private static void createDirectory() {
        Path currentRelativePath = Paths.get("");
        Path currentPath = currentRelativePath.toAbsolutePath();
        File f = new File(currentPath + "/data");
        boolean isCreated = f.mkdir();
        if (isCreated) {
            System.out.println("data directory created successfully >>::))");
        } else {
            System.out.println("Duke-data directory exists >>::))");
        }
    }

    public static void main(String[] args) throws IOException, DukeException {
        createDirectory();
        createFile();
        setUpDuke();
        greet();
        listOperations();
        writeFile();
    }
}
