import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.exception.DukeException;

public class Duke {
    public static final String DIVIDING_LINE = "________________________________________";
    public static final int MAX_STORED_TASKS = 100;
    public static final int TODO_OFFSET = 5;
    public static final int DEADLINE_OFFSET = 9;
    public static final int EVENT_OFFSET = 6;
    public static final String PATHNAME = Paths.get("data/saveDuke.txt").toString();

    public static void printGreeting() {
        System.out.println(DIVIDING_LINE);
        System.out.println("Greetings, human! I'm Duke. \nWhat can I do for you?");
        System.out.println(DIVIDING_LINE);
    }

    public static void printFarewell() {
        System.out.println(DIVIDING_LINE);
        System.out.println("Closing Duke. Have a nice day!");
        System.out.println(DIVIDING_LINE);
    }

    public static void manageTasks() {
        Task[] userTasks = new Task[MAX_STORED_TASKS];
        Scanner userInput = new Scanner(System.in);
        loadTasks(userTasks);
        String userInputString = userInput.nextLine();

        while (!userInputString.equals("bye")) {
            System.out.println(DIVIDING_LINE);

            try {
                if (userInputString.equals("list")) {
                    listTasks(userTasks);
                } else if (userInputString.startsWith("done")) {
                    markTaskDone(userInputString, userTasks);
                } else {
                    addNewTask(userInputString, userTasks);
                }
                saveTasks(userTasks);
            } catch (DukeException e) {
                System.out.println("DukeException: " + e.getMessage());
            }

            System.out.println(DIVIDING_LINE);
            userInputString = userInput.nextLine();
        }
    }

    public static void listTasks(Task[] userTasks) {
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + ". " + userTasks[i]);
        }
    }

    public static void markTaskDone(String userInputString, Task[] userTasks) throws DukeException {
        String[] words = userInputString.split(" ");
        int completeIndex;

        try {
            completeIndex = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.TASK_INDEX_INVALID);
        }

        if (completeIndex >= 0 && completeIndex < Task.getTotalTasks()) {
            userTasks[completeIndex].markComplete();
            System.out.println("Task " + userTasks[completeIndex].getDescription() + " marked as complete.");
        } else {
            throw new DukeException(DukeException.TASK_INDEX_OOB);
        }
    }

    public static void addNewTask(String userInputString, Task[] userTasks) throws DukeException {
        if (Task.getTotalTasks() >= MAX_STORED_TASKS) {
            throw new DukeException(DukeException.TASK_ARRAY_FULL);
        }

        int slashIndex = userInputString.indexOf('/');
        String taskSubstring;
        String timeSubstring = userInputString.substring(slashIndex + 1);
        timeSubstring = timeSubstring.replaceFirst(" ", ": ");
        if (userInputString.startsWith("todo ")) {
            taskSubstring = userInputString.substring(TODO_OFFSET);
            if (taskSubstring.isBlank()) {
                throw new DukeException(DukeException.TODO_BLANK_DESC);
            }
            userTasks[Task.getTotalTasks()] = new Todo(taskSubstring);
        } else if (userInputString.startsWith("deadline ")) {
            if (slashIndex < 0) {
                throw new DukeException(DukeException.DEADLINE_NO_SLASH);
            }
            if (timeSubstring.isBlank()) {
                throw new DukeException(DukeException.DEADLINE_BLANK_DATE);
            }
            if (DEADLINE_OFFSET >= slashIndex - 1) {
                throw new DukeException(DukeException.DEADLINE_BLANK_DESC);
            }
            taskSubstring = userInputString.substring(DEADLINE_OFFSET, slashIndex - 1);
            userTasks[Task.getTotalTasks()] = new Deadline(taskSubstring, timeSubstring);
        } else if (userInputString.startsWith("event ")) {
            if (slashIndex < 0) {
                throw new DukeException(DukeException.EVENT_NO_SLASH);
            }
            if (timeSubstring.isBlank()) {
                throw new DukeException(DukeException.EVENT_BLANK_DATE);
            }
            if (EVENT_OFFSET >= slashIndex - 1) {
                throw new DukeException(DukeException.EVENT_BLANK_DESC);
            }
            taskSubstring = userInputString.substring(EVENT_OFFSET, slashIndex - 1);
            userTasks[Task.getTotalTasks()] = new Event(taskSubstring, timeSubstring);
        } else {
            throw new DukeException(DukeException.COMMAND_INVALID);
        }

        System.out.println("Gotcha. I've added this task:");
        System.out.println(userTasks[Task.getTotalTasks() - 1]);
        System.out.println("You have a total of " + Task.getTotalTasks() + " tasks now.");
    }

    public static void saveTasks(Task[] userTasks) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            output.append(userTasks[i].saveString());
            output.append(System.lineSeparator());
        }
        try {
            FileWriter writer = new FileWriter(PATHNAME);
            writer.write(output.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadTasks(Task[] userTasks) {
        File load = new File(PATHNAME);

        if (!load.getParentFile().exists()) {
            load.getParentFile().mkdir();
        }

        try {
            Scanner loadScanner = new Scanner(load);
            String currTask;

            while (loadScanner.hasNext()) {
                currTask = loadScanner.nextLine();
                String[] atomArray = currTask.split(" \\| ");
                if (atomArray[1].equals("T")) {
                    userTasks[Task.getTotalTasks()] = new Todo(atomArray[2]);
                    if (atomArray[0].equals("[X]")) {
                        userTasks[Task.getTotalTasks() - 1].markComplete();
                    }
                } else if (atomArray[1].equals("D")) {
                    userTasks[Task.getTotalTasks()] = new Deadline(atomArray[2], atomArray[3]);
                    if (atomArray[0].equals("[X]")) {
                        userTasks[Task.getTotalTasks() - 1].markComplete();
                    }
                } else {
                    userTasks[Task.getTotalTasks()] = new Event(atomArray[2], atomArray[3]);
                    if (atomArray[0].equals("[X]")) {
                        userTasks[Task.getTotalTasks() - 1].markComplete();
                    }
                }
            }
        } catch (DukeException e) {
            System.out.println("DukeException:" + e.getMessage());
        } catch (FileNotFoundException e) {
            try {
                load.createNewFile();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }

        System.out.println("Previous session restored.");
        System.out.println(DIVIDING_LINE);
    }

    public static void main(String[] args) {
        printGreeting();
        manageTasks();
        printFarewell();
    }
}
