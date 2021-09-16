import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Duke {
    public static final String MESSAGE = "Here are the tasks in your list:";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "___________________________________________________";

    public static void main(String[] args) {
        printWelcome(LOGO, LINE);
        ArrayList<String> arrayInput = new ArrayList<>();
        ArrayList<Integer> taskStatus = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        checkCommand(output, taskStatus, arrayInput);
    }

    public static void printWelcome(String logo, String line) {
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void checkTodo(String userCommand) throws DukeException {
        if (userCommand.length() <= 5) {     //generate error when receiving invalid Todoinput
            DukeException e = new DukeException();
            throw e;
        }
    }

    public static void checkDeadline(String userCommand) throws DukeException {
        if (userCommand.length() <= 9) {     //generate error when receiving invalid Deadlineinput
            DukeException e = new DukeException();
            throw e;
        }
    }

    public static void checkEvent(String userCommand) throws DukeException {
        if (userCommand.length() <= 6) {     //generate error when receiving invalid Eventinput
            DukeException e = new DukeException();
            throw e;
        }
    }

    public static void checkDelete(String userCommand) throws DukeException {
        if (userCommand.length() <= 7) {     //generate error when receiving invalid delete input
            DukeException e = new DukeException();
            throw e;
        } else if (!isInt(userCommand.valueOf(7))) {
            DukeException e = new DukeException();
            throw e;
        }
    }

    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public static void checkCommand(ArrayList<String> output, ArrayList<Integer> taskStatus, ArrayList<String> arrayInput) {
        File file = new File("D:/data/duke.txt");
        Scanner userInput = new Scanner(System.in);
        String userCommand = userInput.nextLine();
        ArrayList<String> taskType = new ArrayList<>();
        int taskIDInt;
        int inputCount = 0;
        char taskID;
        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                System.out.println("status = " + taskStatus.get(0));
                printList(taskType, taskStatus, arrayInput, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("done")) {
                output = getUpdateDone(output, taskStatus, arrayInput, file, userCommand, taskType, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("todo")) {
                if (!isValidTodo(userCommand)) {
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = printTodo(arrayInput, userCommand, inputCount);
                output = getUpdateTodo(output, taskStatus, arrayInput, file, taskType, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("deadline")) {
                if (!isValidDeadline(userCommand)) {
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = printDeadline(arrayInput, userCommand, inputCount);
                output = getUpdateDeadline(output, taskStatus, arrayInput, file, taskType, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("event")) {
                if (!isValidEvent(userCommand)) {
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = printEvent(arrayInput, userCommand, inputCount);
                output = getUpdateEvent(output, taskStatus, arrayInput, file, taskType, inputCount, "E");
                userCommand = userInput.nextLine();
                continue;
            } else if (userCommand.contains("delete")) {
                if (!isValidDelete(userCommand)) {
                    userCommand = userInput.nextLine();
                    continue;
                }
                inputCount = printDelete(taskType, taskStatus, arrayInput, inputCount, userCommand);
                output = getUpdateDelete(output, taskStatus, arrayInput, file, taskType, inputCount);
                userCommand = userInput.nextLine();
                continue;
            } else if (!userCommand.equals("bye")) {
                printInvalid();
                System.out.println(LINE);
                userCommand = userInput.nextLine();
                continue;
            }
        }
        if (userCommand.equals("bye")) {
            printBye();
        }
    }

    private static ArrayList<String> getUpdateDelete(ArrayList<String> output,
                                                     ArrayList<Integer> taskStatus,
                                                     ArrayList<String> arrayInput, File file,
                                                     ArrayList<String> taskType, int inputCount) {
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        writeTasksToFile(file, output);
        return output;
    }

    private static ArrayList<String> getUpdateEvent(ArrayList<String> output,
                                                    ArrayList<Integer> taskStatus,
                                                    ArrayList<String> arrayInput, File file,
                                                    ArrayList<String> taskType, int inputCount, String e) {
        taskType.add(inputCount - 1, e);
        taskStatus.add(inputCount - 1, 0);
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        writeTasksToFile(file, output);
        return output;
    }

    private static ArrayList<String> getUpdateDeadline(ArrayList<String> output,
                                                       ArrayList<Integer> taskStatus,
                                                       ArrayList<String> arrayInput, File file,
                                                       ArrayList<String> taskType, int inputCount) {
        taskType.add(inputCount - 1, "D");
        taskStatus.add(inputCount - 1, 0);
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        writeTasksToFile(file, output);
        return output;
    }

    private static ArrayList<String> getUpdateTodo(ArrayList<String> output,
                                                   ArrayList<Integer> taskStatus,
                                                   ArrayList<String> arrayInput, File file,
                                                   ArrayList<String> taskType, int inputCount) {
        taskType.add(inputCount - 1, "T");
        taskStatus.add(inputCount - 1, 0);
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        writeTasksToFile(file, output);
        return output;
    }

    private static ArrayList<String> getUpdateDone(ArrayList<String> output,
                                                   ArrayList<Integer> taskStatus,
                                                   ArrayList<String> arrayInput, File file,
                                                   String userCommand,
                                                   ArrayList<String> taskType, int inputCount) {
        printDone(taskStatus, taskType, arrayInput, userCommand);
        output = convertToArrayList(output, taskType, taskStatus, arrayInput, inputCount);
        writeTasksToFile(file, output);
        return output;
    }

    public static boolean isValidTodo(String userCommand) {
        try {
            checkTodo(userCommand);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println(LINE);
            return false;
        }
        return true;
    }

    public static boolean isValidDeadline(String userCommand) {
        try {
            checkDeadline(userCommand);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            System.out.println(LINE);
            return false;
        }
        return true;
    }


    public static boolean isValidEvent(String userCommand) {
        try {
            checkEvent(userCommand);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
            System.out.println(LINE);
            return false;
        }
        return true;
    }

    public static boolean isValidDelete(String userCommand) {
        try {
            checkDelete(userCommand);
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The delete command is invalid.");
            System.out.println(LINE);
            return false;
        }
        return true;
    }

    public static int printDelete(ArrayList<String> taskType, ArrayList<Integer> taskStatus,
                                  ArrayList<String> arrayInput, int inputCount, String userCommand) {
        int position = Integer.parseInt(userCommand.substring(7)) - 1;
        System.out.println("Noted. I've removed this task:");
        if (taskStatus.get(position) == 1) {
            System.out.println("  " + "[" + taskType.get(position) + "]"
                    + "[X] " + arrayInput.get(position));
        } else {
            System.out.println("  " + "[" + taskType.get(position) + "]"
                    + "[ ] " + arrayInput.get(position));
        }
        inputCount--;
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(LINE);
        arrayInput.remove(position);
        taskStatus.remove(position);
        taskType.remove(position);
        return inputCount;
    }

    public static void printInvalid() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static int printEvent(ArrayList<String> arrayInput, String userCommand, int inputCount) {
        String at;
        int index = userCommand.indexOf("-");
        at = userCommand.substring(index - 5);
        Event event = new Event(userCommand, at, index);
        inputCount++;
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(LINE);
        String taskName = event.description.substring(6, index - 10);
        String eventName = taskName + " (at: " + at + ")";
        arrayInput.add(inputCount - 1, eventName);
        return inputCount;
    }

    private static int printDeadline(ArrayList<String> arrayInput, String userCommand, int inputCount) {
        String by;
        int index = userCommand.indexOf("by");
        by = userCommand.substring(index + 3);
        Deadline deadline = new Deadline(userCommand, by, index);
        inputCount++;
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(LINE);
        String taskName = deadline.description.substring(9, index - 2);
        String deadlineName = taskName + " (by: " + by + ")";
        arrayInput.add(inputCount - 1, deadlineName);
        return inputCount;
    }

    private static int printTodo(ArrayList<String> arrayInput, String userCommand, int inputCount) {
        Todo todo = new Todo(userCommand);
        inputCount++;
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + inputCount + " tasks in the list.");
        System.out.println(LINE);
        String todoName = todo.description.substring(5);
        arrayInput.add(inputCount - 1, todoName);
        return inputCount;
    }

    private static void printBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void printDone(ArrayList<Integer> taskStatus, ArrayList<String> taskType,
                                  ArrayList<String> arrayInput, String userCommand) {
        char taskID;
        int taskIDInt;
        int len = userCommand.length();
        taskID = userCommand.charAt(len - 1);
        taskIDInt = taskID - 49;
        taskStatus.set(taskIDInt, 1);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("	" + "[" + taskType.get(taskIDInt) + "]" +
                "[X] " + arrayInput.get(taskIDInt));
        System.out.println(LINE);
    }

    public static void printList(ArrayList<String> taskType, ArrayList<Integer> taskStatus,
                                 ArrayList<String> arrayInput, int inputCount) {
        System.out.println(LINE);
        System.out.println(MESSAGE);
        for (int i = 1; i <= inputCount; i++) {
            if (taskStatus.get(i - 1) == 1) {
                System.out.println(i + ".[" + taskType.get(i - 1) + "]" + "[X] " + arrayInput.get(i - 1));
            } else {
                System.out.println(i + ".[" + taskType.get(i - 1) + "]" + "[ ] " + arrayInput.get(i - 1));
            }
        }
        System.out.println(LINE);
    }

    public static ArrayList<String> convertToArrayList(ArrayList<String> output, ArrayList<String> taskType,
                                                       ArrayList<Integer> taskStatus,
                                                       ArrayList<String> arrayInput, int inputCount) {
        String tempTask;
        output.clear();
        for (int i = 1; i <= inputCount; i++) {
            if (taskStatus.get(i - 1) == 1) {
                tempTask = i + ".[" + taskType.get(i - 1) + "]" + "[X] " + arrayInput.get(i - 1);
                output.add(i - 1, tempTask);
            } else {
                tempTask = i + ".[" + taskType.get(i - 1) + "]" + "[ ] " + arrayInput.get(i - 1);
                output.add(i - 1, tempTask);
            }
        }
        return output;
    }

    public static void writeTasksToFile(File file, ArrayList<String> output) {
        File directory = new File("D:/data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("D:/data/duke.txt");
            for (int i = 0; i < output.size(); i++) {
                myWriter.write(output.get(i));
                myWriter.write("\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            System.out.println(LINE);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
