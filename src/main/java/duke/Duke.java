package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    // ArrayList to store all tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints display message on program startup.
     */
    private static void displayGreetingMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        String greet = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);
    }

    /**
     * Beautify output by printing it along with a custom border.
     *
     * @param output Output to print
     */
    private static void printOutput(String output) {
        String niceOutput = "____________________________________________________________\n"
                + output
                + "____________________________________________________________\n";
        System.out.println(niceOutput);
    }

    /**
     * Used when adding Event & Deadline Tasks to split command using specified delimiter.
     *
     * @param delimiter Delimiter to look for to split.
     * @param arguments Arguments of the command in execution.
     * @return Array of the split values for easier processing.
     */
    private static String[] splitByDelimiter(String delimiter, String arguments) {
        String[] splitValues = new String[2];
        int indexOfDelimiter = arguments.indexOf(delimiter);
        splitValues[0] = arguments.substring(0, indexOfDelimiter).trim();
        splitValues[1] = arguments.substring(indexOfDelimiter + delimiter.length(), arguments.length()).trim();
        return splitValues;
    }

    /**
     * Adds a Todo Task.
     * If no arguments provided by user, prints error message.
     *
     * @param arguments Arguments of the command in execution.
     */
    private static void addTodoTask(String arguments) {
        if (arguments.equals("")) {
            String output = " ☹ OOPS!!! The description of a todo cannot be empty.\n";
            printOutput(output);
        } else {
            Todo newTodo = new Todo(arguments);
            tasks.add(newTodo);
            acknowledgeAddedTask(newTodo);
        }
    }

    /**
     * Adds a Deadline Task
     * If no arguments provided by user, then error message is printed.
     * Calls splitByDelimiter to process command and arguments.
     *
     * @param arguments Arguments of the command in execution.
     */
    private static void addDeadlineTask(String arguments) {
        if (arguments.equals("")) {
            String output = " ☹ OOPS!!! The description of a deadline cannot be empty.\n";
            printOutput(output);
        } else {
            String delimiter = "/by";
            try {
                String[] splitArguments = splitByDelimiter(delimiter, arguments);
                String description = splitArguments[0];
                String by = splitArguments[1];

                Deadline newDeadline = new Deadline(description, by);
                tasks.add(newDeadline);
                acknowledgeAddedTask(newDeadline);
            } catch (StringIndexOutOfBoundsException e) {
                displayDelimiterErrorMessage();
            }
        }
    }

    /**
     * Adds an Event Task
     * If no arguments provided by user, then error message is printed.
     * Calls splitByDelimiter to process command and arguments.
     *
     * @param arguments Arguments of the command in execution.
     */
    private static void addEventTask(String arguments) {
        if (arguments.equals("")) {
            String output = " ☹ OOPS!!! The description of an event cannot be empty.\n";
            printOutput(output);
        } else {
            String delimiter = "/at";
            try {
                String[] splitArguments = splitByDelimiter(delimiter, arguments);
                String description = splitArguments[0];
                String at = splitArguments[1];

                Event newEvent = new Event(description, at);
                tasks.add(newEvent);
                acknowledgeAddedTask(newEvent);
            } catch (StringIndexOutOfBoundsException e) {
                displayDelimiterErrorMessage();
            }
        }
    }

    /**
     * Deletes Task based on given user input of task number.
     * Removal of Task from ArrayList tasks.
     * Out of bounds error handling is implemented.
     *
     * @param arguments taskNumber
     */
    private static void deleteTask(String arguments) {
        int taskNumber = Integer.parseInt(arguments);
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            displayTaskDoesNotExistMessage();
        } else {
            int taskIndex = taskNumber - 1;
            Task taskToDelete = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            displayDeleteMessage(taskToDelete);
        }
    }

    /**
     * Display all Tasks in ArrayList tasks
     */
    private static void listTasks() {
        String output = " Here are the tasks in your list:\n";
        for (int i = 1; i < tasks.size() + 1; i++) {
            output = output + " " + i + "." + tasks.get(i - 1).toString() + "\n";
        }
        printOutput(output);
    }

    /**
     * Mark specified task as done.
     *
     * @param arguments taskNumber
     */
    private static void markDone(String arguments) {
        int taskNumber = Integer.parseInt(arguments);
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            displayTaskDoesNotExistMessage();
        } else {
            int taskIndex = taskNumber - 1;
            tasks.get(taskNumber).markAsDone();
            displayDoneMessage(taskNumber);
        }
    }

    private static void acknowledgeAddedTask(Task addedTask) {
        String output = " Got it. I've added this task:\n"
                + "   " + addedTask + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n";
        printOutput(output);
    }

    private static void displayDelimiterErrorMessage() {
        String output = " ☹ OOPS!!! Could not find delimiter\n";
        printOutput(output);
    }

    private static void displayTaskDoesNotExistMessage() {
        String output = " ☹ OOPS!!! The task specified does not exist.\n";
        printOutput(output);
    }

    private static void displayDeleteMessage(Task taskToDelete) {
        String output = " Noted. I've removed this task:\n"
                + "   " + taskToDelete + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.\n";
        printOutput(output);
    }

    private static void displayDoneMessage(int taskNumber) {
        String output = " Nice! I've marked this task as done:\n"
                + "   " + tasks.get(taskNumber).toString() + "\n";
        printOutput(output);
    }

    private static void displayByeMessage() {
        String output = " Bye. Hope to see you again soon!\n";
        printOutput(output);
    }

    private static void displayUnknownCommandResponse() {
        String unknownCommandResponse = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        printOutput(unknownCommandResponse);
    }

    /**
     * Execute the command based on user input
     *
     * @param command Command entered by user
     * @param arguments Arguments entered along with command by user
     * @throws DukeException Invalid command
     */
    private static void executeCommand(String command, String arguments) throws DukeException {
        switch (command) {
        case "list":
            listTasks();
            break;
        case "done":
            markDone(arguments);
            break;
        case "todo":
            addTodoTask(arguments);
            break;
        case "deadline":
            addDeadlineTask(arguments);
            break;
        case "event":
            addEventTask(arguments);
            break;
        case "delete":
            deleteTask(arguments);
            break;
        case "bye":
            saveData();
            displayByeMessage();
            System.exit(0);
        default:
            throw new DukeException();
        }
    }

    /**
     * Write data into file data/duke.txt
     * @param data
     * @throws IOException
     */
    private static void writeToFile(String data) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        fw.write(data);
        fw.close();
    }

    /**
     * Saves all task details into data/duke.txt
     * Creates directory or file if it does not exist
     */
    private static void saveData() {
        String data = "";
        for (Task task : tasks) {
            data += task.toFileFormat() + System.lineSeparator();
        }

        File myDirectory = new File("data");
        File myFile = new File("data/duke.txt");

        if (!myDirectory.exists()) {
            myDirectory.mkdir();
        }

        try {
            myFile.createNewFile();
            writeToFile(data);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Read and process tasks details from file data/duke.txt to replicate task state in program
     *
     * @param myFile File object of data/duke.txt
     * @throws FileNotFoundException If file is not found
     */
    private static void readFromFile(File myFile) throws FileNotFoundException {
        Scanner sc = new Scanner(myFile);
        while (sc.hasNext()) {
            String taskDetails = sc.nextLine();

            String[] splitTaskDetails = taskDetails.split("\\|");
            String taskType = splitTaskDetails[0];
            Boolean taskStatus = false;
            if (splitTaskDetails[1].equals("true")) {
                taskStatus = true;
            }
            String description = splitTaskDetails[2];
            String date = "";
            if (splitTaskDetails.length > 3) {
                date = splitTaskDetails[3];
            }

            switch (taskType) {
            case "T":
                Todo newTodo = new Todo(description);
                tasks.add(newTodo);
                if (taskStatus) {
                    newTodo.markAsDone();
                }
                break;
            case "D":
                Deadline newDeadline = new Deadline(description, date);
                tasks.add(newDeadline);
                if (taskStatus) {
                    newDeadline.markAsDone();
                }
                break;
            case "E":
                Event newEvent = new Event(description, date);
                tasks.add(newEvent);
                if (taskStatus) {
                    newEvent.markAsDone();
                }
                break;
            default:
                break;
            }
        }
    }

    /**
     * Load saved data from data/duke.txt into program
     */
    private static void loadData() {
        File myFile = new File("data/duke.txt");
        if (myFile.exists()) {
            try {
                readFromFile(myFile);
            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        displayGreetingMessage();
        loadData();
        while (true) {
            String userInput = "";
            String command = "";
            String arguments = "";

            userInput = sc.nextLine();
            String[] splitUserInput = userInput.trim().split("\\s+", 2);
            command = splitUserInput[0];

            if (splitUserInput.length > 1) {
                arguments = splitUserInput[1];
            }

            try {
                executeCommand(command, arguments);
            } catch (DukeException e) {
                displayUnknownCommandResponse();
            }
        }
    }
}
