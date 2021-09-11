import java.util.Scanner;
import java.util.ArrayList;

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
        ArrayList<Task> userTasks = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();

        while (!userInputString.equals("bye")) {
            System.out.println(DIVIDING_LINE);

            try {
                if (userInputString.equals("list")) {
                    listTasks(userTasks);
                } else if (userInputString.startsWith("done ")) {
                    markTaskDone(userInputString, userTasks);
                } else if (userInputString.startsWith("delete ")) {
                    deleteTask(userInputString, userTasks);
                } else {
                    addNewTask(userInputString, userTasks);
                }
            } catch (DukeException e) {
                System.out.println("DukeException: " + e.getMessage());
            }

            System.out.println(DIVIDING_LINE);
            userInputString = userInput.nextLine();
        }
    }

    public static void listTasks(ArrayList<Task> userTasks) {
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            System.out.println((i + 1) + ". " + userTasks.get(i));
        }
    }

    public static void markTaskDone(String userInputString, ArrayList<Task> userTasks) throws DukeException {
        String[] words = userInputString.split(" ");
        int completeIndex;

        try {
            completeIndex = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.INDEX_INVALID);
        }

        if (completeIndex >= 0 && completeIndex < Task.getTotalTasks()) {
            userTasks.get(completeIndex).markComplete();
            System.out.println("Task " + userTasks.get(completeIndex).getDescription() + " marked as complete.");
        } else {
            throw new DukeException(DukeException.INDEX_OOB);
        }
    }

    public static void deleteTask(String userInputString, ArrayList<Task> userTasks) throws DukeException {
        String[] words = userInputString.split(" ");
        int deleteIndex;
        String taskStorage;

        try {
            deleteIndex = Integer.parseInt(words[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.INDEX_INVALID);
        }

        if (deleteIndex >= 0 && deleteIndex < Task.getTotalTasks()) {
            taskStorage = userTasks.get(deleteIndex).toString();
            userTasks.get(deleteIndex).decrementTaskNumber();
            userTasks.remove(deleteIndex);
        } else {
            throw new DukeException(DukeException.INDEX_OOB);
        }

        System.out.println("Alright. I've deleted this task:");
        System.out.println(taskStorage);
        System.out.println("You have a total of " + Task.getTotalTasks() + " tasks now.");
    }

    public static void addNewTask(String userInputString, ArrayList<Task> userTasks) throws DukeException {
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
            userTasks.add(new Todo(taskSubstring));
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
            userTasks.add(new Deadline(taskSubstring, timeSubstring));
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
            userTasks.add(new Event(taskSubstring, timeSubstring));
        } else {
            throw new DukeException(DukeException.COMMAND_INVALID);
        }

        System.out.println("Gotcha. I've added this task:");
        System.out.println(userTasks.get(Task.getTotalTasks() - 1));
        System.out.println("You have a total of " + Task.getTotalTasks() + " tasks now.");
    }

    public static void main(String[] args) {
        printGreeting();
        manageTasks();
        printFarewell();
    }
}
