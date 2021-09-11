package duke;

import duke.exception.DukeInvalidAddTaskException;
import duke.exception.DukeInvalidTaskNotExistedException;
import duke.message.OutputMessage;
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
    
    public static ArrayList<Task> tasks = new ArrayList<>();

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
            OutputMessage.printTaskAlreadyDoneMessage(taskNumber);
        } else {
            tasks.get(taskNumber).markAsDone();
            OutputMessage.printMarkDoneMessage(taskNumber);
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
        OutputMessage.printAddTaskMessage();
    }
    
    public static void deleteTask(String userInput) {
        String[] inputWords = userInput.split(" ");
        String extractedNumber = inputWords[1];
        int taskNumber = Integer.parseInt(extractedNumber) - 1;
        OutputMessage.printDeleteTaskMessage(taskNumber);
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
                OutputMessage.printInvalidAddTaskMessage();
                continue;
            } catch (DukeInvalidTaskNotExistedException e) {
                OutputMessage.printInvalidTaskNotExistedMessage();
                continue;
            }
            switch (command) {
            case "list":
                OutputMessage.printTaskList();
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
                OutputMessage.printInvalidCommandMessage();
                break;
            }
        }
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
            tasks.add(new Deadline("deadline " + words[2] + " /by " + words[3]));
            break;
        case "E":
            tasks.add(new Event("event " + words[2] + " /at " + words[3]));
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

    public static void loadDataFromFile(String filePath) {
        try {
            readFile(filePath);
        } catch (FileNotFoundException e) {
            OutputMessage.printFileNotDetectedMessage();
        }
    }
    
    public static void checkFilePath(String filePath) {
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.getParentFile().mkdirs();
        }
    }

    public static void writeToFile(String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            String taskAsString = String.valueOf(task);
            String taskType = taskAsString.substring(1, 2);
            String doneSymbol = task.isDone() ? "1" : "0";
            String formattedTask = null;
            switch (taskType) {
            case "T":
                formattedTask = "T--" + doneSymbol + "--" + task.getDescription();
                break;
            case "D":
                Deadline currentDeadline = (Deadline) task; 
                formattedTask = "D--" + doneSymbol + "--" + currentDeadline.getDescription() + "--" + currentDeadline.getDeadlineDate();
                break;
            case "E":
                Event currentEvent = (Event) task;
                formattedTask = "E--" + doneSymbol + "--" + currentEvent.getDescription() + "--" + currentEvent.getEventTime();
                break;
            default:
                System.out.println("Invalid Task Found");
                break;
            }
            writer.write(formattedTask + System.lineSeparator());
        }
        writer.close();
    }

    public static void saveDataToFile(String filePath) {
        checkFilePath(filePath);
        try {            
            writeToFile(filePath);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public static void main(String[] args) {
        OutputMessage.printGreetingMessage();
        loadDataFromFile("data/task.txt");
        userOperation();
        saveDataToFile("data/task.txt");
        OutputMessage.printGoodbyeMessage();
    }
}
