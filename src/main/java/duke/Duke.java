package duke;

import duke.exception.EmptyCommandArgumentException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidCommandSeparatorException;
import duke.exception.InvalidTaskIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> taskList = new ArrayList<>();
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
            case "delete":
                deleteTask(inputWords);
                handleCommand();
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (InvalidCommandException e) {
            printLine();
            System.out.println("OOPS! I'm sorry, but I don't know what that means! :(");
            System.out.println("Available commands: deadline, todo, event, done, list, delete, bye");
            printLine();
            handleCommand();
        } catch (EmptyCommandArgumentException e) {
            printLine();
            System.out.println("OOPS! The description of deadline/event/todo/delete cannot be empty! " +
                    "Please follow this format:");
            System.out.println("deadline <your task here> /by <your deadline time>");
            System.out.println("event <your task here> /at <your event time period>");
            System.out.println("todo <your task here>");
            System.out.println("delete <task number>");
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
        } catch (InvalidTaskIndexException e) {
            printLine();
            System.out.println("OOPS! That task does not exist!");
            printLine();
            handleCommand();
        }
    }

    public void deleteTask(String[] inputWords)
            throws EmptyCommandArgumentException, InvalidTaskIndexException {

        if (inputWords.length < 2) {
            throw new EmptyCommandArgumentException();
        }

        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }

        Task deletedTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);

        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        printLine();
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
            taskList.add(new Deadline(description, time));
        } else {
            taskList.add(new Event(description, time));
        }

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        printLine();

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

        taskList.add(new Todo(description));

        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        printLine();

        listSize++;
    }

    public void showList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    public void markTaskAsDone(String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        taskList.get(taskIndex).setAsDone();
        System.out.println(taskList.get(taskIndex));
    }

    public static void printLine() {
        System.out.println("-----------------------------------------------");
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

        chatBot.handleCommand();
    }
}
