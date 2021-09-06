package duke;

import duke.actions.Deadline;
import duke.actions.Event;
import duke.actions.Task;
import duke.actions.Todo;
import duke.exceptions.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINE = "------------------------------------------------------";
    public static final String STRING_LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String INCORRECT_TASK_COMMAND = "Please specify a task to be added!";
    public static final String INCORRECT_DEADLINE_COMMAND = "Please specify a task/deadline of completion!";
    public static final String INCORRECT_EVENT_COMMAND = "Please specify an event/time of the event!";
    public static final String INCORRECT_DONE_COMMAND = "Please specify the number of the task to be marked as done!";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String userInput = "";

        System.out.println("Hello from\n" + STRING_LOGO);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke!\n"
                + "So far, I can create a simple task list for you.\n"
                + "What can I do for you?\n");

        while (!userInput.startsWith("bye")) {
            userInput = in.nextLine().toLowerCase();
            if (userInput.contains("help")) {
                printHelpList();
            } else if (userInput.startsWith("to do ")) {
                try {
                    addTaskAsToDo(taskList, userInput);
                } catch (DukeException e) {
                    System.out.println(INCORRECT_TASK_COMMAND);
                }
            } else if (userInput.startsWith("deadline ")) {
                try {
                    addTaskAsDeadline(taskList, userInput);
                } catch (DukeException e) {
                    System.out.println(INCORRECT_DEADLINE_COMMAND);
                }
            } else if (userInput.startsWith("event ")) {
                try {
                    addTaskAsEvent(taskList, userInput);
                } catch (DukeException e) {
                    System.out.println(INCORRECT_EVENT_COMMAND);
                }
            } else if (userInput.startsWith("list")) {
                printTaskList(taskList);
            } else if (userInput.startsWith("done ")) {
                try {
                    markTaskAsDone(taskList, userInput);
                } catch (DukeException e) {
                    System.out.println(INCORRECT_DONE_COMMAND);
                }
            } else if (userInput.startsWith("bye")) {
                break;
            } else {
                System.out.println("Aw man! I am unable to " + userInput + " yet! Please specify a different function! :D");
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addTaskAsEvent(ArrayList<Task> taskList, String userInput) throws DukeException {
        if (userInput.contains("/at")) {
            Task taskAdded = new Event(userInput);
            if (!taskAdded.toString().equals("") && !taskAdded.getDeadline().equals("")) {
                taskList.add(taskAdded);
                printTaskAddedConfirmation(taskAdded);
            } else {
                throw new DukeException();
            }
        } else {
            throw new DukeException();
        }
    }

    private static void addTaskAsDeadline(ArrayList<Task> taskList, String userInput) throws DukeException {
        if (userInput.contains("/by")) {
            Task taskAdded = new Deadline(userInput);
            if (!taskAdded.toString().equals("") && !taskAdded.getDeadline().equals("")) {
                taskList.add(taskAdded);
                printTaskAddedConfirmation(taskAdded);
            } else {
                throw new DukeException();
            }
        } else {
            throw new DukeException();
        }
    }

    private static void addTaskAsToDo(ArrayList<Task> taskList, String userInput) throws DukeException {
        String task = userInput.replace("to do ", "").trim();
        Task taskAdded = new Todo(task);
        if (!task.equals("")) {
            taskList.add(taskAdded);
            printTaskAddedConfirmation(taskAdded);
        } else {
            throw new DukeException();
        }
    }

    private static void markTaskAsDone(ArrayList<Task> taskList, String userInput) throws DukeException {
        int wordIndex = 0;
        boolean numberExists = false;
        String[] splitTask = userInput.replaceAll("[\\p{Alpha}, [\\p{Punct}&&[^-]]+]", " ").trim().split(" ");
        for (String word : splitTask) {
            if (isValidNumber(word)) {
                numberExists = true;
                int taskNumber = (Integer.parseInt(splitTask[wordIndex])) - 1;
                try {
                    printTaskMarkAsDone(taskList, taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("No number or Invalid number specified! Please specify the number on the list of the task you have completed!");
                }
            }
            wordIndex++;
        }
        if (!numberExists) {
            throw new DukeException();
        }
    }

    public static boolean isValidNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static void printTaskMarkAsDone(ArrayList<Task> taskList, int taskNumber) {
        Task taskUpdated = taskList.get(taskNumber);
        taskUpdated.updateIsDone();
        taskUpdated.printMarkAsDoneMessage(taskNumber);
    }

    private static void printTaskList(ArrayList<Task> taskList) {
        int listIndex = 1;
        System.out.println(HORIZONTAL_LINE);
        for (Task task : taskList) {
            task.printTaskList(listIndex);
            listIndex++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printTaskAddedConfirmation(Task taskAdded) {
        System.out.println(HORIZONTAL_LINE);
        taskAdded.printTaskAddedMessage();
        System.out.println(HORIZONTAL_LINE);
    }

    private static void printHelpList() {
        System.out.println("Use the following commands!\n" +
                "To add a task without deadline: to do [taskName]\n" +
                "To add a task with a deadline: deadline [deadlineName] /by [deadline]\n" +
                "To add an event: event [eventName] /at [eventTime]\n" +
                "To mark a task as done: done [taskNumber]\n" +
                "To view your current task list, simply type: list\n" +
                "To end your chat with me, simply type: bye\n" +
                HORIZONTAL_LINE);
    }
}
