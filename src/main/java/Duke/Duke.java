package Duke;

import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static int taskCounter = 0;
    //    private static final Task[] tasks = new Task[100];
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printHeaderMessage();
        handleInputs();
        printByeMessage();
    }

    private static void handleInputs() {
        String input = getInput();
        while (!input.equals("bye")) {
            //Splits the input string by WhiteSpace into an array of strings.
            String[] splittedInput = input.split(" ");

            try {
                switch (splittedInput[0].toLowerCase()) {
                case "todo":
                    addTodoTask(input);
                    break;
                case "deadline":
                    addDeadlineTask(input);
                    break;
                case "event":
                    addEventTask(input);
                    break;
                case "list":
                    printListMessage();
                    break;
                case "done":
                    markTaskAsDone(input);
                    break;
                case "delete":
                    deleteTask(input);
                    break;
                case "bye":
                    return;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means." + System.lineSeparator()
                            + "\tPlease enter a valid input!" + System.lineSeparator()
                            + "\ti.e. todo, deadline, event, list, done or bye.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            input = getInput();
        }
//
    }

    private static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        printLineSeparator();
        return input;
    }

    private static void addTodoTask(String input) throws DukeException {
        //get the name of the task
        String taskName = getTodoTaskName(input);

        //create new Todo task
        tasks.add(new Todo(taskName));

        printNewTaskMsg();
    }

    private static String getTodoTaskName(String input) throws DukeException {
        int spaceIndex = input.indexOf(' ');
        String taskName = input.substring(spaceIndex + 1);
        if (taskName.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo task cannot be empty.");
        }
        return taskName;
    }

    private static void addEventTask(String input) throws DukeException {


        //get the name of the task
        int slashIndex = input.indexOf('/');

        String taskName = getEventTaskName(input, slashIndex);

        //get the due date of the task
        String dueDate = getDueDate(input, slashIndex);

        //create new Event task
        tasks.add(new Event(taskName, dueDate));

        printNewTaskMsg();
    }

    private static String getEventTaskName(String input, int slashIndex) throws DukeException {
        final int EVENT_WORD_LENGTH = 6;
        int taskNameLastIndex = slashIndex - 1; //the last index of the tsak name

        //if the slash is not present in the input
        if (slashIndex == -1) {
            throw new DukeException("☹ OOPS!!! The description of an event task requires a task name" + System.lineSeparator()
                    + "\tfollowed by a front slash, and then a specific time after." + System.lineSeparator()
                    + "\ti.e. event team project meeting /on 2-10-2019 2-4pm");
        } else if (taskNameLastIndex <= EVENT_WORD_LENGTH) { //if the slash exists but the task name is empty
            throw new DukeException("☹ OOPS!!! The name of an event task cannot be empty.");
        }

        String taskName = input.substring(EVENT_WORD_LENGTH, taskNameLastIndex);

        //if taskName are just filled with whitespaces
        if (taskName.replace(" ", "").isEmpty()) {
            throw new DukeException("☹ OOPS!!! The name of an event task cannot be empty.");
        }
        return taskName;
    }

    private static void addDeadlineTask(String input) throws DukeException {

        //get the name of the task
        int slashIndex = input.indexOf('/');
        String taskName = getDeadlineTaskName(input, slashIndex);

        //get the due date of the task
        String dueDate = getDueDate(input, slashIndex);

        //create new Deadline task
        tasks.add(new Deadline(taskName, dueDate));

        printNewTaskMsg();
    }

    private static String getDeadlineTaskName(String input, int slashIndex) throws DukeException {
        final int DEADLINE_WORD_LENGTH = 9;
        int taskNameLastIndex = slashIndex - 1; //the last index of the tsak name

        //if the slash is not present in the input
        if (slashIndex == -1) {
            throw new DukeException("☹ OOPS!!! The description of an deadline task requires a task name" + System.lineSeparator()
                    + "\tfollowed by a front slash, and then a specific date/time after." + System.lineSeparator()
                    + "\ti.e. deadline submit report /by 11-10-2019 5pm");
        } else if (taskNameLastIndex <= DEADLINE_WORD_LENGTH) { //if the slash exists but the task name is empty
            throw new DukeException("☹ OOPS!!! The name of an deadline task cannot be empty.");
        }

        String taskName = input.substring(DEADLINE_WORD_LENGTH, taskNameLastIndex);

        //if taskName are just filled with whitespaces
        if (taskName.replace(" ", "").isEmpty()) {
            throw new DukeException("☹ OOPS!!! The name of a deadline task cannot be empty.");
        }
        return taskName;
    }

    private static String getDueDate(String input, int slashIndex) throws DukeException {
        if (slashIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of this task type requires a specific time");
        }
        return input.substring(slashIndex + 1);
    }

    private static void printNewTaskMsg() {
        int taskCount = taskCounter + 1;
        System.out.println("\tAlright! I've just added this task:");
        System.out.println("\t" + tasks.get(taskCounter).toString());
        System.out.println("\tYou now have " + taskCount + " tasks on your task list.");
        printLineSeparator();
        taskCounter++;
    }

    private static void printListMessage() {
        if (taskCounter == 0) {
            System.out.println("\tThe list is empty!");
        } else {
            System.out.println("\tHere's the list of your tasks: ");
            for (int j = 0; j < taskCounter; j++) {
                int itemNumber = j + 1;
                System.out.println("\t" + itemNumber + "." + tasks.get(j).toString());
            }
        }
        printLineSeparator();
    }

    private static void markTaskAsDone(String s) {

        String[] splittedInput = s.split(" ");
        try {
            int taskIndex = Integer.parseInt(splittedInput[1]) - 1;
            tasks.get(taskIndex).markAsDone();
            System.out.println("\tGood job! I've marked this task as done: ");
            System.out.println("\t" + tasks.get(taskIndex));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            if (taskCounter == 0) {
                System.out.println("\t☹ OOPS!!! The list is empty!");
            } else {
                System.out.println("\t☹ OOPS!!! Please enter a valid task index.");
            }
        }
        printLineSeparator();
    }

    private static void deleteTask(String s) throws IndexOutOfBoundsException, NullPointerException {
        String[] splittedInput = s.split(" ");

        try {
            int taskIndex = Integer.parseInt(splittedInput[1]) - 1;

            System.out.println("\tAlright, I've deleted this task: " + System.lineSeparator()
                    + "\t" + tasks.get(taskIndex).toString());
            taskCounter--;
            tasks.remove(taskIndex);
            System.out.println("\tYou now have " + taskCounter + " tasks on your task list.");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            if (taskCounter == 0) {
                System.out.println("\t☹ OOPS!!! The list is empty!");
            } else {
                System.out.println("\t☹ OOPS!!! Please enter a valid task index.");
            }
        }
        printLineSeparator();
    }

    private static void printHeaderMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("\tHey, how are you?");
        System.out.println("\tWhat can I do for you today?");
        printLineSeparator();
    }

    private static void printByeMessage() {
        System.out.println("\tGoodbye! Hope to see you again soon!");
        printLineSeparator();
    }

    private static void printLineSeparator() {
        System.out.println("\t----------------------------------------------------------------------");
    }
}
