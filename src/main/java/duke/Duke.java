package duke;

import duke.exception.DukeInvalidAddTaskException;
import duke.exception.DukeInvalidTaskNotExistedException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static final String LOGO = "    ____        _        \n"
            + "   |  _ \\ _   _| | _____ \n"
            + "   | | | | | | | |/ / _ \\\n"
            + "   | |_| | |_| |   <  __/\n"
            + "   |____/ \\__,_|_|\\_\\___|\n";

    public static final String BORDER_LINE = "------------------------------------------------";
    
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void printGreetingMessage() {
        System.out.println(LOGO + System.lineSeparator()
                + BORDER_LINE + System.lineSeparator()
                + "    Hello!, I'm Duke" + System.lineSeparator()
                + "    How can I help you?" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printGoodbyeMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Bye, see you again!" + System.lineSeparator()
                + BORDER_LINE);
    }
    
    public static void printFileNotDetectedMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    There is no existing file detected," + System.lineSeparator()
                + "    Empty task list initialized" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printInvalidAddTaskMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID TASK DESCRIPTION PROVIDED" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printInvalidTaskNotExistedMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID TASK NUMBER PROVIDED" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printTaskList() {
        System.out.println(BORDER_LINE);
        if (tasks.size() == 0) {
            System.out.println("    The list is currently empty!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(BORDER_LINE);
    }

    public static void printTaskAlreadyDoneMessage(int taskNumber) {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Task " + (taskNumber + 1) + " has already been marked as done!" + System.lineSeparator()
                + BORDER_LINE);
    }
    
    public static void printMarkDoneMessage(int taskNumber) {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    The following task is now marked as done:" + System.lineSeparator()
                + "      " + tasks.get(taskNumber) + System.lineSeparator()
                + BORDER_LINE);
    }
    
    public static void printAddTaskMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Task added: " + System.lineSeparator()
                + "      " + tasks.get(tasks.size() - 1) + System.lineSeparator()
                + "    You have " + tasks.size() + " tasks in the list." + System.lineSeparator()
                + BORDER_LINE);
    }
    
    public static void printDeleteTaskMessage(int taskNumber) {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    The following task is removed from the list: " + System.lineSeparator()
                + "      " + tasks.get(taskNumber) + System.lineSeparator()
                + "    You now have " + (tasks.size() - 1) + " tasks in the list." + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printInvalidCommandMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID COMMAND" + System.lineSeparator()
                + BORDER_LINE);
    }
    
    public static void addTaskFromFile(String input, int taskCount) {
        String[] words = input.split("--");
        String taskType = words[0];
        String markDoneCharacter = words[1];
        
        switch (taskType) {
        case "T":
            tasks.add(new Todo("todo " + words[2]));
            break;
        case "D":
            tasks.add(new Todo("deadline " + words[2] + " /by " + words[3]));
            break;
        case "E":
            tasks.add(new Todo("event " + words[2] + " /at " + words[3]));
            break;
        default:
            System.out.println("Invalid task in file");
            break;
        }

        if (markDoneCharacter.equals("1")) {
            tasks.get(taskCount).markAsDone();
        }
    }

    public static void readFile(String filePath) throws FileNotFoundException {
        File inputFile = new File(filePath);
        Scanner input = new Scanner(inputFile);
        String inputTask;
        int taskCount = 0;
        while (input.hasNext()) {
            inputTask = input.nextLine();
            addTaskFromFile(inputTask, taskCount);
            taskCount++;
        }
    }
    
    public static void loadTaskFromFile(String filePath) {
        try {
            readFile(filePath);
        } catch (FileNotFoundException e) {
            printFileNotDetectedMessage();
        }
    }

    public static boolean isAddTaskCommand(String command) {
        return (command.equals("todo") || command.equals("deadline") || command.equals("event"));
    }

    public static boolean containsValidTaskDescription(String userInput) {
        String[] inputWords = userInput.split(" ");
        String command = inputWords[0];
        switch (command) {
        case "todo":
            if (inputWords.length > 1) {
                return true;
            }
            break;
        case "deadline":
            for (int i = 0; i < inputWords.length; i++) {
                if (inputWords[i].equals("/by") && i < inputWords.length - 1) {
                    return true;
                }
            }
            break;
        case "event":
            for (int i = 0; i < inputWords.length; i++) {
                if (inputWords[i].equals("/at") && i < inputWords.length - 1) {
                    return true;
                }
            }
            break;
        }
        return false;
    }

    public static boolean isMarkDoneCommand(String command) {
        return command.equals("done");
    }
    
    public static boolean isDeleteTaskCommand(String command) {
        return command.equals("delete");
    }

    public static boolean containsValidTaskNumber(String userInput) {
        String[] inputWords = userInput.split(" ");
        try {
            int taskNumber = Integer.parseInt(inputWords[1]);
            return taskNumber <= tasks.size() && taskNumber > 0;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static String getCommand(String userInput) throws DukeInvalidAddTaskException, DukeInvalidTaskNotExistedException {
        String[] words = userInput.split(" ");
        String command = words[0];
        if (isAddTaskCommand(command)) {
            if (!containsValidTaskDescription(userInput)) {
                throw new DukeInvalidAddTaskException();
            }
        } else if (isMarkDoneCommand(command) || isDeleteTaskCommand(command)) {
            if (!containsValidTaskNumber(userInput)) {
                throw new DukeInvalidTaskNotExistedException();
            }
        }
        return command;
    }

    public static void markDone(String userInput) {
        String[] inputWords = userInput.split(" ");
        String extractedNumber = inputWords[1];
        int taskNumber = Integer.parseInt(extractedNumber) - 1;
        if (tasks.get(taskNumber).isDone()) {
            printTaskAlreadyDoneMessage(taskNumber);
        } else {
            tasks.get(taskNumber).markAsDone();
            printMarkDoneMessage(taskNumber);
        }
    }

    public static void addTask(String userInput, String command) {
        switch (command) {
        case "todo":
            tasks.add(new Todo(userInput));
            break;
        case "deadline":
            tasks.add(new Deadline(userInput));
            break;
        case "event":
            tasks.add(new Event(userInput));
            break;
        default:
            System.out.println("Invalid Command");
            break;
        }
        printAddTaskMessage();
    }
    
    public static void deleteTask(String userInput) {
        String[] inputWords = userInput.split(" ");
        String extractedNumber = inputWords[1];
        int taskNumber = Integer.parseInt(extractedNumber) - 1;
        printDeleteTaskMessage(taskNumber);
        tasks.remove(taskNumber);
    }

    public static void userOperation() {
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean hasEnded = false;

        while (!hasEnded) {
            userInput = input.nextLine();
            String command;
            try {
                command = getCommand(userInput);
            } catch (DukeInvalidAddTaskException e) {
                printInvalidAddTaskMessage();
                continue;
            } catch (DukeInvalidTaskNotExistedException e) {
                printInvalidTaskNotExistedMessage();
                continue;
            }
            switch (command) {
            case "list":
                printTaskList();
                break;
            case "done":
                markDone(userInput);
                break;
            case "todo":
            case "deadline":
            case "event":
                addTask(userInput, command);
                break;
            case "delete":
                deleteTask(userInput);
                break;
            case "bye":
                hasEnded = true;
                break;
            default:
                printInvalidCommandMessage();
                break;
            }
        }
    }
    
    public static void updateTaskFile(String pathFile) throws IOException{
        File directory = new File(pathFile);
        if (!directory.exists()) {
            directory.getParentFile().mkdirs();
        }
        
        FileWriter fw = new FileWriter(pathFile);
        for (int i = 0; i < tasks.size(); i++) {
            String taskAsString = String.valueOf(tasks.get(i));
            String taskType = taskAsString.substring(1, 2);
            String doneSymbol = taskAsString.substring(4, 5);
            String formattedTask = taskType + "--" + (doneSymbol.equals("X") ? "1" : "0") + "--";
            if (taskAsString.contains("(by:")) {
                formattedTask += taskAsString.substring(7, taskAsString.indexOf(" (by:")) + "--" + taskAsString.substring(taskAsString.indexOf("(by:") + 5, taskAsString.length() - 1);
            } else if (taskAsString.contains("(at:")) {
                formattedTask += taskAsString.substring(7, taskAsString.indexOf(" (at:")) + "--" + taskAsString.substring(taskAsString.indexOf("(at:") + 5, taskAsString.length() - 1);
            } else {
                formattedTask += taskAsString.substring(7);
            }
            fw.write(formattedTask + System.lineSeparator());
        }
        fw.close();
    }

    public static void main(String[] args) {
        printGreetingMessage();
        loadTaskFromFile("data/task.txt");
        userOperation();
        try {
            updateTaskFile("data/task.txt");
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        printGoodbyeMessage();
    }
}
